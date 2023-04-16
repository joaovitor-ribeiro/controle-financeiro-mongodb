package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Despesa;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DespesaTestAPI {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginApi.login();
	}
	
	@Test
	public void testInserirDespesa() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "despesa";
		
		Despesa despesa = new Despesa();
		despesa.setCategoria_id(1);
		despesa.setDescricao("Akademia");
		despesa.setCartao_id(1);
		despesa.setValor(100.0);
		despesa.setData(new Date());
		
		given()
			.contentType(ContentType.JSON)
			.body(despesa)
			.header("Authorization", LoginApi.getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarDespesa() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "despesa";
		
		given()
			.header("Authorization", LoginApi.getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.extract().body().asString().contains("\"id\":1");
	}
	
	@Test
	public void testListarDespesaUm() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "despesa";
		
		given()
			.header("Authorization", LoginApi.getToken())
		.when()
			.get("1")
		.then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("descricao", is("Teste"));
	}

}
