package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class PainelTestApi extends RestAssured {
	
	@BeforeAll
	public static void beforeAll() {
		basePath = "painel";
	}
	
	@Test
	public void testGetTotais() {
		given()
			.contentType(ContentType.JSON)
			.param("data", "2023-02")
			.header("Authorization", getToken())
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("totalGanho", Matchers.greaterThan(0F))
			.body("totalDespesa", Matchers.greaterThan(0F))
			.body("saldo", is(notNullValue()));
	}

}
