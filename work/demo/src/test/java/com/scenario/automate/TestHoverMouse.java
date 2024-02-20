package com.scenario.automate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest
public class TestHoverMouse {
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
	public void testHoverMouse() {
		this.driver.get("https://www.demoblaze.com/index.html");

		Actions action = new Actions(this.driver);

		elementAction
				.hoverMouseToElement(action,
						elementAction.getTheElement(driver,
								By.cssSelector("li.nav-item:nth-child(1) > a:nth-child(1)")))
				.pause(1000).build().perform();

		elementAction
				.hoverMouseToElement(action,
						elementAction.getTheElement(driver,
								By.cssSelector("li.nav-item:nth-child(2) > a:nth-child(1)")))
				.pause(1000).build().perform();

		elementAction
				.hoverMouseToElement(action,
						elementAction.getTheElement(driver,
								By.cssSelector("li.nav-item:nth-child(3) > a:nth-child(1)")))
				.pause(1000).build().perform();

		elementAction
				.hoverMouseToElement(action,
						elementAction.getTheElement(driver,
								By.cssSelector("li.nav-item:nth-child(4) > a:nth-child(1)")))
				.pause(1000).build().perform();

		elementAction
				.hoverMouseToElement(action,
						elementAction.getTheElement(driver,
								By.cssSelector("li.nav-item:nth-child(5) > a:nth-child(1)")))
				.pause(100).build().perform();
	}

	@AfterTest
	public void closeDriver() {
		this.driver.close();
	}
}
