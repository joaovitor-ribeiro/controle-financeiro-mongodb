package controlefinanceiro.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.bean.LoginBean;
import controlefinanceiro.bean.TokenBean;
import controlefinanceiro.model.Despesa;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class DespesaTestAPI {
	
	private static String token;
	private final static String baseUri = "http://localhost:8080/";
	private final static String basePath = "despesa/";
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginBean usuario = new LoginBean("jaibinho@email.com", "123456");
		Response response = given()
								.contentType(ContentType.JSON)
								.body(usuario).
						    when()
						    	.baseUri(baseUri)
								.post("autenticacao");
		
		ResponseBody<?> body = response.getBody();
		TokenBean tokenBean = body.as(TokenBean.class);
		token = tokenBean.getToken();
	}
	
	@Test
	public void testInserirDespesa() {
		Despesa despesa = new Despesa();
		despesa.setCategoria_id(1);
		despesa.setDescricao("Akademia");
		despesa.setCartao_id(1);
		despesa.setValor(100.0);
		despesa.setData(new Date());
		
		given()
			.contentType(ContentType.JSON)
			.body(despesa)
			.header("Authorization", "Bearer " + token).
		when()
			.baseUri(baseUri)
			.basePath(basePath)
			.post("inserir").
		then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testListarDespesa() {
		Response response = given()
			.header("Authorization", "Bearer " + token).
		when()
			.baseUri(baseUri)
			.basePath(basePath)
			.get("listar");
		
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.getContentType(), "application/json");
		assertTrue(response.getBody().asString().contains("\"id\":1"));
	}
	
	@Test
	public void testListarDespesaUm() {
		given()
			.header("Authorization", "Bearer " + token).
		when()
			.baseUri(baseUri)
			.basePath(basePath)
			.get("1").
		then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("descricao", is("Teste"));
	}

}
