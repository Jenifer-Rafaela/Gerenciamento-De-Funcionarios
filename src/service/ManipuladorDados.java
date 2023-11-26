package service;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class ManipuladorDados {
	
	private LocalDate data;
	private String cpf;
	
	public ManipuladorDados() {}

	//Formata campo de CPF
	public JFormattedTextField formatarCpf(JFormattedTextField fTextField_cpf) {
		System.out.println("linha - 32: CPF sendo formatado..");
		try {
			MaskFormatter formatoCpf = new MaskFormatter("###.###.###-##");
			formatoCpf.setPlaceholderCharacter('_');
			JFormattedTextField jfTextField_cpf = new JFormattedTextField( formatoCpf );
			jfTextField_cpf.setFocusLostBehavior( JFormattedTextField.COMMIT);

			System.out.println("linha - 39: CPF formatado.");
			return fTextField_cpf = jfTextField_cpf;

		} catch (ParseException e) {
			System.out.println("linha - 43: Erro na formatação do CPF..");
			System.out.println(e);
			return null;
		}
	}

	//Formata campo de Data
	public JFormattedTextField formatarData(JFormattedTextField fTextField_data) {
		System.out.println("linha - 51: Data sendo formatada..");
		try {
			MaskFormatter formatoData = new MaskFormatter("##-##-####");
			formatoData.setPlaceholderCharacter('_');
			JFormattedTextField jfTextField_data = new JFormattedTextField( formatoData );
			jfTextField_data.setFocusLostBehavior( JFormattedTextField.COMMIT);

			System.out.println("linha - 58: Data formatada.");
			return fTextField_data = jfTextField_data;

		} catch (ParseException e) {
			System.out.println("linha - 62: Erro na formatação da Data..");
			System.out.println(e);
			return null;
		}
	}

	public boolean validarData(String data,JFormattedTextField fTextField_data) {
		System.out.println("linha - 56: Validando data..");

		long dataMax = 75;
		long dataMin = 18;

		LocalDate validarData = LocalDate.now();

		if (data.replaceAll("[^0-9]", "").isEmpty() || data.replaceAll("[^0-9]", "").length() < 8) {
			System.out.println("linha - 64: Data não informada");
			
			JOptionPane.showMessageDialog(null, "O campo Data é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			
			fTextField_data.requestFocus();
			return false;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			this.data = LocalDate.parse(data, formatter);

			if (this.data.isBefore(validarData.minusYears(dataMax))
					|| this.data.isAfter(validarData.minusYears(dataMin))) {
				System.out.println("linha - 77: Data inválida");
				
				JOptionPane.showMessageDialog(null, "Data informada é invalida.", "Campo Invalido",
						JOptionPane.ERROR_MESSAGE);
				
				fTextField_data.requestFocus();
				return false;
			} else {
				System.out.println("linha - 85: Data válida");
				return true;
			}
		}
	}

	
	public boolean validarCPF(String cpf, JFormattedTextField fTextField_cpf) {
		System.out.println("linha - 93: Validando cpf..");

		this.cpf = cpf.replaceAll("[^0-9]", "");

		if (this.cpf.isEmpty() || this.cpf.length() < 11) {
			System.out.println("linha - 98: CPF inválido");
			JOptionPane.showMessageDialog(null, "O campo CPF é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			fTextField_cpf.requestFocus();
			return false;
		} else {
			System.out.println("linha - 104: CPF válido");
			return true;
		}
	}
	
	public boolean validarEmail(String email, JTextField textField_email) {
		System.out.println("linha - 110: Validando email..");
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
			System.out.println("linha - 122: Email inválido");

			JOptionPane.showMessageDialog(null, "O campo Email é obrigatório!", "Campo Invalido",JOptionPane.ERROR_MESSAGE);
			textField_email.requestFocus();
			return false;
		} else { 
			System.out.println("linha - 128: Email válido");
			return true;
		}
	}
	
	public boolean validarNome(String nome, JTextField textField_nome) {
		System.out.println("linha - 134: Validando nome..");
		/*
		 * (?![0-9]*$): garante que a string não seja composta apenas por dígitos numéricos.
		 * [A-Za-zÀ-ÖØ-öø-ÿ\s']{3,}: Corresponde a pelo menos 3 letras (maiúsculas ou minúsculas), espaços em branco e apóstrofos 
		 * */
		String regex = "^(?![0-9]*$)[A-Za-zÀ-ÖØ-öø-ÿ\\s']{3,}$";

		if (nome.isEmpty() || !(Pattern.matches(regex, nome))) {
			System.out.println("linha - 142: Nome inválido.");
			JOptionPane.showMessageDialog(null, "O campo Nome é obrigatório!", "Campo Invalido",
					JOptionPane.ERROR_MESSAGE);
			textField_nome.requestFocus();
			return false;
		} else {
			System.out.println("linha - 148: Nome válido.");
			return true;
		}
	}
	
	public String dataFormatada(String data) {
		System.out.println("linha - 167: Formatando a data para exibição..");
		String[] dataInteira = data.split("-");
		return dataInteira[2]+"-"+dataInteira[1]+"-"+dataInteira[0];
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public String getCpf() {
		return cpf;
	}


}
