package controlefinanceiro.tdd;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.dto.cartao.CartaoSaida;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.service.CartaoService;
import controlefinanceiro.validators.cartao.IniciaValidatorsCartao;

@SpringBootTest
public class CartaoServiceTest {
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private IniciaValidatorsCartao validacao;
	
	private CartaoService service;
	
	@BeforeEach
    public void beforeEach() {
		this.service = new CartaoService(cartaoRepository, validacao);
	}

	@Test
	@DisplayName("Cartão cadastrado com sucesso!")
	public void cadastroCategoriaComSucesso()  {
		CartaoEntrada entrada = new CartaoEntrada("Nubank", "Mastercard", "5388708838533791", 50.00);
		Cartao        cartao  = new Cartao(1, entrada);

		Mockito.when(cartaoRepository.insert(Mockito.any(Cartao.class))).thenReturn(cartao);

		CartaoSaida saida = service.inserir(entrada);

		assertNotNull(saida);
		assertEquals(saida.id(), 1);
	}
	
	@Test
	@DisplayName("Cartão com número inválido de acordo com a banderia!")
	public void cadastroCartaoValidaNumeroCorrespondenteABandeira() {
		List<String> cartoesValidos = Arrays.asList("5388708838533791", "4532557096669138", "377157978157280", "30105527889423", "5388708838533791", "5037289724580589", "6062827885779171", "ABCDEFGHIJKLMNOP", "0037289724580589");
		List<String> bandeirasValidas = Arrays.asList("Hipercard", "Aura", "Diners Club", "American Express", "JCB", "Visa", "Mastercard", "Aura", "Aura" );
		
		for (int i = 0; i < cartoesValidos.size(); i++) {
			try {
				CartaoEntrada entrada = new CartaoEntrada("Nubank", bandeirasValidas.get(i), cartoesValidos.get(i), 10.00);
				service.inserir(entrada);
				fail("Não validou o Número do cartão de acordo com a Bandeira!");
			} catch (Exception e) {
				assertEquals("O número de cartão informado não corresponde com a bandeira!", e.getMessage());
			}
		}
	}
	
	@Test
	@DisplayName("Número de cartão inválido!")
	public void cadastroCartaoValidaNumero() {
		List<String> cartoesInvalidos = Arrays.asList("5547350794900010", "5400215600034433", "370014997122445", "6062820087088777", "3527434000509338", "30250310001001");
		List<String> bandeirasValidas = Arrays.asList("Mastercard", "Mastercard", "American Express", "Hipercard", "JCB", "Diners Club" );
		
		for (int i = 0; i < cartoesInvalidos.size(); i++) {
			try {
				CartaoEntrada entrada = new CartaoEntrada("Nubank", bandeirasValidas.get(i), cartoesInvalidos.get(i), 10.00);
				service.inserir(entrada);
				fail("Não validou o Número do cartão como inválido!");
			} catch (Exception e) {
				assertEquals("Número de cartão inválido!", e.getMessage());
			}
		}
	}
	
	
}
