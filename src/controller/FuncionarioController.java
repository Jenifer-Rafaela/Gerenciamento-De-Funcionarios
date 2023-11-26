package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import DTO.FuncionarioDTO;
import model.BD;
import service.ManipuladorDados;

public class FuncionarioController {

	private Connection con;
	private PreparedStatement query;
	private ResultSet queryData;


	private BD bancoDeDados = new BD();
	private ManipuladorDados manipuladorDados = new ManipuladorDados();

	public FuncionarioController() {}


	public FuncionarioDTO buscarFuncionario(String idFuncionario, JTextField textField_buscar) {
		if(idFuncionario.matches("^\\d+$")) {
			String select = "SELECT NOME, EMAIL, CPF, DN, SETORID FROM FUNCIONARIOS WHERE ID = ?";

			try {
				con = bancoDeDados.conectar();
				query = con.prepareStatement(select);
				query.setString(1, idFuncionario);
				queryData = query.executeQuery();

				if(queryData.next()) {
					System.out.println("linha - 41: Funcionario encontrado.");
					return new FuncionarioDTO(queryData.getString(1),queryData.getString(2),queryData.getString(3), manipuladorDados.dataFormatada(queryData.getString(4)),queryData.getInt(5));
				} else {
					System.out.println("linha - 44: Funcionario não encontrado.");
					JOptionPane.showMessageDialog(null, "Funcionario com código "+idFuncionario+" não foi encontrado.", "Campo Invalido",
							JOptionPane.ERROR_MESSAGE);
					textField_buscar.requestFocus();
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("linha - 55: Código do funcionário é inválido.");
			JOptionPane.showMessageDialog(null, "O código do funcionario deve ser numérico!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_buscar.requestFocus();
		}
		return null;
	}

	public DefaultTableModel buscarFuncionarios(DefaultTableModel model) {
		String select = "SELECT FUNCIONARIOS.ID,FUNCIONARIOS.NOME, FUNCIONARIOS.EMAIL, FUNCIONARIOS.CPF, FUNCIONARIOS.DN, SETOR.NOME AS SETOR\r\n"
				+ "FROM FUNCIONARIOS\r\n"
				+ "JOIN SETOR\r\n"
				+ "ON FUNCIONARIOS.SETORID = SETOR.SETORID;";

		try {
			con = bancoDeDados.conectar();
			query = con.prepareStatement(select);
			queryData = query.executeQuery();

			
			while(queryData.next()) {
				String[] linha = {queryData.getString(1),queryData.getString(2),queryData.getString(3),queryData.getString(4), manipuladorDados.dataFormatada(queryData.getString(5)),queryData.getString(6)};
				model.addRow(linha);
			}
			con.close();
			System.out.println("linha - 80: Funcionários encontrados.");
			return model;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} 
	}

	public void removerFuncionario(String idFuncionario) {
		String remove = "DELETE FROM FUNCIONARIOS WHERE ID = ?";
		try {
			con = bancoDeDados.conectar();
			query = con.prepareStatement(remove);
			query.setString(1, idFuncionario);

			int deletado = query.executeUpdate();

			//verifica se foi executado ou não
			if(deletado == 1) {
				JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso!");
				System.out.println("linha - 100: Funcionário removido.");
			} else{
				JOptionPane.showMessageDialog(null, "Funcionário não pode ser removido.");
				con.close();
			}
			con.close();
			return;
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}

	public boolean login(String usuario, String senha) {

		String select = "select id from usuarios where usuario = ? AND permissao = 'ADMIN' AND senha = ?";

		try {
			con = bancoDeDados.conectar();
			query = con.prepareStatement(select);
			query.setString(1, usuario);
			query.setString(2, senha);

			queryData = query.executeQuery();

			if(queryData.next()) {
				con.close();
				return true;
			} else {
				System.out.println("linha - 113: Login Incorreto");
				JOptionPane.showMessageDialog(null, "Email ou Senha Incorreto");
				return false;
			}

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public DefaultComboBoxModel<String> buscarSetores(DefaultComboBoxModel<String> boxModel) {
		String select = "SELECT nome FROM SETOR;";

		try {
			con = bancoDeDados.conectar();
			query = con.prepareStatement(select);

			queryData = query.executeQuery();

			while(queryData.next()) {
				String linha = queryData.getString(1);
				boxModel.addElement(linha);
			}
			con.close();
			System.out.println("linha - 154: Setores encontrados.");
			return boxModel;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void atualizarFuncionario(String email, String nome, Date data, String cpf, int setorId, int funcionarioId, JFormattedTextField fTextField_cpf, JTextField textField_email) {
		//query que será feita no banco de dados
		String update = "UPDATE funcionarios SET Email = ?, Nome = ?, DN = ?, CPF = ?, setorId = ? WHERE id = ?";

		try {
			//faz conexão com o banco
			con = bancoDeDados.conectar();
			//coloca os dados a serem inseridos na query
			query = con.prepareStatement(update);
			query.setString(1, email);
			query.setString(2, nome);
			query.setDate(3, data);
			query.setString(4, cpf);
			query.setInt(5, setorId);
			query.setInt(6, funcionarioId);

			int atualizado = query.executeUpdate();

			//verifica se foi executado ou não
			if(atualizado == 1) {
				JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
			} else{
				JOptionPane.showMessageDialog(null, "Funcionário não atualizado.");
			}
			//fecha conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao atualizar funcionário");
			if(e.getMessage().contains("CPF_UNIQUE")) {
				System.out.println("linha - 208: Cpf já está em uso.");
				JOptionPane.showMessageDialog(null, "CPF informado já está em uso.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				fTextField_cpf.requestFocus();
			}else if(e.getMessage().contains("Email")) {
				System.out.println("linha - 213: Email já está em uso.");
				JOptionPane.showMessageDialog(null, "Email informado já está em uso.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				textField_email.requestFocus();
			} else System.out.println(e);
		}
	}

	public int cadastrarFuncionario(String email, String nome, Date data, String cpf, int setorId, JFormattedTextField fTextField_cpf, JTextField textField_email) {
		//query que será feita no banco de dados
		String insert = "insert into funcionarios(Email,Nome,DN,CPF,setorId) values(?,?,?,?,?)";

		try {
			//faz conexão com o banco
			con = bancoDeDados.conectar();
			//coloca os dados a serem inseridos na query
			query = con.prepareStatement(insert);
			query.setString(1, email);
			query.setString(2, nome);
			query.setDate(3, data);
			query.setString(4, cpf);
			query.setInt(5, setorId);

			int cadastrado = query.executeUpdate();

			//verifica se foi executado ou não
			if(cadastrado == 1) {
				JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
				System.out.println("linha - 224: Funcionário Cadastrado .");
				return 1;
			}

			//fecha conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println("Erro ao Cadastrar Funcionário");

			if(e.getMessage().contains("CPF_UNIQUE")) {
				JOptionPane.showMessageDialog(null, "CPF informado já está em uso.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				fTextField_cpf.requestFocus();
			}
			if(e.getMessage().contains("Email")) {
				JOptionPane.showMessageDialog(null, "Email informado já está em uso.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				textField_email.requestFocus();
			}
			return 0;
		}
		return 0;
	}
}
