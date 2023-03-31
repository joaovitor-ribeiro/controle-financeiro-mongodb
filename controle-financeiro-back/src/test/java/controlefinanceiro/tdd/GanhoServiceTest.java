package controlefinanceiro.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Ganho;
import controlefinanceiro.validators.ganho.IniciaValidatorsGanho;

public class GanhoServiceTest {
	
	private IniciaValidatorsGanho validator = new IniciaValidatorsGanho();
	
	@Test
	public void cadastroGanhoSemParametroDescricao() {
		try {
			Ganho ganho = new Ganho();
			validator.inicia(ganho);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoParametroDescricaoVazio() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("");
			validator.inicia(ganho);
			fail("A Descrição não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo descrição é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaTamanhoMinimoDescricao() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Fa");
			validator.inicia(ganho);
			fail("O tamanho da descrição não possui menos de 3 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaTamanhoMaximoDescricao() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faaaaaaaaaaaaaaaaaaaaa");
			validator.inicia(ganho);
			fail("O tamanho da descrição possui menos de 20 caracteres.");
		} catch (Exception e) {
			assertEquals("O campo descrição não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoCategoriaMenorIgualAZero() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setValor(10.1);
			ganho.setData(new Date());
			ganho.setCategoria_id(-1);
			validator.inicia(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroValor() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria_id(1);			
			validator.inicia(ganho);
			fail("O valor é de preenchimento obrigatório!");
		} catch (Exception e) {
			assertEquals("O campo valor é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoValidaValorMaiorQueZero() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria_id(1);
			ganho.setValor(-10.1);
			validator.inicia(ganho);
			fail("O valor informado não foi maior que zero!");
		} catch (Exception e) {
			assertEquals("O campo valor não pode ser menor ou igual a zero!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroData() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setCategoria_id(1);
			ganho.setValor(10.1);			
			validator.inicia(ganho);
			fail("A data não foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo data é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroCategoria() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setValor(10.1);
			ganho.setData(new Date());
			validator.inicia(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroGanhoSemParametroIDCategoria() {
		try {
			Ganho ganho = new Ganho();
			ganho.setDescricao("Faculdade");
			ganho.setValor(10.1);
			ganho.setData(new Date());
			validator.inicia(ganho);
			fail("A categoria foi preenchida!");
		} catch (Exception e) {
			assertEquals("O campo categoria é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
}
