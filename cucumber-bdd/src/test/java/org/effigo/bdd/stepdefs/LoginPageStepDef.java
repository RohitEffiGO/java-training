package org.effigo.bdd.stepdefs;

import static org.testng.Assert.assertEquals;

import org.effigo.bdd.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDef {
	LoginPage loginPage;
	WebDriver driver;

	@Before
	public void setup() {
		driver = new FirefoxDriver();
		loginPage = new LoginPage(driver);
	}

	@After
	public void close() {
		driver.close();
	}

	@Given("I am redirected to login page")
	public void redirectTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Given("Entered valid username and password")
	public void enterUserAndPass() {
		loginPage.enterPassword();
		loginPage.enterUsername();
	}

	@When("Click on login button")
	public void clickOnButton() {
		loginPage.clickOn();
	}

	@Then("Should login successfully")
	public void loginSuccess() {
		assertEquals(loginPage.lookForAdmin(), true);
	}

	@Given("Wrong user credentials with username: {string} and password: {string}")
	public void wrongUserCredentials(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
	}

	@Then("Message {string} should appear")
	public void loginFail(String message) {
		assertEquals(loginPage.validateFailure(message), true);
	}

}
