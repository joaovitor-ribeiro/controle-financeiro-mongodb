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

import controlefinanceiro.model.Cartao;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CartaoTestAPI {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginApi.login();
	}
	
	@Test
	public void testInserirCartao() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "cartao";
		
		Cartao cartao = new Cartao();
		cartao.setNome("Nubank Teste API");
		cartao.setBandeira("Mastercard");
		cartao.setNumero("5173863405996183");
		cartao.setLimite(120.00);
		
		given()
			.contentType(ContentType.JSON)
			.body(cartao)
			.header("Authorization", LoginApi.getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarCartao() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "cartao";
		
		Cartao cartao = new Cartao();
		cartao.setId(1);
		cartao.setNome("Teste");
		cartao.setBandeira("Mastercard");
		cartao.setNumero("5388708838533791");
		cartao.setLimite(105.59);
		
		given()
			.header("Authorization", LoginApi.getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.extract().body().jsonPath().getList(".", Cartao.class).contains(cartao);
	}
	
	@Test
	public void testListarCartaoUm() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "cartao";
		
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
