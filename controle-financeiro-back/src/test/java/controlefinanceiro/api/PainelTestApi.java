package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PainelTestApi {
	
	@BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
	
	@BeforeAll
	public static void login() {
		LoginApi.login();
	}
	
	@Test
	public void testGetTotais() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "painel";
		
		given()
			.contentType(ContentType.JSON)
			.param("data", "2023-02")
			.header("Authorization", LoginApi.getToken())
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("totalGanho", Matchers.greaterThan(0F))
			.body("totalDespesa", Matchers.greaterThan(0F))
			.body("saldo", is(notNullValue()));
	}
	

}
