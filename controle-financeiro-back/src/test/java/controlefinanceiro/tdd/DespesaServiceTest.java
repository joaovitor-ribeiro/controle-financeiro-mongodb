package controlefinanceiro.tdd;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.dto.despesa.DespesaEntrada;
import controlefinanceiro.dto.despesa.DespesaSaida;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.DespesaCustomRepository;
import controlefinanceiro.repository.DespesaRepository;
import controlefinanceiro.service.DespesaService;

@SpringBootTest
public class DespesaServiceTest {
	
	@Mock
	private CartaoRepository cartaoRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private DespesaRepository despesaRepository;
	
	@Mock
	private DespesaCustomRepository despesaCustomRepository;
	
	private DespesaService service;
	
	@BeforeEach
    public void beforeEach() {
		this.service = new DespesaService(despesaRepository, despesaCustomRepository, cartaoRepository, categoriaRepository);
	}
	
	@Test
	@DisplayName("Despesa cadastrada com sucesso!")
	public void despesaCadastradaComSucesso() {
		DespesaEntrada entrada = new DespesaEntrada(1, "Despesa", 1, 20.00, new Date());
		Despesa        despesa =  new Despesa(1, entrada, getCartao(), getCategoria());
		
		Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(getCategoria()));
		Mockito.when(cartaoRepository.findById(1)).thenReturn(Optional.of(getCartao()));
		Mockito.when(despesaRepository.insert( Mockito.any(Despesa.class) )).thenReturn(despesa);
		
		DespesaSaida saida = service.inserir(entrada);
		
		assertNotNull(saida);
		assertEquals(saida.id(), 1);
	}
	
	
	public Cartao getCartao() {
		CartaoEntrada entrada = new CartaoEntrada("Nubank", "Mastercard", "5388708838533791", 50.00);
		return new Cartao(1, entrada);
	}
	
	public Categoria getCategoria() {
		CategoriaEntrada entrada = new CategoriaEntrada("Categoria", "D");
    	return new Categoria(1, entrada);
	}
	
}
