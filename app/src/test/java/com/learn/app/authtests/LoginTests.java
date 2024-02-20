package com.learn.app.authtests;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import io.restassured.http.ContentType;

@SpringBootTest
public class LoginTests {
	private String password;
	private String email;
	private String apiPath = "api/auth/login";
	private Gson gson;
	private Type gsonType;

	@SuppressWarnings({ "rawtypes", "serial" })
	@BeforeTest
	public void defineCredentials() {
		this.email = "test@gmail.com";
		this.password = "pass";
		this.gson = new Gson();
		this.gsonType = new TypeToken<HashMap>() {
		}.getType();
	}

	@Test(priority = 1)
	public void testLoginSuccess() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("email", this.email);
		requestMap.put("password", this.password);

		String request = this.gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath).then().assertThat()
				.statusCode(202);

	}

	@Test(priority = 2)
	public void testLoginFail() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("email", null);
		requestMap.put("password", null);

		String request = this.gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath).then().statusCode(403);

		requestMap.put("email", "test@gmail.com");
		request = this.gson.toJson(requestMap, this.gsonType);
		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath).then().statusCode(403);

		requestMap.put("password", "wrong password");
		request = this.gson.toJson(requestMap, this.gsonType);
		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath).then().statusCode(403);
	}
}
