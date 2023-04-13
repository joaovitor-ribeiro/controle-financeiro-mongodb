package controlefinanceiro.api;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import controlefinanceiro.model.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsuarioTestAPI {
	
	private final static String baseUri = "http://localhost:8080/";
	private final static String basePath = "usuario/";
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@Test
	public void testInserirUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usuário normal");
		usuario.setCpf("51027504027");
		usuario.setEmail("usunormal@gmail.com");
		usuario.setSenha("123456");
		
		given()
			.contentType(ContentType.JSON)
			.body(usuario).
		when()
			.baseUri(baseUri)
			.basePath(basePath)
			.post("inserir").
		then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	
}
