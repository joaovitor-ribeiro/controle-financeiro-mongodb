package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controlefinanceiro.dto.usuario.UsuarioEntrada;
import io.restassured.http.ContentType;

public class UsuarioTestAPI extends RestAssured {
	
	@BeforeAll
	public static void beforeAll() {
		basePath = "usuario";
	}
	
	@Test
	public void testInserirUsuario() {
		UsuarioEntrada usuario = new UsuarioEntrada("Usuário Teste", "51027504027", "usuarioteste@gmail.com", "123456");
		
		given()
			.contentType(ContentType.JSON)
			.body(usuario)
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
}
