package com.scenario.automate.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementAction {
//	Leaving it for temprory purpose, if needed can given a call.
	public boolean checkForElement(WebDriver driver, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
		return (searchResult == null) ? false : true;
	}

//	Remember this method can return null value
	public WebElement getTheElement(WebDriver driver, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
		return searchResult;
	}

	public boolean clickOnElement(WebElement element) {
		if (element != null) {
			element.click();
			return true;
		}
		return false;
	}

	public Actions hoverMouseToElement(Actions action, WebElement element) {
		if (element != null) {
			action = action.moveToElement(element);
			return action;
		}
		return null;
	}

}
