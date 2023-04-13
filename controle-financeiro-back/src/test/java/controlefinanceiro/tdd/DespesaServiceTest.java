package controlefinanceiro.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.validators.despesa.IniciaValidatorsDespesa;

public class DespesaServiceTest {
	
	IniciaValidatorsDespesa validator = new IniciaValidatorsDespesa();
	
	@Test
	public void cadastroDespesaSemParametroDescricao() {
		try {
			Despesa despesa = new Despesa();
			validator.inicia(despesa);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaParametroDescricaoVazio() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("");
			validator.inicia(despesa);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaValidaTamanhoMinimoDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Fa");
			validator.inicia(despesa);
			fail("O tamanho da descrição não possui menos de 3 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaValidaTamanhoMaximoDescricao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdadepagamentomensal");
			validator.inicia(despesa);
			fail("O tamanho da descrição possui menos de 20 caracteres");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroValor() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			validator.inicia(despesa);
			fail("O valor é de preenchimento obrigatório!");
		} catch (Exception e) {
			assertEquals("O campo valor é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaValidaValorMaiorQueZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(-523.10);
			validator.inicia(despesa);
			fail("O valor informado não foi maior que zero!");
		} catch (Exception e) {
			assertEquals("O campo valor não pode ser menor ou igual a zero!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroData() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			validator.inicia(despesa);
			fail("A data não foi preenchida");
		} catch (Exception e) {
			assertEquals("O campo data é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroCartao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			validator.inicia(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroIDCartao() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			despesa.setCartao_id(null);
			validator.inicia(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaIDCartaoMenorIgualAZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			despesa.setCartao_id(-1);
			validator.inicia(despesa);
			fail("O cartão não foi preenchido!");
		} catch (Exception e) {
			assertEquals("O campo cartão é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroCategoria() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			despesa.setCartao_id(1);
			validator.inicia(despesa);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaSemParametroIDCategoria() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			despesa.setCartao_id(1);
			despesa.setCategoria_id(null);
			validator.inicia(despesa);
			fail("A categoria não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroDespesaIDCategoriaMenorIgualAZero() {
		try {
			Despesa despesa = new Despesa();
			despesa.setDescricao("Faculdade");
			despesa.setValor(523.10);
			despesa.setData(new Date());
			despesa.setCartao_id(1);
			despesa.setCategoria_id(-1);
			validator.inicia(despesa);
			fail("A categoria não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
}
