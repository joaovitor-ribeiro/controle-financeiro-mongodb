package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import org.springframework.stereotype.Service;

import controlefinanceiro.api.bean.LoginBeanTest;
import controlefinanceiro.bean.TokenBean;
import io.restassured.http.ContentType;

@Service
public class LoginApi {
	
	private static String token;
	
	public static void login() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "autenticacao";
		
		TokenBean bean = given()
								.contentType(ContentType.JSON)
								.body(new LoginBeanTest())
						    .when()
								.post()
						    .thenReturn()
						    	.as(TokenBean.class);
		
		setToken( bean.getTipo(), bean.getToken() );
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String tipo, String token) {
		LoginApi.token = tipo + " " + token;
	}
	
}
