package model;
import java.sql.Connection;
import java.sql.DriverManager;

public class BD {
	
	private Connection con;
	
	//caminho para o driver
	private String driver = "com.mysql.cj.jdbc.Driver";
	//caminho para o banco de dados
	private String url = "jdbc:mysql://localhost:3306/gerencia";
	private String user = "jeni";
	private String password = "J1021";
	
	public Connection conectar() {
		try {
			//carrega a classe do driver JDBC correspondente ao banco de dados que será usado
			Class.forName(driver);
			//cria conexão com o banco de dados
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
