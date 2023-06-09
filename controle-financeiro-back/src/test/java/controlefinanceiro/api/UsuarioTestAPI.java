package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsuarioTestAPI {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@Test
	public void testInserirUsuario() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "usuario";
		
		Usuario usuario = new Usuario();
		usuario.setNome("Usuário normal");
		usuario.setCpf("51027504027");
		usuario.setEmail("usunormal@gmail.com");
		usuario.setSenha("123456");
		
		given()
			.contentType(ContentType.JSON)
			.body(usuario)
		.when()
			.post("inserir")
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
}
