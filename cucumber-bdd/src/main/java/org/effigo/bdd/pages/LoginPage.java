package org.effigo.bdd.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername() {
		By userBy = By.xpath("//input[@name='username']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(userBy));
		searchResult.sendKeys("Admin");
	}

	public void enterUsername(String username) {
		By userBy = By.xpath("//input[@name='username']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(userBy));
		searchResult.sendKeys(username);
	}

	public void enterPassword() {
		By passBy = By.xpath("//input[@name='password']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(passBy));
		searchResult.sendKeys("admin123");
	}

	public void enterPassword(String password) {
		By passBy = By.xpath("//input[@name='password']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(passBy));
		searchResult.sendKeys(password);
	}

	public void clickOn() {
		By buttonBy = By.xpath("//button[text()=' Login ']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
		searchResult.click();
	}

	public boolean lookForAdmin() {
		By buttonBy = By.xpath("//span[text()='Admin']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
		return searchResult != null;
	}

	public boolean validateFailure(String message) {
		By buttonBy = By.xpath("//p[text()='Invalid credentials']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(buttonBy));
		return searchResult.getText().equals(message);

	}
}
