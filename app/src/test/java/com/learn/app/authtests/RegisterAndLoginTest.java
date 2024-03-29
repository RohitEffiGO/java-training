package com.learn.app.authtests;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest
public class RegisterAndLoginTest {

	private String random_email;
	private String random_password;

	@BeforeTest
	public void generateTempCreds() {
		String random_email = RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
		String random_password = RandomStringUtils.randomAlphabetic(5);

		this.random_email = random_email;
		this.random_password = random_password;
	}

	@Test
	public void testCategory() {
		Response response = get("api/category/all");
		Assert.assertEquals(response.getStatusCode(), 202);
	}

	@Test
	public void testRegister() {
		Map<String, String> requestMap = new HashMap<>();

		requestMap.put("email", this.random_email);
		requestMap.put("name", "test_rest_assured");
		requestMap.put("password", this.random_password);

		Gson gson = new Gson();
		@SuppressWarnings("rawtypes")
		Type gsonType = new TypeToken<HashMap>() {
		}.getType();
		String request = gson.toJson(requestMap, gsonType);
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("api/auth/register")
				.then().assertThat().statusCode(201).log().all();
	}

	@Test(dependsOnMethods = "testRegister")
	public void testLogin() {
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("email", this.random_email);
		requestMap.put("password", this.random_password);

		Gson gson = new Gson();
		@SuppressWarnings("rawtypes")
		Type gsonType = new TypeToken<HashMap>() {
		}.getType();

		String request = gson.toJson(requestMap, gsonType);

		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("api/auth/login")
				.then().statusCode(202);
	}
}
