package controlefinanceiro.tdd;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.dto.categoria.CategoriaSaida;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.service.categoria.CategoriaService;
import controlefinanceiro.validators.categoria.IniciaValidatorsCategoria;
import jakarta.validation.ValidationException;

@SpringBootTest
public class CategoriaServiceTest {
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private IniciaValidatorsCategoria validacao;
	
	private CategoriaService service;
	
	@BeforeEach
    public void beforeEach() {
		this.service = new CategoriaService(categoriaRepository, validacao);
	}

	@Test
	@DisplayName("Inserir categoria com o tipo errado!")
	public void cadastroCategoriaTipoIncorreto()  {
		try {
			CategoriaEntrada categoria= new CategoriaEntrada("Categoria", "V");
			service.inserir(categoria);
			fail("O erro de tipo não foi lançado. ");
		} catch (ValidationException e) {
			assertEquals("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!", e.getMessage());
		}
	}
    
    @Test
    @DisplayName("Inserir categoria com sucesso!")
    public void cadastroCategoriaComSucesso()  {
    	CategoriaEntrada entrada = new CategoriaEntrada("Categoria", "D");
    	Categoria categoria      = new Categoria(1, entrada);
    	
    	Mockito.when(categoriaRepository.insert(Mockito.any(Categoria.class))).thenReturn(categoria);
    	
    	CategoriaSaida saida = service.inserir(entrada);
    	
    	assertNotNull(saida);
    	assertEquals(saida.id(), 1);
    }
    
    @Test
    @DisplayName("Categoria não encontrada!")
    public void categoriaNaoEncontrada()  {
    	CategoriaEntrada entrada = new CategoriaEntrada("Edit categ", "D");
    	
    	Mockito.when(categoriaRepository.findById(2)).thenThrow(new ValidationException("Categoria não encontrada!"));
    	
    	assertThrows(ValidationException.class, () -> service.editar(entrada, 2), "Categoria não encontrada!");
    }
    
    @Test
    @DisplayName("Editar categoria com sucesso!")
    public void editaCategoriaComSucesso()  {
    	CategoriaEntrada entrada = new CategoriaEntrada("Edit categ", "D");
    	Categoria categoria      = new Categoria(1, entrada);
    	
    	Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
    	Mockito.when(categoriaRepository.save(Mockito.any(Categoria.class))).thenReturn(categoria);
    	
    	CategoriaSaida saida = service.editar(entrada, 1);
    	
    	assertNotNull(saida);
    	assertEquals(saida.id(), 1);
    	assertEquals(saida.nome(), "Edit categ");
    	assertEquals(saida.tipo(), "D");
    }
    
    @Test
    @DisplayName("Retorna categoria pelo id com sucesso!")
    public void getCategoria()  {
    	CategoriaEntrada entrada = new CategoriaEntrada("Categoria", "D");
    	Categoria categoria      = new Categoria(1, entrada);
    	
    	Mockito.when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
    	
    	CategoriaSaida saida = service.retornarCategoriaId(1);
    	
    	assertNotNull(saida);
    	assertEquals(saida.id(), 1);
    	assertEquals(saida.nome(), "Categoria");
    	assertEquals(saida.tipo(), "D");
    }
    
}
