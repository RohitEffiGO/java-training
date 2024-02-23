package com.scenario.automate.utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleAlertsPopUpsDailogue {

	public boolean handleAlertsWithOk(WebDriver driver) {
		try {
			driver.switchTo().alert().accept();
			return true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return false;

	}

	public boolean handleAlertsWithCancel(WebDriver driver) {
		try {
			driver.switchTo().alert().dismiss();
			return true;
		} catch (Exception error) {
			error.printStackTrace();
		}
		return false;
	}

	public boolean handleAlertWithTextBox(WebDriver driver, String data) {

		try {
			driver.switchTo().alert().sendKeys(data);
			driver.switchTo().alert().accept();
			return true;
		} catch (Exception error) {
			error.printStackTrace();
		}

		return false;
	}

	/*
	 * This method takes instance of webdriver and switches to window(pop up) if
	 * appeared.
	 */
	public WebDriver switchToPopUp(WebDriver driver) {
		String parentHandle = driver.getWindowHandle();
		Set<String> allHandleSet = driver.getWindowHandles();

		for (String handle : allHandleSet) {
			if (!handle.equals(parentHandle)) {
				return driver.switchTo().window(handle);
			}
		}

		return driver;
	}

	public boolean closePopUp(WebDriver driver) {

		String parentHandle = driver.getWindowHandle();
		Set<String> allHandleSet = driver.getWindowHandles();

		for (String handle : allHandleSet) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle).close();
				driver.switchTo().window(parentHandle);
				return true;
			}
		}

		return false;
	}

	public void waitForElement(WebDriver driver, WebElement element) {
		if (element != null)
			new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * This method closes or performs some task on dialog box.
	 * 
	 * Remember to add a WebDriverWait before calling this method by adding a
	 * condition elementToBeClickable(<pass the element>).
	 * 
	 */
	public boolean dialogBoxHandler(WebElement interactElement) {

		if (interactElement != null) {
			interactElement.click();
			return true;
		}

		return false;
	}
}