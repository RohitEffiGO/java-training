package org.effigo.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RedirectPage {
	protected WebDriver driver;

	@FindBy(how = How.XPATH, using = "(//input[@value=\"Google Search\"])[2]")
	WebElement redirectElement;

	public RedirectPage() {
		driver = new FirefoxDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public boolean validateText() {
		String text = redirectElement.getAccessibleName();
		return text.equalsIgnoreCase("Google Search");
	}
}
