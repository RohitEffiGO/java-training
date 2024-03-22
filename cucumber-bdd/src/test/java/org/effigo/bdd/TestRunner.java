package org.effigo.bdd;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature", glue = { "org.effigo.bdd.stepdefs" }, plugin = { "pretty",
		"html:target/cucumber-reports.html", "json:target/cucumber.json" }, monochrome = true, publish = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
