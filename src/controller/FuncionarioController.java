package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import DTO.FuncionarioDTO;
import model.BD;

public class FuncionarioController {

	private Connection con;
	private PreparedStatement query;
	private ResultSet queryData;


	private BD bancoDeDados = new BD();
	private FuncionarioDTO funcionarioDTO;



	public FuncionarioDTO buscarFuncionario(String idFuncionario) {
		String select = "SELECT NOME, EMAIL, CPF, DN, SETORID FROM FUNCIONARIOS WHERE ID = ?";

		try {
			con = bancoDeDados.conectar();
			query = con.prepareStatement(select);
			query.setString(1, idFuncionario);
			queryData = query.executeQuery();

			if(queryData.next()) {
				funcionarioDTO = new FuncionarioDTO(queryData.getString(1),queryData.getString(2),queryData.getString(3), queryData.getString(4),queryData.getInt(5));
			} else {
				funcionarioDTO = null;
			}
			con.close();
			return funcionarioDTO;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
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
				funcionarioDTO = new FuncionarioDTO(queryData.getString(5));

				String[] linha = {queryData.getString(1),queryData.getString(2),queryData.getString(3),queryData.getString(4),funcionarioDTO.getData(),queryData.getString(6)};
				model.addRow(linha);
			}
			con.close();
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
				System.out.println("linha - 113: Login incorreto");
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
			return boxModel;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public String atualizarFuncionario(String email, String nome, Date data, String cpf, int setorId, int funcionarioId) {
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
				return "";
			} else{
				JOptionPane.showMessageDialog(null, "Funcionário não atualizado.");
			}
			//fecha conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println("Erro");
			return e.getMessage();
		}
		return null;
	}

	public String cadastrarFuncionario(String email, String nome, Date data, String cpf, int setorId) {
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
				return "reset";
			} else{
				JOptionPane.showMessageDialog(null, "Funcionário não cadastrado.");
			}
			//fecha conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println("Erro");
			return e.getMessage();
		}
		return null;
	}
}
