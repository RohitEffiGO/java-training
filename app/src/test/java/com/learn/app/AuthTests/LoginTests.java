package com.learn.app.AuthTests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
	private String loginPath = "schema/login.json";
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

		given().contentType(ContentType.JSON).body(request).when().post("api/auth/login").then().assertThat()
				.body(matchesJsonSchemaInClasspath(this.loginPath)).statusCode(202);

	}

	@Test(priority = 2)
	public void testLoginFail() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("email", null);
		requestMap.put("password", null);

		String request = this.gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).body(request).when().post("api/auth/login").then().statusCode(403);

		requestMap.put("email", "test@gmail.com");
		request = this.gson.toJson(requestMap, this.gsonType);
		given().contentType(ContentType.JSON).body(request).when().post("api/auth/login").then().statusCode(403);

		requestMap.put("password", "wrong password");
		request = this.gson.toJson(requestMap, this.gsonType);
		given().contentType(ContentType.JSON).body(request).when().post("api/auth/login").then().statusCode(403);
	}
}
