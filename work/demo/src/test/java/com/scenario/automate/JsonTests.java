package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ConvertToMap;

@SpringBootTest
public class JsonTests {
	ConvertToMap mapper;

	@BeforeTest
	public void initClasses() {
		this.mapper = new ConvertToMap();
	}

	@Test
	public void testConversion() throws JSONException {
		Map<Object, Object> jsonDataMap = new HashMap<>();
		jsonDataMap.put("name", "John Doe");
		jsonDataMap.put("age", 30);
		jsonDataMap.put("city", "New York");
		jsonDataMap.put("isStudent", false);

		// Grades as a list
		List<Integer> grades = new ArrayList<>();
		grades.add(95);
		grades.add(87);
		grades.add(92);
		jsonDataMap.put("grades", grades);

		// Address as a nested Map
		Map<String, String> address = new HashMap<>();
		address.put("street", "123 Main St");
		address.put("zipCode", "10001");
		jsonDataMap.put("address", address);

		String jsonString = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\",\"isStudent\":false,\"grades\":[95,87,92],\"address\":{\"street\":\"123 Main St\",\"zipCode\":\"10001\"}}";

		mapper.setMap(jsonString);

		Map<Object, Object> getResMap = mapper.getMap();

		assertEquals(getResMap, jsonDataMap);
	}

	@Test
	public void jsonTest2() throws JSONException {
		Map<String, Object> jsonDataMap = new HashMap<>();

		// Person object
		Map<String, Object> person = new HashMap<>();
		person.put("name", "Alice");
		person.put("age", 28);

		// Address object
		Map<String, String> address = new HashMap<>();
		address.put("street", "456 Oak St");
		address.put("city", "Wonderland");
		address.put("zipcode", "12345");
		person.put("address", address);

		// Contacts array
		List<Map<String, String>> contacts = new ArrayList<>();
		Map<String, String> emailContact = new HashMap<>();
		emailContact.put("type", "email");
		emailContact.put("value", "alice@example.com");
		contacts.add(emailContact);

		Map<String, String> phoneContact = new HashMap<>();
		phoneContact.put("type", "phone");
		phoneContact.put("value", "+1234567890");
		contacts.add(phoneContact);

		person.put("contacts", contacts);

		// Skills object
		Map<String, List<String>> skills = new HashMap<>();
		skills.put("programming", List.of("Java", "JavaScript", "Python"));
		skills.put("languages", List.of("English", "French"));
		person.put("skills", skills);

		person.put("isStudent", false);

		jsonDataMap.put("person", person);

		// Company object
		Map<String, Object> company = new HashMap<>();
		company.put("name", "Tech Co.");
		company.put("location", "Silicon Valley");

		// Employees array
		List<Map<String, Object>> employees = new ArrayList<>();

		// Employee 1
		Map<String, Object> employee1 = new HashMap<>();
		employee1.put("name", "Bob");
		employee1.put("position", "Software Engineer");
		employee1.put("skills", List.of("C++", "Java"));
		employees.add(employee1);

		// Employee 2
		Map<String, Object> employee2 = new HashMap<>();
		employee2.put("name", "Charlie");
		employee2.put("position", "Data Scientist");
		employee2.put("skills", List.of("Python", "R"));
		employees.add(employee2);

		company.put("employees", employees);

		jsonDataMap.put("company", company);

		String jsonString = "{\"person\":{\"name\":\"Alice\",\"age\":28,\"address\":{\"street\":\"456 Oak St\",\"city\":\"Wonderland\",\"zipcode\":\"12345\"},\"contacts\":[{\"type\":\"email\",\"value\":\"alice@example.com\"},{\"type\":\"phone\",\"value\":\"+1234567890\"}],\"skills\":{\"programming\":[\"Java\",\"JavaScript\",\"Python\"],\"languages\":[\"English\",\"French\"]},\"isStudent\":false},\"company\":{\"name\":\"Tech Co.\",\"location\":\"Silicon Valley\",\"employees\":[{\"name\":\"Bob\",\"position\":\"Software Engineer\",\"skills\":[\"C++\",\"Java\"]},{\"name\":\"Charlie\",\"position\":\"Data Scientist\",\"skills\":[\"Python\",\"R\"]}]}}";
		mapper.setMap(jsonString);

		Map<Object, Object> getResMap = mapper.getMap();

		assertEquals(getResMap, jsonDataMap);
	}
}
