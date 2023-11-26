package DTO;

public class FuncionarioDTO {
	
	private String nome;
	private String email;
	private String cpf;
	private String data;
	private int setor;
	
	public FuncionarioDTO(){}	
	
	public FuncionarioDTO(String nome, String email, String cpf, String data, int setor){
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.data = dataFormatada(data);
		this.setor = setor-1;
	}	
	
	public FuncionarioDTO(String data){
		this.data = dataFormatada(data);
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getSetor() {
		return setor;
	}

	public void setSetor(int setor) {
		this.setor = setor;
	}

	@Override
	public String toString() {
		return "DTO [nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", data=" + data + ", setor=" + setor + "]";
	}
	
	private String dataFormatada(String data) {
		System.out.println("Formatando a data para exibição..");

		String[] dataInteira = data.split("-");
		return dataInteira[2]+"-"+dataInteira[1]+"-"+dataInteira[0];
	}

}
