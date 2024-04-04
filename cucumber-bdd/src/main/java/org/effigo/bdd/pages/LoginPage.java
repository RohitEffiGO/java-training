package org.effigo.bdd.pages;

import org.effigo.bdd.utils.Loader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends Loader {
	@FindBy(how = How.XPATH, using = "//input[@name='username']")
	WebElement usernameElement;

	@FindBy(how = How.XPATH, using = "//input[@name='password']")
	WebElement passwordElement;

	@FindBy(how = How.XPATH, using = "//button[text()=' Login ']")
	WebElement loginElement;

	@FindBy(how = How.XPATH, using = "//span[text()='Admin']")
	WebElement adminPanElement;

	@FindBy(how = How.XPATH, using = "//p[text()='Invalid credentials']")
	WebElement invalidMessageElement;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public void enterUsername() {
		usernameElement.sendKeys("Admin");
	}

	public void enterUsername(String username) {
		usernameElement.sendKeys(username);
	}

	public void enterPassword() {
		passwordElement.sendKeys("admin123");
	}

	public void enterPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void clickOn() {
		loginElement.click();
	}

	public boolean lookForAdmin() {
		return adminPanElement != null;
	}

	public boolean validateFailure(String message) {
		return invalidMessageElement.getText().equals(message);
	}
}
