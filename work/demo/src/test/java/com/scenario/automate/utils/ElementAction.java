package com.scenario.automate.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// change signature of every method
public class ElementAction {
	WebDriver driver;

	public boolean checkForElement(WebDriver driver, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
		return (searchResult == null) ? false : true;
	}

	public boolean writeInTextBox(WebDriver driver, By findBy, String data) {
		if (checkForElement(driver, findBy)) {
			WebElement result = driver.findElement(findBy);
			result.sendKeys(data);
			return true;
		}
		return false;
	}

	public boolean clickOnElement(WebDriver driver, By findBy) {
		if (checkForElement(driver, findBy)) {
			WebElement result = driver.findElement(findBy);
			result.click();
			return true;
		}
		return false;
	}

	public Actions hoverMouseToElement(WebDriver driver, By findBy) {
		if (checkForElement(driver, findBy)) {
			WebElement element = driver.findElement(findBy);
			Actions action = new Actions(driver);
			action = action.moveToElement(element);
			return action;
		}
		return null;
	}

	public boolean selectRadioButton(WebDriver driver, By findBy) {
		return clickOnElement(driver, findBy);
	}

	public boolean dropDownSelector(WebDriver driver, List<By> findByList) throws InterruptedException {
		for (By findBy : findByList) {
			if (checkForElement(driver, findBy)) {
				WebElement element = driver.findElement(findBy);
				Actions action = new Actions(driver);
				action = action.moveToElement(element);
			}
		}

		return true;
	}
}
