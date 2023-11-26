package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DTO.FuncionarioDTO;
import controller.FuncionarioController;
import service.ManipuladorDados;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class TelaExcluir extends JFrame implements ActionListener {
	private FuncionarioController funcionarioController = new FuncionarioController();
	private ManipuladorDados manipuladorDados = new ManipuladorDados();
	private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_nome;
	private JTextField textField_email;
	private JComboBox<String> comboBoxSetor = new JComboBox<String>();
	private JTextField textField_buscar;
	private JFormattedTextField fTextField_data;
	private JFormattedTextField fTextField_cpf;
	private JButton btnRemover = new JButton("Remover");
	private JButton btnBuscar = new JButton("Buscar");

	//	public static void main(String[] args) {
	//		TelaExcluir tela = new TelaExcluir();
	//		tela.setVisible(true);
	//	}

	public TelaExcluir() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRemover = new JLabel("Remover Funcionário");
		lblRemover.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRemover.setBounds(148, 11, 222, 26);
		contentPane.add(lblRemover);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(30, 106, 52, 14);
		contentPane.add(lblNome);

		textField_nome = new JTextField();
		textField_nome.setEditable(false);
		textField_nome.setColumns(10);
		textField_nome.setBounds(30, 122, 214, 31);
		contentPane.add(textField_nome);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(269, 106, 46, 14);
		contentPane.add(lblEmail);

		textField_email = new JTextField();
		textField_email.setEditable(false);
		textField_email.setColumns(10);
		textField_email.setBounds(269, 123, 214, 33);
		contentPane.add(textField_email);

		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCPF.setBounds(269, 164, 25, 14);
		contentPane.add(lblCPF);

		fTextField_cpf = manipuladorDados.formatarCpf(fTextField_cpf);
		fTextField_data = manipuladorDados.formatarData(fTextField_data);

		fTextField_cpf.setEditable(false);
		fTextField_cpf.setBounds(269, 180, 214, 31);
		contentPane.add(fTextField_cpf);

		JLabel lblDataDeNascimento = new JLabel("Data De Nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDeNascimento.setBounds(30, 164, 122, 14);
		contentPane.add(lblDataDeNascimento);


		fTextField_data.setEditable(false);
		fTextField_data.setBounds(30, 180, 214, 31);
		contentPane.add(fTextField_data);

		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSetor.setBounds(30, 222, 52, 14);
		contentPane.add(lblSetor);

		comboBoxSetor.setBounds(30, 244, 214, 31);
		comboBoxSetor.setEnabled(false);
		contentPane.add(comboBoxSetor);

		JLabel lblCodigo = new JLabel("Código Funcionário");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodigo.setBounds(30, 44, 108, 22);
		contentPane.add(lblCodigo);


		textField_buscar = new JTextField();
		textField_buscar.setColumns(10);
		textField_buscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_buscar.setColumns(10);
		textField_buscar.setBounds(30, 61, 89, 31);
		contentPane.add(textField_buscar);

		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(this);

		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setFocusable(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(new Color(59, 113, 202));
		btnBuscar.setBounds(129, 61, 89, 31);
		contentPane.add(btnBuscar);


		btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemover.addActionListener(this);

		btnRemover.setForeground(Color.WHITE);
		btnRemover.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRemover.setFocusable(false);
		btnRemover.setBorderPainted(false);
		btnRemover.setBackground(new Color(220, 53, 69));
		btnRemover.setBounds(175, 288, 163, 31);
		contentPane.add(btnRemover);

		buscarSetores();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRemover) {
			System.out.println("linha - 154: Removendo funcionario..");
			funcionarioController.removerFuncionario(textField_buscar.getText());      
			reset();
		}

		if(e.getSource() == btnBuscar) {
			System.out.println("linha - 160: Buscando Funcionário..");

			funcionarioDTO = funcionarioController.buscarFuncionario(textField_buscar.getText(), textField_buscar);

			if(!(funcionarioDTO == null)) {
				System.out.println("linha - 173: Funcionário encontrado.");

				textField_nome.setText(funcionarioDTO.getNome());
				textField_email.setText(funcionarioDTO.getEmail());
				fTextField_data.setText(funcionarioDTO.getData());
				fTextField_cpf.setText(funcionarioDTO.getCpf());
				comboBoxSetor.setSelectedIndex(funcionarioDTO.getSetor());
			}
		}
	}

	private void buscarSetores() {	
		System.out.println("linha - 194: Setando comboBox Setor..");
		DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<String>();
		comboBoxSetor.setModel(funcionarioController.buscarSetores(boxModel));	
	}

	private void reset() {
		textField_buscar.setText("");
		textField_email.setText("");
		textField_nome.setText("");
		comboBoxSetor.setSelectedIndex(0);
		fTextField_cpf.setText("");
		fTextField_data.setText("");
	}

}
