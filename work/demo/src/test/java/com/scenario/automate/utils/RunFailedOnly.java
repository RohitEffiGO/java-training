package com.scenario.automate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.TestNG;

/*
 * 
 * Call runTestLoader along with xmlFilePath.
 * Also declare a property in applications.properties: 'execute.failed.tests'
 * And set this property to boolean either true or false.
 * 
 */

public class RunFailedOnly {
	private final String applicationPropertiesFilePathString = "src\\main\\resources\\application.properties";
	private final String failedTestProperty = "execute.failed.tests";

	private Properties loadProperties(String filePath) throws IOException {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		}
		return properties;
	}

	public void runTestLoader(String xmlFilePath, String applicationPropertiesFilePathString) throws IOException {
		Properties properties = null;
		if (applicationPropertiesFilePathString == null) {
			properties = loadProperties(this.applicationPropertiesFilePathString);
		} else {
			properties = loadProperties(this.applicationPropertiesFilePathString);
		}

		boolean executeOnlyFailedTests = Boolean.parseBoolean(properties.getProperty(this.failedTestProperty));

		TestNG testNG = new TestNG();
		List<String> suites = new ArrayList<>();

		if (executeOnlyFailedTests) {
			suites.add(xmlFilePath);
		}

		testNG.setTestSuites(suites);
		testNG.run();
	}

	public void runTestLoader(String xmlFilePath) throws IOException {
		Properties properties = loadProperties(this.applicationPropertiesFilePathString);

		boolean executeOnlyFailedTests = Boolean.parseBoolean(properties.getProperty(this.failedTestProperty));
		TestNG testNG = new TestNG();
		List<String> suites = new ArrayList<>();

		if (executeOnlyFailedTests) {
			suites.add(xmlFilePath);
		}
		testNG.setTestSuites(suites);
		testNG.run();
	}
}
