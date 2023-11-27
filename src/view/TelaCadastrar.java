package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.FuncionarioController;
import service.ManipuladorDados;

import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class TelaCadastrar extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nome;
	private JTextField textField_email;
	private JFormattedTextField fTextField_cpf;
	private JFormattedTextField fTextField_data;
	private LocalDate stringParaData;
	private String CPFParaString;
	private int setorId;
	private FuncionarioController funcionarioController = new FuncionarioController();;
	private ManipuladorDados manipuladorDados = new ManipuladorDados();
	private JComboBox<String> comboBoxSetor = new JComboBox<>();

	/**
	 * Create the frame.
	 */
	public TelaCadastrar() {
		setTitle("Dev Company Enterprise");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastrar = new JLabel("Cadastrar Funcionário");
		lblCadastrar.setBounds(138, 11, 234, 26);
		lblCadastrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCadastrar);

		textField_nome = new JTextField();
		textField_nome.setBounds(31, 84, 214, 33);
		contentPane.add(textField_nome);
		textField_nome.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(31, 68, 52, 14);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(268, 68, 46, 14);
		contentPane.add(lblEmail);

		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(268, 84, 214, 33);
		contentPane.add(textField_email);

		JLabel lblDataDeNascimento = new JLabel("Data De Nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeNascimento.setBounds(31, 135, 122, 14);
		contentPane.add(lblDataDeNascimento);

		fTextField_cpf = manipuladorDados.formatarCpf(fTextField_cpf);
		fTextField_data = manipuladorDados.formatarData(fTextField_data);

		fTextField_cpf.setBounds(268, 152, 214, 33);
		contentPane.add(fTextField_cpf);

		fTextField_data.setBounds(31, 151, 214, 33);
		contentPane.add(fTextField_data);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCpf.setBounds(268, 135, 25, 14);
		contentPane.add(lblCpf);

		comboBoxSetor.setBounds(31, 217, 214, 33);
		contentPane.add(comboBoxSetor);

		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetor.setBounds(31, 200, 36, 14);
		contentPane.add(lblSetor);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalvar.addActionListener(this);
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setFocusable(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBackground(new Color(59, 113, 202));
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalvar.setBounds(175, 288, 163, 31);
		contentPane.add(btnSalvar);
		buscarSetores();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setorId = comboBoxSetor.getSelectedIndex()+1;
		if(manipuladorDados.validarCPF(fTextField_cpf.getText(), fTextField_cpf) && 
				manipuladorDados.validarData(fTextField_data.getText(), fTextField_data) && 
				manipuladorDados.validarNome(textField_nome.getText(), textField_nome) &&
				manipuladorDados.validarEmail(textField_email.getText(), textField_email) == true) {
			System.out.println("linha - 127: Cadastrando Funcionário..");
			stringParaData = manipuladorDados.getData();
			CPFParaString = manipuladorDados.getCpf();
			int resultado = funcionarioController.cadastrarFuncionario(textField_email.getText(), textField_nome.getText(), Date.valueOf(stringParaData), CPFParaString, setorId, fTextField_cpf, textField_email);
			if(resultado == 1) reset();
		}
	}


	private void buscarSetores() {	
		System.out.println("linha - 137: Buscando Setores..");
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		comboBoxSetor.setModel(funcionarioController.buscarSetores(boxModel));
	}

	private void reset() {
		textField_email.setText("");
		textField_nome.setText("");
		fTextField_data.setText("");
		fTextField_cpf.setText("");
		comboBoxSetor.setSelectedIndex(0);
		fTextField_data.setText("");
	}
}




