package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FuncionarioController;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

public class TelaLogin extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel loginPane;
	private JTextField textField_usuario;
	private JPasswordField passwordField_senha;
	
	private FuncionarioController funcionarioController = new FuncionarioController();

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 358);
		loginPane = new JPanel();
		loginPane.setBackground(new Color(255, 255, 255));
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(loginPane);
		loginPane.setLayout(null);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(59, 113, 202));
		btnNewButton.addActionListener(this);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(50, 234, 221, 37);
		loginPane.add(btnNewButton);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblLogin.setBounds(95, 11, 111, 50);
		loginPane.add(lblLogin);

		JLabel lblUsuário = new JLabel("Usuário");
		lblUsuário.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuário.setBounds(50, 72, 50, 20);
		loginPane.add(lblUsuário);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(50, 155, 50, 20);
		loginPane.add(lblSenha);

		textField_usuario = new JTextField();
		textField_usuario.setHorizontalAlignment(SwingConstants.LEFT);
		textField_usuario.setColumns(10);
		textField_usuario.setBounds(50, 91, 221, 37);
		loginPane.add(textField_usuario);

		passwordField_senha = new JPasswordField();
		passwordField_senha.setBounds(50, 173, 221, 37);
		loginPane.add(passwordField_senha);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("linha - 85: Fazendo o login..");
		if(funcionarioController.login(textField_usuario.getText(), String.valueOf(passwordField_senha.getPassword()))) {
			System.out.println("linha - 87: login correto.");
			TelaFuncionarios telaFuncionarios = new TelaFuncionarios();
			telaFuncionarios.setVisible(true);
			this.dispose();
		}
	}
}
