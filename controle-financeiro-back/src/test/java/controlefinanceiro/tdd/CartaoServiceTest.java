package controlefinanceiro.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.validators.cartao.IniciaValidatorsCartao;

public class CartaoServiceTest {
	
	private IniciaValidatorsCartao validator = new IniciaValidatorsCartao();
	
	@Test
	public void cadastroCartaoSemParametroNome() {
		try {
			Cartao cartao = new Cartao();
			validator.inicia(cartao);
			fail("Não validou a obrigatoriedade no preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoParametroNomeVazio() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("");
			validator.inicia(cartao);
			fail("Não validou a obrigatoriedade no preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMinimoNome() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Ma");
			validator.inicia(cartao);
			fail("Não validou o tamanho minimo para o preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMaximoNome() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("MastercardCieloNubank");
			validator.inicia(cartao);
			fail("Não validou o tamanho maximo para o preenchimento do Nome!");
		} catch (Exception e) {
			assertEquals("O campo nome não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoSemParametroBandeira() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			validator.inicia(cartao);
			fail("Não validou a obrigatoriedade no preenchimento da Bandeira!");
			
		} catch (Exception e) {
			assertEquals("O campo bandeira é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoParametroBandeiraVazio() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("");
			validator.inicia(cartao);
			fail("Não validou a obrigatoriedade no preenchimento da Bandeira!");
			
		} catch (Exception e) {
			assertEquals("O campo bandeira é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMinimoBandeira() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("Ma");
			validator.inicia(cartao);
			fail("Não validou o tamanho minimo para o preenchimento da Bandeira!");
		} catch (Exception e) {
			assertEquals("O campo bandeira não pode ter menos do que 3 caracteres!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaTamanhoMaximoBandeira() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("MastercardNubankCielo");
			validator.inicia(cartao);
			fail("Não validou o tamanho maximo para o preenchimento da Bandeira!");
		} catch (Exception e) {
			assertEquals("O campo bandeira não pode ter mais do que 20 caracteres!", e.getMessage());
		}
	}
	
	
	@Test
	public void cadastroCartaoSemParametroNumero() {
		//try - tentando executar as linhas dentro do seu bloco
		try {
			Cartao cartao = new Cartao();
			//set - definir
			cartao.setNome("Nubank");
			cartao.setBandeira("Mastercard");
			cartao.setLimite(10.00);
			validator.inicia(cartao);
			//fail - falhou
			fail("Não validou a obrigatoriedade no preenchimento do Número!");
			//catch - pegar
		} catch (Exception e) {
			assertEquals("O campo número é de preenchimento obrigatório!", e.getMessage());
		}
	} 
	
	@Test
	public void cadastroCartaoParametroNumeroVazio() {
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("Mastercard"); 
			cartao.setLimite(10.00); 
			cartao.setNumero(""); 
			validator.inicia(cartao);
			fail("Não validou a obrigatoriedade no preenchimento do Número!");
		} catch (Exception e) {
			assertEquals("O campo número é de preenchimento obrigatório!", e.getMessage());
		}
	} 
	
	@Test
	public void cadastroCartaoSemParametroLimite(){
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("Mastercard");
			cartao.setNumero("5388708838533791");
			validator.inicia(cartao);
			fail("Não validou o Limite do cartão!");
		} catch (Exception e) {
			assertEquals("O campo limite é de preenchimento obrigatório!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaLimiteMaiorQueZero(){
		try {
			Cartao cartao = new Cartao();
			cartao.setNome("Nubank");
			cartao.setBandeira("Mastercard");
			cartao.setNumero("5388708838533791");
			cartao.setLimite(0.00);
			validator.inicia(cartao);
			fail("Não validou o Limite do cartão!");
		} catch (Exception e) {
			assertEquals("O campo limite não pode ser menor ou igual a zero!", e.getMessage());
		}
	}
	
	@Test
	public void cadastroCartaoValidaNumeroCorrespondenteABandeira() {
		List<String> cartoesValidos = Arrays.asList("5388708838533791", "4532557096669138", "377157978157280", "30105527889423", "5388708838533791", "5037289724580589", "6062827885779171", "ABCDEFGHIJKLMNOP", "0037289724580589");
		List<String> bandeirasValidas = Arrays.asList("Hipercard", "Aura", "Diners Club", "American Express", "JCB", "Visa", "Mastercard", "Aura", "Aura" );
		
		for (int i = 0; i < cartoesValidos.size(); i++) {
			try {
				Cartao cartao = new Cartao();
				cartao.setNome("Nubank");
				cartao.setBandeira(bandeirasValidas.get(i));
				cartao.setLimite(10.00);
				cartao.setNumero(cartoesValidos.get(i));
				validator.inicia(cartao);
				fail("Não validou o Número do cartão de acordo com a Bandeira!");
			} catch (Exception e) {
				assertEquals("O número de cartão informado não corresponde com a bandeira!", e.getMessage());
			}
		}
	}
	
	@Test
	public void cadastroCartaoValidaNumero() {
		List<String> cartoesInvalidos = Arrays.asList("513456789012", "51345678901234567", "5547350794900010", "5400215600034433", "370014997122445", "6062820087088777", "3527434000509338", "30250310001001");
		List<String> bandeirasValidas = Arrays.asList("Mastercard", "Mastercard", "Mastercard", "Mastercard", "American Express", "Hipercard", "JCB", "Diners Club" );
		
		for (int i = 0; i < cartoesInvalidos.size(); i++) {
			try {
				Cartao cartao = new Cartao();
				cartao.setNome("Nubank");
				cartao.setBandeira(bandeirasValidas.get(i));
				cartao.setLimite(10.00);
				cartao.setNumero(cartoesInvalidos.get(i));
				validator.inicia(cartao);
				fail("Não validou o Número do cartão como inválido!");
			} catch (Exception e) {
				assertEquals("Número de cartão inválido!", e.getMessage());
			}
		}
	}

	
}
