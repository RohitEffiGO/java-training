package com.scenario.automate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;
import com.scenario.automate.utils.OtherActions;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
public class WaitAndClickElementTest {
	private WebDriver driver;
	private ElementAction elementAction;
	private FirefoxOptions options;
	private OtherActions performOnElement;

	@BeforeTest
	public void loadWebDriver() {
		WebDriverManager.firefoxdriver().setup();
		this.options = new FirefoxOptions();
		this.options.addArguments("-private");
		this.driver = new FirefoxDriver(options);
		this.elementAction = new ElementAction();
		this.performOnElement = new OtherActions();
	}

	@Test(priority = 1)
	public void testFindElement() {
		this.driver.get("https://www.saucedemo.com/");
		assertEquals(true, elementAction.checkForElement(this.driver, By.cssSelector("#user-name")));
		assertEquals(true, elementAction.checkForElement(this.driver, By.xpath("//*[@id=\"user-name\"]")));
		assertEquals(true, elementAction.checkForElement(this.driver, By.id("user-name")));
	}

	@Test(priority = 2)
	public void testGetWebElement() {
		this.driver.get("https://www.demoblaze.com/index.html");
		assertNotEquals(null,
				elementAction.getTheElement(driver, By.cssSelector("div.col-md-6:nth-child(1) > div:nth-child(1)")));
	}

	@Test(priority = 3)
	public void testClickOnElement() {
		this.driver.get("https://www.demoblaze.com/index.html");
		WebElement element = elementAction.getTheElement(driver, By.cssSelector(
				"html body div#contcont.container div.row div.col-lg-9 div#tbodyid.row div.col-lg-4.col-md-6.mb-4 div.card.h-100 div.card-block h4.card-title a.hrefch"));
		assertEquals(true, elementAction.clickOnElement(element));
	}

	@Test(priority = 4)
	public void testRadioButton() {
		this.driver.get("https://artoftesting.com/samplesiteforselenium");
		Actions action = new Actions(this.driver);

		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN).pause(100).build().perform();

		performOnElement.selectRadioButton(elementAction.getTheElement(this.driver, By.cssSelector("#male")));
		action.pause(100).build().perform();
		performOnElement.selectRadioButton(elementAction.getTheElement(this.driver, By.cssSelector("#female")));
	}

	@Test
	public void dropDownSelector() {
		this.driver.get("https://artoftesting.com/samplesiteforselenium");

		Actions action = new Actions(this.driver);
		WebElement primary = elementAction.getTheElement(this.driver, By.cssSelector("#menu-item-98 > a:nth-child(1)"));
		WebElement secondary = elementAction.getTheElement(this.driver,
				By.cssSelector("#menu-item-309 > a:nth-child(1)"));
		assertEquals(true, performOnElement.dropDownSelector(primary, secondary, action));
	}

	@Test
	public void testDataInTextField() {
		this.driver.get("https://www.saucedemo.com/");
		WebElement usernameElement = elementAction.getTheElement(this.driver, By.cssSelector("#user-name"));
		WebElement passwordElement = elementAction.getTheElement(this.driver, By.cssSelector("#password"));
		assertEquals(true, performOnElement.writeInTextBox(usernameElement, "standard_user"));
		assertEquals(true, performOnElement.writeInTextBox(passwordElement, "secret_sauce"));
	}

	@AfterTest
	public void closeDriver() {
		this.driver.close();
	}
}
