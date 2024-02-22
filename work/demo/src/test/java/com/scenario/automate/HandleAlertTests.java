package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;
import com.scenario.automate.utils.HandleAlertsPopUpsDailogue;

@SpringBootTest
public class HandleAlertTests {
	ChromeOptions options;
	WebDriver driver;
	HandleAlertsPopUpsDailogue handler;
	ElementAction elementAction;

	@BeforeTest
	public void loadEverything() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Login\\work\\demo\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");

		this.options = new ChromeOptions();
		options.addArguments("incognito");
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		String downloadFilepath = "D:\\Login\\work\\demo\\src\\main\\resources\\files";
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.prompt_for_download", false);

		options.setExperimentalOption("prefs", chromePrefs);
		this.driver = new ChromeDriver(options);
		this.handler = new HandleAlertsPopUpsDailogue();
		this.elementAction = new ElementAction();
	}

	@Test
	public void handleAlertTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
		WebElement element = elementAction.getTheElement(driver, By.cssSelector(".btn-danger"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertsWithOk(driver));
	}

	@Test
	public void cancelAlertTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();
		WebElement element = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[2]/a"));
		elementAction.clickOnElement(element);
		element = elementAction.getTheElement(driver, By.cssSelector(".btn-primary"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertsWithCancel(driver));
	}

	@Test
	public void sendAlertMessageTest() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().window().maximize();

		WebElement element = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a"));
		elementAction.clickOnElement(element);
		element = elementAction.getTheElement(driver, By.xpath("/html/body/div[1]/div/div/div/div[2]/div[3]/button"));
		elementAction.clickOnElement(element);

		assertEquals(true, handler.handleAlertWithTextBox(driver, "This is a test data"));
	}

	@Test
	public void testClosePopUp() {
		driver.manage().window().maximize();
		driver.get("https://www.webroot.com/services/popuptester1.htm");

		assertEquals(true, handler.closePopUp(driver));
	}

	@Test
	public void testModalOpenAndClose() {
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");

		WebElement triggerElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/button"));

		assertEquals(true, elementAction.clickOnElement(triggerElement));

		WebElement interactElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/div[2]/div/div/div[3]/button[2]"));

		handler.waitForElement(driver, interactElement);
		assertEquals(true, handler.dialogBoxHandler(interactElement));

		elementAction.clickOnElement(triggerElement);

		WebElement closeElement = elementAction.getTheElement(driver,
				By.xpath("/html/body/div[1]/section[2]/div/div/div/div[1]/div[2]/div/div/div[1]/button"));

		handler.waitForElement(driver, closeElement);
		assertEquals(true, handler.dialogBoxHandler(closeElement));
	}

	@AfterTest
	public void closeAll() {
		driver.close();
	}
}
