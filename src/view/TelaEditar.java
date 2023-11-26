package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.FuncionarioDTO;
import controller.FuncionarioController;
import service.ManipuladorDados;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class TelaEditar extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_buscar;
	private JTextField textField_nome;
	private JTextField textField_email;
	private JFormattedTextField fTextField_data;
	private JFormattedTextField fTextField_cpf;
	private LocalDate stringParaData;
	private int setorId;
	private String CPFParaString;
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnBuscar = new JButton("Buscar");
	private FuncionarioController funcionarioController = new FuncionarioController();
	private ManipuladorDados manipuladorDados = new ManipuladorDados();
	private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
	private JComboBox<String> comboBoxSetor = new JComboBox<String>();

	public static void main(String[] args) {
		TelaEditar tela = new TelaEditar();
		tela.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public TelaEditar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_buscar = new JTextField();
		textField_buscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_buscar.setBounds(30, 61, 89, 31);
		contentPane.add(textField_buscar);
		textField_buscar.setColumns(10);

		JLabel lblFuncionarioId = new JLabel("Código Funcionário");
		lblFuncionarioId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFuncionarioId.setBounds(30, 42, 108, 22);
		contentPane.add(lblFuncionarioId);


		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(this);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBorderPainted(false);
		btnBuscar.setFocusable(false);
		btnBuscar.setBackground(new Color(59, 113, 202));
		btnBuscar.setBounds(129, 61, 89, 31);
		contentPane.add(btnBuscar);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(30, 106, 52, 14);
		contentPane.add(lblNome);

		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		textField_nome.setBounds(30, 122, 214, 31);
		contentPane.add(textField_nome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(269, 106, 46, 14);
		contentPane.add(lblEmail);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(269, 123, 214, 33);
		contentPane.add(textField_email);

		fTextField_data = manipuladorDados.formatarData(fTextField_data);

		JLabel lblDataDeNascimento = new JLabel("Data De Nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeNascimento.setBounds(30, 164, 122, 14);
		contentPane.add(lblDataDeNascimento);

		fTextField_data.setBounds(30, 180, 214, 31);
		contentPane.add(fTextField_data);

		fTextField_cpf = manipuladorDados.formatarCpf(fTextField_cpf);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCPF.setBounds(269, 163, 25, 14);
		contentPane.add(lblCPF);

		fTextField_cpf.setBounds(269, 180, 214, 31);
		contentPane.add(fTextField_cpf);

		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetor.setBounds(30, 229, 36, 14);
		contentPane.add(lblSetor);

		comboBoxSetor.setBounds(30, 244, 214, 31);
		contentPane.add(comboBoxSetor);

		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.addActionListener(this);
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setBorderPainted(false);
		btnSalvar.setFocusable(false);
		btnSalvar.setBackground(new Color(59, 113, 202));
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalvar.setBounds(175, 288, 163, 31);
		contentPane.add(btnSalvar);

		JLabel lblAtualizar = new JLabel("Atualizar Funcionário");
		lblAtualizar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAtualizar.setBounds(155, 11, 225, 22);
		contentPane.add(lblAtualizar);
		buscarSetores();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBuscar) {
			funcionarioDTO = funcionarioController.buscarFuncionario(textField_buscar.getText(), textField_buscar);
			if(!(funcionarioDTO == null)) {
				textField_nome.setText(funcionarioDTO.getNome());
				textField_email.setText(funcionarioDTO.getEmail());
				fTextField_data.setText(funcionarioDTO.getData());
				fTextField_cpf.setText(funcionarioDTO.getCpf());
				comboBoxSetor.setSelectedIndex(funcionarioDTO.getSetor());
			}
		}
		if(e.getSource() == btnSalvar) {
			setorId = comboBoxSetor.getSelectedIndex()+1;
			if(manipuladorDados.validarCPF(fTextField_cpf.getText(), fTextField_cpf) && 
					manipuladorDados.validarData(fTextField_data.getText(), fTextField_data) && 
					manipuladorDados.validarNome(textField_nome.getText(), textField_nome) &&
					manipuladorDados.validarEmail(textField_email.getText(), textField_email) == true) {
				System.out.println("linha - 171: Atualizando Funcionário..");
				stringParaData = manipuladorDados.getData();
				CPFParaString = manipuladorDados.getCpf();
				funcionarioController.atualizarFuncionario(textField_email.getText(),textField_nome.getText(), Date.valueOf(stringParaData), 
						CPFParaString, setorId, Integer.valueOf(textField_buscar.getText()),fTextField_cpf, textField_email);
			}
		}
	}

	private void buscarSetores() {	
		System.out.println("linha - 167: Setando Setores..");
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		comboBoxSetor.setModel(funcionarioController.buscarSetores(boxModel));	
	}

}
