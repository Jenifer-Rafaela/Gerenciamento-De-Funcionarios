package service;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFormattedTextField;

import org.junit.jupiter.api.Test;

class ManipuladorDadosTest {

	ManipuladorDados manipuladorDados = new ManipuladorDados();
	JFormattedTextField fTextField_cpf = new JFormattedTextField();
	JFormattedTextField fTextField_data = new JFormattedTextField();
	
	@Test
	void testaMetodoFormatarCpf() {
		assertNotNull(manipuladorDados.formatarCpf(fTextField_cpf));
	}

	@Test
	void testaMetodoFormatarData() {
		assertNotNull(manipuladorDados.formatarData(fTextField_data));
	}

	@Test
	void testaMetodoValidarDataQuandoDataNaoEInformada() {
		assertFalse(manipuladorDados.validarData("",fTextField_data));
	}

	@Test
	void testaMetodoValidarDataQuandoDataNaoEValida() {
		assertFalse(manipuladorDados.validarData("13-05-200",fTextField_data));
	}

	@Test
	void testaMetodoValidarDataQuandoDataEMaiorQueAPermitida() {
		assertFalse(manipuladorDados.validarData("13-05-1948",fTextField_data));
	}
	
	@Test
	void testaMetodoValidarDataQuandoDataEMenorQueAPermitida() {
		assertFalse(manipuladorDados.validarData("13-05-2008",fTextField_data));
	}
	
	@Test
	void testaMetodoValidarDataQuandoDataEValida() {
		assertTrue(manipuladorDados.validarData("13-05-2000",fTextField_data));
	}

	@Test
	void testaMetodoValidarCPFQuandoCpfNaoEInformado() {
		assertFalse(manipuladorDados.validarCPF("",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarCPFQuandoCpfNaoEValido() {
		assertFalse(manipuladorDados.validarCPF("123456789",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarCPFQuandoCpfEValido() {
		assertTrue(manipuladorDados.validarCPF("12345678910",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarEmailQuandoEmailEInvalido() {
		assertFalse(manipuladorDados.validarEmail("jenifer",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarEmailQuandoEmailNaoEInformado() {
		assertFalse(manipuladorDados.validarEmail("",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarEmailQuandoEmailEValido() {
		assertTrue(manipuladorDados.validarEmail("jenifer@gmail.com",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarNomeQuandoNomeEInvalido() {
		assertFalse(manipuladorDados.validarNome("je",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarNomeQuandoNomeNaoEInformado() {
		assertFalse(manipuladorDados.validarNome("",fTextField_cpf));
	}

	@Test
	void testaMetodoValidarNomeQuandoNomeEValido() {
		assertTrue(manipuladorDados.validarNome("jenifer",fTextField_cpf));
	}

	@Test
	void testaMetodoFormatarDataParaDiaMesEAno() {
		assertEquals("13-05-2000",manipuladorDados.dataFormatada("2000-05-13"));
	}

}
