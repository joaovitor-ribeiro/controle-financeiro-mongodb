package controlefinanceiro.tdd;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.validators.categoria.IniciaValidatorsCategoria;

public class CategoriaServiceTest {

	private IniciaValidatorsCategoria validator = new IniciaValidatorsCategoria();
    
    @Test
	public void cadastroCategoriaSemParametroNome()  {
		try {
			Categoria categoria= new Categoria();
			validator.inicia(categoria);
			fail("Nome da categoria é de preenchimento obrigatório. ");
		} catch (Exception e) {
			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
		}
	}
    
    @Test
    public void cadastroCategoriaParametroNomeVazio()  {
    	try {
    		Categoria categoria= new Categoria();
    		categoria.setNome("");
    		validator.inicia(categoria);
    		fail("Nome da categoria é de preenchimento obrigatório. ");
    	} catch (Exception e) {
    		assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
    	}
    }
    
    @Test
    public void cadastroCategoriaSemParametroTipo() {
 	   try {
 		   Categoria categoria = new Categoria();
 		   categoria.setNome("Faculdade");
 		   validator.inicia(categoria);
 		   fail("O Tipo do cadastro de categoria é de preenchimento obrigatório!");
 	   } catch (Exception e) {
 		   assertEquals("O campo tipo é de preenchimento obrigatório!", e.getMessage());
 	   }
    }
    
    @Test
    public void cadastroCategoriaParametroTipoVazio() {
 	   try {
 		   Categoria categoria = new Categoria();
 		   categoria.setNome("Faculdade");
 		   categoria.setTipo("");
 		   validator.inicia(categoria);
 		   fail("O Tipo do cadastro de categoria é de preenchimento obrigatório!");
 		   
 	   } catch (Exception e) {
 		   assertEquals("O campo tipo é de preenchimento obrigatório!", e.getMessage());
 	   }
    }
    
    @Test
    public void cadastroCategoriaValidaTamanhoMinimoNome() {
    	try {
			Categoria categoria= new Categoria();
			categoria.setNome("Te");
			categoria.setTipo("D");
			validator.inicia(categoria);
			fail("O Nome da categoria deve ter no minimo 3 caracteres!");
		} catch (Exception e) {
			assertEquals("O campo nome não pode ter menos do que 3 caracteres!", e.getMessage());
		}
    }
    
   @Test
   public void cadastroCategoriaValidaTamanhoMaximoNome() {
	   try {
		   Categoria categoria = new Categoria();
		   categoria.setNome("0123456789012345678912");
		   categoria.setTipo("D");
		   validator.inicia(categoria);
		   fail("O Nome da categoria não pode ter mais de 20 caractres!");
	   } catch (Exception e) {
		   assertEquals("O campo nome não pode ter mais do que 20 caracteres!", e.getMessage()); 
	   }
   }
   
   @Test
   public void cadastroCategoriaInformandoTipoComMaisCaracteres() {
	   try {
		   Categoria categoria = new Categoria();
		   categoria.setNome("Faculdade");
		   categoria.setTipo("Dia");
		   validator.inicia(categoria);
		   fail("Informar um caracter no campo Tipo!");
	   } catch (Exception e) {
		   assertEquals("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!", e.getMessage());
	   }
   }
   
   @Test
   public void cadastroCategoriaInformandoTipoInvalido() {
	   try {
		   Categoria categoria = new Categoria();
		   categoria.setNome("Faculdade");
		   categoria.setTipo("F");
		   validator.inicia(categoria);
		   fail("Informar D para despesa ou G para ganho!");
	   } catch (Exception e) {
		   assertEquals("Informe G para ganho ou D para despesa!", e.getMessage());
	   }
   }
}
