package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import controller.FuncionarioController;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
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
	private FuncionarioController funcionarioController = new FuncionarioController();
	private JComboBox<String> comboBoxSetor = new JComboBox<>();

	/**
	 * Create the frame.
	 */
	public TelaCadastrar() {
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

		formatar();

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
		if(validarCPF(fTextField_cpf.getText()) && 
				validarData(fTextField_data.getText()) && 
				validarNome(textField_nome.getText()) &&
				validarEmail(textField_email.getText()) == true) cadastrar();
	}

	private void buscarSetores() {	
		System.out.println("Setando Setores..");

		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		comboBoxSetor.setModel(funcionarioController.buscarSetores(boxModel));

	}

	private void cadastrar() {
		System.out.println("Cadastrando Funcionário..");
		String message = funcionarioController.cadastrarFuncionario(textField_email.getText(), textField_nome.getText(), Date.valueOf(stringParaData), CPFParaString, setorId);

		if(message.contains("CPF_UNIQUE")) {
			JOptionPane.showMessageDialog(this, "CPF informado já está em uso.", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_cpf.requestFocus();
		}
		if(message.contains("Email")) {
			JOptionPane.showMessageDialog(this, "Email informado já está em uso.", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_email.requestFocus();
		} 
		if(message.equals("reset")) {
			reset();
		}
	}

	private boolean validarData(String data) {
		System.out.println("linha - 155: Validando Data..");

		long dataMax = 75;
		long dataMin = 18;

		LocalDate validarData = LocalDate.now();

		if (data.replaceAll("[^0-9]", "").isEmpty() || data.replaceAll("[^0-9]", "").length() < 8) {
			System.out.println("linha - 163: Data não informada");
			
			JOptionPane.showMessageDialog(this, "O campo Data é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_data.requestFocus();
			return false;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			stringParaData = LocalDate.parse(data, formatter);

			if (stringParaData.isBefore(validarData.minusYears(dataMax))
					|| stringParaData.isAfter(validarData.minusYears(dataMin))) {
				System.out.println("linha - 175: Data inválida");
				
				JOptionPane.showMessageDialog(this, "Data informada é invalida.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				fTextField_data.requestFocus();
				return false;
			} else {
				System.out.println("linha - 182: Data válida");
				return true;
			}
		}
	}

	private boolean validarCPF(String cpf) {
		System.out.println("linha - 189: Validando cpf..");

		CPFParaString = cpf.replaceAll("[^0-9]", "");

		if (CPFParaString.isEmpty() || CPFParaString.length() < 11) {
			System.out.println("linha - 194: CPF inválido");
			JOptionPane.showMessageDialog(this, "O campo CPF é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_cpf.requestFocus();
			return false;
		} else {
			System.out.println("linha - 200: CPF válido");
			return true;
		}
	}

	private boolean validarNome(String nome) {
		System.out.println("linha - 206: Validando nome..");
		/*
		 * (?![0-9]*$): garante que a string não seja composta apenas por dígitos numéricos.
		 * [A-Za-zÀ-ÖØ-öø-ÿ\s']{3,}: Corresponde a pelo menos 3 letras (maiúsculas ou minúsculas), espaços em branco e apóstrofos 
		 * */
		String regex = "^(?![0-9]*$)[A-Za-zÀ-ÖØ-öø-ÿ\\s']{3,}$";
		System.out.println(nome);
		System.out.println(Pattern.matches(regex, nome));

		if (nome.isEmpty() || !(Pattern.matches(regex, nome))) {
			System.out.println("linha - 216: Nome inválido.");
			JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_nome.requestFocus();
			return false;
		} else {
			System.out.println("linha - 222: Nome válido.");
			return true;
		}
	}

	private boolean validarEmail(String email) {
		System.out.println("linha - 228: Validando email..");
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
			System.out.println("linha - 240: Email inválido");

			JOptionPane.showMessageDialog(this, "O campo Email é obrigatório!", "Campo Invalido",JOptionPane.ERROR_MESSAGE);
			textField_email.requestFocus();
			return false;
		} else { 
			System.out.println("linha - 246: Email válido");
			return true;
		}
	}

	//Formata campos de CPF e Data
	private void formatar() {
		try {
			System.out.println("linha - 254: Formatação sendo feita..");

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
			System.out.println("linha - 270: Erro na formatação..");
			e.printStackTrace();
		}
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




