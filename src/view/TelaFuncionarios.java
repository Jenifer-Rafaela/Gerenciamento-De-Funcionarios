package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controller.FuncionarioController;

public class TelaFuncionarios extends JFrame implements ActionListener, WindowFocusListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	private JMenuItem mntmSair = new JMenuItem("Sair");
	private JButton btnCadastrar = new JButton("Cadastrar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnEditar = new JButton("Editar");

	private FuncionarioController funcionarioController = new FuncionarioController();

	public TelaFuncionarios() {
		addWindowFocusListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 491);
		setLocationRelativeTo(null);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		mntmSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSair.addActionListener(this);
		menuBar.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(113, 194, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(1, 1));
		scrollPane.setPreferredSize(new Dimension(1, 1));
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setBounds(22, 66, 849, 334);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setGridColor(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		table.setName("Funcionarios");
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

		
		btnCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCadastrar.setBorderPainted(false);
		btnCadastrar.setFocusable(false);
		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(59, 113, 202));
		btnCadastrar.addActionListener(this);
		btnCadastrar.setBounds(22, 21, 105, 23);
		contentPane.add(btnCadastrar);

		
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(this);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExcluir.setBorderPainted(false);
		btnExcluir.setFocusable(false);
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(220, 53, 69));
		btnExcluir.setBounds(252, 21, 105, 23);
		contentPane.add(btnExcluir);

		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(this);
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEditar.setBorderPainted(false);
		btnEditar.setFocusable(false);
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBackground(new Color(228, 161, 27));
		btnEditar.setBounds(137, 21, 105, 23);
		contentPane.add(btnEditar);

		mostrarFuncionarios();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmSair) {
			System.out.println("Botão sair foi selecionado..");
			System.exit(0);
		}
		if(e.getSource() == btnCadastrar) {
			System.out.println("Botão cadastrar foi selecionado..");
			TelaCadastrar telaCadastrar = new TelaCadastrar();
			telaCadastrar.setVisible(true);
		}
		if(e.getSource() == btnExcluir) {
			System.out.println("Botão excluir foi selecionado..");
			TelaExcluir telaExcluir = new TelaExcluir();
			telaExcluir.setVisible(true);
		}
		if(e.getSource() == btnEditar) {
			System.out.println("Botão editar foi selecionado..");
			TelaEditar telaEditar = new TelaEditar();
			telaEditar.setVisible(true);
		}
	}
	
	@Override
	public void windowGainedFocus(WindowEvent e) {
		atualizarTabela();
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
	}

	private void mostrarFuncionarios() {
		System.out.println("linha - 150: Setando funcionarios..");

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Código");
		model.addColumn("Nome");
		model.addColumn("Email");
		model.addColumn("Cpf");
		model.addColumn("Data de nascimento");
		model.addColumn("Setor");
		table.setModel(funcionarioController.buscarFuncionarios(model));
		columns();
	}

	public void atualizarTabela() {
		System.out.println("linha - 165: atualizando tabela..");
		mostrarFuncionarios();
		System.out.println("linha - 167: Tabela atualizada.");
	}

	private void columns() {
		// Cria um renderizador centralizado para as células
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		table.getColumnModel().getColumn(0).setPreferredWidth(13);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setPreferredWidth(18);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
		table.getColumnModel().getColumn(4).setResizable(false);
	}
}

