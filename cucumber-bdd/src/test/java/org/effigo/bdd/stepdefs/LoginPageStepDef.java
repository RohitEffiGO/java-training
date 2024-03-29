package org.effigo.bdd.stepdefs;

import static org.testng.Assert.assertEquals;

import org.effigo.bdd.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDef extends LoginPage {

	@Given("I am redirected to login page")
	public void redirectTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Given("Entered valid username and password")
	public void enterUserAndPass() {
		enterPassword();
		enterUsername();
	}

	@When("Click on login button")
	public void clickOnButton() {
		clickOn();
	}

	@Then("Should login successfully")
	public void loginSuccess() {
		assertEquals(lookForAdmin(), true);
	}

	@Given("Wrong user credentials with username: {string} and password: {string}")
	public void wrongUserCredentials(String username, String password) {
		enterUsername(username);
		enterPassword(password);
	}

	@Then("Message {string} should appear")
	public void loginFail(String message) {
		assertEquals(validateFailure(message), true);
	}

	@After
	public void destroy() {
		driver.close();
	}
}
