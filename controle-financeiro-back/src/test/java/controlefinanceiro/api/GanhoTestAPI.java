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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GanhoTestAPI {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginApi.login();
	}
	
//	@Test
//	public void testInserirGanho() {
//		baseURI = "http://localhost";
//		port = 8080;
//		basePath = "ganho";
//		
//		Ganho ganho = new Ganho();
//		ganho.setCategoria_id(1);
//		ganho.setDescricao("Akademia");
//		ganho.setValor(100.0);
//		ganho.setData(new Date());
//		
//		given()
//			.contentType(ContentType.JSON)
//			.body(ganho)
//			.header("Authorization", LoginApi.getToken())
//		.when()
//			.post("inserir")
//		.then()
//			.statusCode(HttpStatus.SC_CREATED);
//	}
	
	@Test
	public void testListarGanho() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "ganho";
		
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
	public void testListarGanhoUm() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "ganho";
		
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
