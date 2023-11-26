package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import DTO.FuncionarioDTO;
import controller.FuncionarioController;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
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
	private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
	private JComboBox<String> comboBoxSetor = new JComboBox<String>();

//	public static void main(String[] args) {
//		TelaEditar tela = new TelaEditar();
//		tela.setVisible(true);
//	}

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

		JLabel lblDataDeNascimento = new JLabel("Data De Nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeNascimento.setBounds(30, 164, 122, 14);
		contentPane.add(lblDataDeNascimento);

		formatar();

		fTextField_data.setBounds(30, 180, 214, 31);
		contentPane.add(fTextField_data);

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
			buscarFuncionario(textField_buscar.getText());
		}
		if(e.getSource() == btnSalvar) {
			setorId = comboBoxSetor.getSelectedIndex()+1;
			if(validarCPF(fTextField_cpf.getText()) && 
					validarData(fTextField_data.getText()) && 
					validarNome(textField_nome.getText()) &&
					validarEmail(textField_email.getText()) == true) { 
				atualizar();
			}
		}
	}

	private void buscarSetores() {	
		System.out.println("linha - 167: Setando Setores..");
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		comboBoxSetor.setModel(funcionarioController.buscarSetores(boxModel));	
	}

	private void buscarFuncionario(String idFuncionario) {
		System.out.println("linha - 173: Buscando Funcionario..");
		if(idFuncionario.matches("^\\d+$")) {
			funcionarioDTO = funcionarioController.buscarFuncionario(idFuncionario);

			if(funcionarioDTO == null) {
				System.out.println("linha - 178: Funcionario não encontrado.");
				
				JOptionPane.showMessageDialog(this, "Funcionario com código "+textField_buscar.getText()+" não foi encontrado.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				textField_buscar.requestFocus();
			} else {
				System.out.println("linha - 184: Funcionario encontrado.");
				
				textField_nome.setText(funcionarioDTO.getNome());
				textField_email.setText(funcionarioDTO.getEmail());
				fTextField_data.setText(funcionarioDTO.getData());
				fTextField_cpf.setText(funcionarioDTO.getCpf());
				comboBoxSetor.setSelectedIndex(funcionarioDTO.getSetor());
			}

		} else {
			System.out.println("linha - 194: Código do funcionário é inválido.");
			
			JOptionPane.showMessageDialog(this, "O código do funcionario deve ser numérico!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_buscar.requestFocus();
			System.out.println(textField_buscar.getText());
		}
	}

	private void atualizar() {
		System.out.println("linha - 204: Atualizando Funcionário..");
		String message = funcionarioController.atualizarFuncionario(textField_email.getText(), textField_nome.getText(), Date.valueOf(stringParaData), CPFParaString, setorId, Integer.valueOf(textField_buscar.getText()));

		if(message.contains("CPF_UNIQUE")) {
			System.out.println("linha - 208: Cpf já está em uso.");
			JOptionPane.showMessageDialog(null, "CPF informado já está em uso.", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_cpf.requestFocus();
		} else if(message.contains("Email")) {
			System.out.println("linha - 213: Email já está em uso.");
			JOptionPane.showMessageDialog(null, "Email informado já está em uso.", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_email.requestFocus();
		} else System.out.println(message);
	}

	//Formata campos de CPF e Data
	private void formatar() {
		try {
			System.out.println("linha - 223: Formatação sendo feita..");

			MaskFormatter formatoCpf = new MaskFormatter("###.###.###-##");
			formatoCpf.setPlaceholderCharacter('_');
			JFormattedTextField jfTextField_cpf = new JFormattedTextField( formatoCpf );
			jfTextField_cpf.setFocusLostBehavior( JFormattedTextField.COMMIT);

			MaskFormatter formatoData = new MaskFormatter("##-##-####");
			formatoData.setPlaceholderCharacter('_');
			JFormattedTextField jfTextField_data = new JFormattedTextField( formatoData );
			jfTextField_data.setFocusLostBehavior( JFormattedTextField.COMMIT);

			fTextField_cpf = jfTextField_cpf;
			fTextField_data = jfTextField_data;

		} catch (ParseException e) {
			System.out.println("linha - 239: Erro na formatação..");
			System.out.println(e);
		}
	}

	private boolean validarData(String data) {
		System.out.println("linha - 245: Validando data..");

		long dataMax = 75;
		long dataMin = 18;

		LocalDate validarData = LocalDate.now();

		if (data.replaceAll("[^0-9]", "").isEmpty() || data.replaceAll("[^0-9]", "").length() < 8) {
			System.out.println("linha - 253: Data não informada");
			
			JOptionPane.showMessageDialog(this, "O campo Data é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_data.requestFocus();
			return false;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			stringParaData = LocalDate.parse(data, formatter);

			if (stringParaData.isBefore(validarData.minusYears(dataMax))
					|| stringParaData.isAfter(validarData.minusYears(dataMin))) {
				System.out.println("linha - 265: Data inválida");
				
				JOptionPane.showMessageDialog(this, "Data informada é invalida.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				fTextField_data.requestFocus();
				return false;
			} else {
				System.out.println("linha - 272: Data válida");
				return true;
			}
		}
	}

	private boolean validarCPF(String cpf) {
		System.out.println("linha - 279: Validando cpf..");

		CPFParaString = cpf.replaceAll("[^0-9]", "");

		if (CPFParaString.isEmpty() || CPFParaString.length() < 11) {
			System.out.println("linha - 284: CPF inválido");
			JOptionPane.showMessageDialog(this, "O campo CPF é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_cpf.requestFocus();
			return false;
		} else {
			System.out.println("linha - 290: CPF válido");
			return true;
		}
	}

	private boolean validarEmail(String email) {
		System.out.println("linha - 296: Validando email..");
		/*
		^ - usado no começo de um regex
		[A-Za-z0-9+_.-] - aceita letras maiusculas ou minusculas, numero e +_.- 
		+ - aceita mais de uma ocorrencia dos anteriores
		@ - é obrigatório ter no email
		(.+) aceita qualquer (.) caracter (+) uma ou mais vezes
		$ - usado no final de um regex
		 */
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

		if (email.isEmpty() || !(Pattern.matches(regex, email))) {
			System.out.println("linha - 308: Email inválido");

			JOptionPane.showMessageDialog(this, "O campo Email é obrigatório!", "Campo Invalido",JOptionPane.ERROR_MESSAGE);
			textField_email.requestFocus();
			return false;
		} else { 
			System.out.println("linha - 314: Email válido");
			return true;
		}

	}

	private boolean validarNome(String nome) {
		System.out.println("linha - 321: Validando nome..");
		/*
		 * (?![0-9]*$): garante que a string não seja composta apenas por dígitos numéricos.
		 * [A-Za-zÀ-ÖØ-öø-ÿ\s']{3,}: Corresponde a pelo menos 3 letras (maiúsculas ou minúsculas), espaços em branco e apóstrofos 
		 * */
		String regex = "^(?![0-9]*$)[A-Za-zÀ-ÖØ-öø-ÿ\\s']{3,}$";
		System.out.println(nome);
		System.out.println(Pattern.matches(regex, nome));

		if (nome.isEmpty() || !(Pattern.matches(regex, nome))) {
			System.out.println("linha - 331: Nome inválido.");
			JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_nome.requestFocus();
			return false;
		} else {
			System.out.println("linha - 337: Nome válido.");
			return true;
		}
	}
}
