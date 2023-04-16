package controlefinanceiro.api;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

import org.springframework.stereotype.Service;

import controlefinanceiro.bean.LoginBean;
import controlefinanceiro.bean.TokenBean;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

@Service
public class LoginApi {
	
	private static String token;
	
	public static void login() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "autenticacao";
		
		LoginBean usuario = new LoginBean("jaibinho@email.com", "MTIzNDU2");
		Response response = given()
								.contentType(ContentType.JSON)
								.body(usuario)
						    .when()
								.post();
		
		ResponseBody<?> body = response.getBody();
		TokenBean tokenBean = body.as(TokenBean.class);
		setToken("Bearer " + tokenBean.getToken());
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		LoginApi.token = token;
	}
	
}
