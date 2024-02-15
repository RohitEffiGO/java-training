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
public class AddCoursesTest {
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
		this.email = "test@gmail.com";
		this.password = "pass";
		this.gson = new Gson();
		this.gsonType = new TypeToken<HashMap>() {
		}.getType();
	}

	@BeforeTest
	public void defineToken() {
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
	public void testAddCoursesAdmin() {
		this.email = "test@gmail.com";
		this.password = "pass";
		defineToken();

		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("name", "Course_admin");
		requestMap.put("category", "test");

//		Login by admin
		// pass 201
		String request = gson.toJson(requestMap, this.gsonType);

		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(201);

		// fail 403
		given().contentType(ContentType.JSON).body(request).when().post(apiPath + "add").then().statusCode(403);

		// fail 400 (name = null)
		requestMap.put("name", null);
		request = gson.toJson(requestMap);
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(400);

		// fail 400 (category = null)
		requestMap.put("name", "valid_course");
		requestMap.put("category", null);
		request = gson.toJson(requestMap);
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(400);

		// fail 400 (unvalid category)
		requestMap.put("name", "valid_course");
		requestMap.put("category", "test_lkafal");
		request = gson.toJson(requestMap);
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(400);
	}

	@Test
	public void addCourseAuthor() {
		Map<String, String> requestMap = new HashMap<>();
//		Login by author
		this.email = "author@gmail.com";
		this.password = "pass";
		defineToken();

		// pass 201
		requestMap.put("name", "valid_course_by_author");
		requestMap.put("category", "test");
		String request = gson.toJson(requestMap);
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(201);

	}

	@Test
	public void addCourseLearner() {
//		Login by leaner (fail)
		this.email = "learner@gmail.com";
		this.password = "pass";
		defineToken();

		Map<String, String> requestMap = new HashMap<>();

		requestMap.put("name", "valid_course_by_author");
		requestMap.put("category", "test");
		String request = gson.toJson(requestMap);
		given().contentType(ContentType.JSON).header("Authorization", this.token).body(request).when()
				.post(apiPath + "add").then().statusCode(401);
	}
}
