package org.effigo.bdd.stepdefs;

import org.effigo.bdd.pages.RedirectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class RedirectStepDef {
	static WebDriver driver;
	static RedirectPage redirectPage;

	@BeforeAll
	public static void load() {
		driver = new FirefoxDriver();
		redirectPage = new RedirectPage(driver);
	}

	@Given("google url")
	public void initSite(DataTable url) {
		driver.get(url.asLists().get(1).get(0).strip());
	}

	@And("try to validate")
	public boolean validate() {
		return redirectPage.validateText();
	}

	@AfterAll
	public static void destroy() {
		driver.close();
	}
}
