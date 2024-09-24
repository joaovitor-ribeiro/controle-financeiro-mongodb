package controlefinanceiro.api;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import org.junit.jupiter.api.BeforeAll;

public class RestAssured {
	
	private static String token;
	
	@BeforeAll //Antes de tudo funcao de teste
	public static void beforeAllRestAssured() {
		LoginApi.login();
		token 	 = LoginApi.getToken();
		
		baseURI  = "http://localhost"; //PROTOCOLO DE SEGURANCA //IP
		port  	 = 8080; //PORTA
	}

	public static String getToken() {
		return token;
	}
	
}
