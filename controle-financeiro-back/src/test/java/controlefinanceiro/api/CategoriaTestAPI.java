package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Categoria;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CategoriaTestAPI {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginApi.login();
	}
	
	@Test
	public void testInserirCategoria() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "categoria";
		
		Categoria categoria = new Categoria();
		categoria.setNome("Akademia");
		categoria.setTipo("D");
		
		given()
			.contentType(ContentType.JSON)
			.body(categoria)
			.header("Authorization", LoginApi.getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarCategoria() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "categoria";
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		categoria.setNome("Teste");
		categoria.setTipo("G");
		
		given()
			.header("Authorization", LoginApi.getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.extract().body().jsonPath().getList(".", Categoria.class).contains(categoria);
	}
	
	@Test
	public void testListarCategoriaUm() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "categoria";
		
		given()
			.header("Authorization", LoginApi.getToken())
		.when()
			.get("1")
		.then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("nome", is("Teste"));
	}

}
