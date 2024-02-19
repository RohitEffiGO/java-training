package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
public class WaitAndClickElementTest {
	private WebDriver driver;
	private ElementAction elementAction;
	private FirefoxOptions options;

	@BeforeTest
	public void loadWebDriver() {
		WebDriverManager.firefoxdriver().setup();
		this.options = new FirefoxOptions();
		this.options.addArguments("-private");
		this.driver = new FirefoxDriver(options);
		this.elementAction = new ElementAction();
	}

	@Test
	public void openBrowser() {
		driver.get("https://www.google.com/");
		driver.close();
	}

	@Test
	public void searchAndClickId() {
		this.driver = new FirefoxDriver(options);
		driver.get("https://www.saucedemo.com/");
		if (elementAction.checkForElement(driver, By.id("user-name"))) {
			driver.close();
		}
	}

	@Test
	public void searchAndClickCss() {
		this.driver = new FirefoxDriver(options);
		driver.get("https://www.saucedemo.com/");
		System.out.println(elementAction.checkForElement(driver, By.cssSelector("#user-name")));
		if (elementAction.checkForElement(driver, By.cssSelector("#user-name"))) {
			driver.close();
		}
	}

	@Test
	public void searchAndClickXpath() {
		this.driver = new FirefoxDriver(options);
		driver.get("https://www.saucedemo.com/");
		if (elementAction.checkForElement(driver, By.xpath("//*[@id=\"user-name\"]"))) {
			driver.close();
		}
	}

	@Test
	public void testTextField() {
		this.driver = new FirefoxDriver(this.options);
		driver.get("https://www.saucedemo.com/");

		assertEquals(true, elementAction.writeInTextBox(driver, By.xpath("//*[@id=\"user-name\"]"), "standard_user"));
		assertEquals(true, elementAction.writeInTextBox(driver, By.cssSelector("#password"), "secret_sauce"));

		driver.close();
	}

	@Test
	public void testButton() {
		this.driver = new FirefoxDriver(this.options);
		driver.get("https://www.saucedemo.com/");

		assertEquals(true, elementAction.writeInTextBox(driver, By.xpath("//*[@id=\"user-name\"]"), "standard_user"));
		assertEquals(true, elementAction.writeInTextBox(driver, By.cssSelector("#password"), "secret_sauce"));
		assertEquals(true, elementAction.clickOnElement(driver, By.cssSelector("#login-button")));

		driver.close();
	}

	@Test
	public void testMouseHover() {
		this.driver = new FirefoxDriver(this.options);
		driver.get("https://www.saucedemo.com/");

		assertEquals(true, elementAction.writeInTextBox(driver, By.xpath("//*[@id=\"user-name\"]"), "standard_user"));
		assertEquals(true, elementAction.writeInTextBox(driver, By.cssSelector("#password"), "secret_sauce"));
		assertEquals(true, elementAction.clickOnElement(driver, By.cssSelector("#login-button")));

		elementAction.hoverMouseToElement(driver, By.cssSelector(".product_sort_container")).click().build().perform();
		driver.close();
	}

	@Test
	public void testSelectRadioButton() {
		this.driver = new FirefoxDriver(this.options);
		driver.get("https://seleniumbase.io/w3schools/radio_buttons");
		assertEquals(true, elementAction.selectRadioButton(driver, By.xpath("//*[@id=\"html\" @type=\"radio\"]")));

	}

	@Test
	public void testDropDownSelector() throws InterruptedException {
		this.driver = new FirefoxDriver(this.options);
		driver.get("https://artoftesting.com/samplesiteforselenium");

		List<By> instructions = new ArrayList<>();
		instructions.add(By.xpath("//*[@id=\"menu-item-99\"]"));
		instructions.add(By.xpath("//*[@id=\"menu-item-313\"]"));
		instructions.add(By.xpath("//*[@id=\"menu-item-99\"]"));
		instructions.add(By.xpath("//*[@id=\"menu-item-605\"]"));

		assertEquals(true, elementAction.dropDownSelector(driver, instructions));
	}
}
