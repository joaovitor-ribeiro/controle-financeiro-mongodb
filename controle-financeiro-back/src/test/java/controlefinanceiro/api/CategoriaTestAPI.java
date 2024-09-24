package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;

import java.util.Collection;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.model.Categoria;
import io.restassured.http.ContentType;

public class CategoriaTestAPI extends RestAssured {
	
	@BeforeAll
	public static void beforeAll() {
		basePath = "categoria";
	}
	
	@Test
	public void testInserirCategoria() {
		CategoriaEntrada categoria = new CategoriaEntrada("Aula", "G");

		given()
			.contentType(ContentType.JSON)
			.body(categoria)
			.header("Authorization", getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setNome("Aula");
		categoria.setTipo("G");
		
		given()
			.header("Authorization", getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.body(".", any(Collection.class))
			.extract().body().jsonPath().getList(".", Categoria.class);
		
	}
	
	@Test
	public void testListarCategoriaUm() {
		given()
			.header("Authorization", getToken())
		.when()
			.get("1")
		.then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("nome", is("Academia"));
	}

}
