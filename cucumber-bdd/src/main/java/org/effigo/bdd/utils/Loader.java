package org.effigo.bdd.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import io.cucumber.java.Before;

public class Loader {
	protected Map<String, String> kwargs = new HashMap<>();
	private static final String RELATIVEPATH = "\\src\\test\\resources\\config.properties";
	private static final Logger logger = Logger.getLogger(Loader.class.getName());

	@Before
	public void loadIt() {
		loadConfigs();
	}

	public void loadConfigs() {
		Properties properties = new Properties();
		String filePath = System.getProperty("user.dir") + RELATIVEPATH;

		try (FileInputStream input = new FileInputStream(filePath)) {
			properties.load(input);
		} catch (IOException ex) {
			logger.severe("Error loading properties file: " + ex.getMessage());
		}

		properties.forEach((key, value) -> kwargs.put(key.toString(), value.toString()));
	}
}
