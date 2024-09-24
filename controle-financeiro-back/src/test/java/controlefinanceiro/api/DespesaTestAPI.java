package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.dto.despesa.DespesaEntrada;
import io.restassured.http.ContentType;

public class DespesaTestAPI extends RestAssured {
	
	@BeforeAll
	public static void beforeAll() {
		basePath = "despesa";
	}
	
	@Test
	public void testInserirDespesa() {
		DespesaEntrada despesa = new DespesaEntrada(1, "Akademia", 1, 100.0, new Date());
		
		given()
			.contentType(ContentType.JSON)
			.body(despesa)
			.header("Authorization", getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarDespesa() {
		given()
			.header("Authorization", getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.extract().body().asString().contains("\"id\":1");
	}
	
	@Test
	public void testListarDespesaUm() {
		given()
			.header("Authorization", getToken())
		.when()
			.get("1")
		.then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("descricao", is("Conta de Luz"));
	}

}
