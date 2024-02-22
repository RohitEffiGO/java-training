package com.scenario.automate.utils;

import org.openqa.selenium.WebDriver;

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

}
