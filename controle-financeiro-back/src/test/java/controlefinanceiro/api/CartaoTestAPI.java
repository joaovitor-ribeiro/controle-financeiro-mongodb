package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.model.Cartao;
import io.restassured.http.ContentType;

public class CartaoTestAPI extends RestAssured {
	
	@BeforeAll
	public static void beforeAll() {
		basePath = "cartao";
	}
	
	@Test
	public void testInserirCartao() {
		CartaoEntrada cartao = new CartaoEntrada("Nubank Teste API", "Mastercard", "5173863405996183", 120.00);
		
		given()
			.contentType(ContentType.JSON)
			.body(cartao)
			.header("Authorization", getToken())
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
	@Test
	public void testEditarCartao() {
		CartaoEntrada cartao = new CartaoEntrada("Nubank Teste API", "Visa", "4485057546086477", 120.30);
		
		given()
			.contentType(ContentType.JSON)
			.body(cartao)
			.header("Authorization", getToken())
		.when()
			.put("editar/1")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("limite", is ( Float.parseFloat( cartao.limite().toString() ) )  );
	}
	
	
	@Test
	public void testListarCartao() {
		Cartao cartao = new Cartao();
		cartao.setId(1);
		cartao.setNome("Teste");
		cartao.setBandeira("Visa");
		cartao.setNumero("4485043530451679");
		cartao.setLimite(105.59);
		
		given()
			.header("Authorization", getToken())
		.when()
			.get("listar")
		.then()
			.statusCode(HttpStatus.SC_OK)
			.contentType(ContentType.JSON)
			.extract().body().jsonPath().getList(".", Cartao.class).contains(cartao);
	}
	
	@Test
	public void testListarCartaoUm() {
		given()
			.header("Authorization", getToken())
		.when()
			.get("1")
		.then()
			.contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.body("nome", is("Nubank Teste API"));
	}

}
