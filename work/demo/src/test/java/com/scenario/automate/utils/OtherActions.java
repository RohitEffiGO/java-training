package com.scenario.automate.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OtherActions {

	public boolean selectRadioButton(WebElement element) {

		if (element != null) {
			element.click();
			return true;
		}
		return false;
	}

	public boolean writeInTextBox(WebElement element, String data) {
		if (element != null) {
			element.sendKeys(data);
			return true;
		}
		return false;
	}

	public boolean dropDownSelector(WebElement dropDownElement, WebElement selectElement, Actions action) {
		ElementAction elementAction = new ElementAction();

		if (dropDownElement != null && selectElement != null) {
			elementAction.hoverMouseToElement(action, dropDownElement).pause(100).build().perform();
			elementAction.hoverMouseToElement(action, selectElement).pause(100).build().perform();
			selectElement.click();
			return true;
		}

		return false;
	}
}
