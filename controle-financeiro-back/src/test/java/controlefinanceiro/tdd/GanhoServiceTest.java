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

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.dto.ganho.GanhoEntrada;
import controlefinanceiro.dto.ganho.GanhoSaida;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.GanhoCustomRepository;
import controlefinanceiro.repository.GanhoRepository;
import controlefinanceiro.service.GanhoService;

public class GanhoServiceTest {
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private GanhoRepository ganhoRepository;
	
	@Mock
	private GanhoCustomRepository ganhoCustomRepository;
	
	private GanhoService service;
	
	@BeforeEach
    public void beforeEach() {
		this.service = new GanhoService(ganhoRepository, ganhoCustomRepository, categoriaRepository);
	}
	
	@Test
	@DisplayName("Despesa cadastrada com sucesso!")
	public void despesaCadastradaComSucesso() {
		GanhoEntrada entrada = new GanhoEntrada("Ganho", 1, 20.00, new Date());
		Ganho        ganho   =  new Ganho(1, entrada, getCategoria());
		
		Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(getCategoria()));
		Mockito.when(ganhoRepository.insert( Mockito.any(Ganho.class) )).thenReturn(ganho);
		
		GanhoSaida saida = service.inserir(entrada);
		
		assertNotNull(saida);
		assertEquals(saida.id(), 1);
	}
	
	
	
	public Categoria getCategoria() {
		CategoriaEntrada entrada = new CategoriaEntrada("Categoria", "D");
    	return new Categoria(1, entrada);
	}
	
}
