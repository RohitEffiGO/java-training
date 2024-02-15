package com.learn.app.coursetests;

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
import io.restassured.response.Response;

@SpringBootTest
public class GetCoursesTest {
	private String token;
	private String email;
	private String password;
	private Gson gson;
	private Type gsonType;
	private String apiPath = "api/course/";
	private String loginPath = "api/auth/login";

	@SuppressWarnings({ "serial", "rawtypes" })
	@BeforeTest
	public void defineCredentials() {
		this.gson = new Gson();
		this.gsonType = new TypeToken<HashMap>() {
		}.getType();
	}

	@BeforeTest
	public void defineToken() {
		this.email = "test@gmail.com";
		this.password = "pass";
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("email", this.email);
		requestMap.put("password", this.password);

		String request = this.gson.toJson(requestMap, this.gsonType);

		Response response = given().contentType(ContentType.JSON).body(request).when().post(this.loginPath);

		Map<String, String> responseMap = this.gson.fromJson(response.asString(), this.gsonType);
		this.token = "Bearer " + responseMap.get("Message");
		System.out.println(this.token);
	}

	@Test
	public void testGetCourses() {
//		Success 200
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("category", "Java");

		String request = gson.toJson(requestMap, this.gsonType);

//		with token
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch").then().statusCode(200);

//		without token
		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath + "fetch").then().statusCode(200);

//		Fail 400
		requestMap.put("category", null);
		request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch").then().statusCode(400);

//		Fail 400
		requestMap.put("category", "");
		request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch").then().statusCode(400);

//		Fail 400
		requestMap.put("category", "this category does not exists");
		request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch").then().statusCode(400);
	}

	@Test
	public void getCourseByid() {
//		Success 200
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("category", "test");

		String request = gson.toJson(requestMap, this.gsonType);

//		with token
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch/10").then().statusCode(200);

//		without token
		given().contentType(ContentType.JSON).body(request).when().post(this.apiPath + "fetch/10").then()
				.statusCode(200);

//		Fail 400
		requestMap.put("category", "test");
		request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch/100").then().statusCode(400);

//		Fail 400
		requestMap.put("category", "this category does not exists");
		request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(this.apiPath + "fetch/10").then().statusCode(400);
	}

}
