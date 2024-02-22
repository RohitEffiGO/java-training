package com.scenario.automate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.ElementAction;
import com.scenario.automate.utils.FileOperations;

@SpringBootTest
public class UploadAndDownloadTest {
	ChromeOptions options;
	WebDriver driver;
	ElementAction elementAction;
	FileOperations ops;

	@BeforeTest
	public void loadEverything() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Login\\work\\demo\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");

		this.options = new ChromeOptions();
		Map<String, Object> chromePrefs = new HashMap<String, Object>();

		String downloadFilepath = "D:\\Login\\work\\demo\\src\\main\\resources\\files";

		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.extensions_to_open", "application/txt");
		chromePrefs.put("safebrowsing.enabled", true);

		options.addArguments("--safebrowsing-disable-download-protection");
		options.addArguments("safebrowsing-disable-extension-blacklist");
		options.setExperimentalOption("prefs", chromePrefs);

		this.driver = new ChromeDriver(options);
		this.elementAction = new ElementAction();
		this.ops = new FileOperations();
	}

	@Test
	public void testUpload() {
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/upload-download");
		WebElement element = elementAction.getTheElement(driver, By.cssSelector("#uploadFile"));
		assertNotEquals(null, element);
		assertEquals(true, ops.uploadFileUsingElement(element, "src/main/resources/files/consumer.py"));
	}

	@Test
	public void testDownload() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get("https://myjob.page/tools/random-file-generator");

		Actions actions = new Actions(this.driver);
		WebElement primaryElement = elementAction.getTheElement(driver, By.cssSelector("#multiplier"));
		WebElement downloadElement = elementAction.getTheElement(driver, By.cssSelector("button.btn"));

		assertNotEquals(downloadElement, null);
		assertNotEquals(null, primaryElement);

		actions.moveToElement(primaryElement).click().pause(Duration.ofSeconds(2)).keyDown(Keys.UP).keyDown(Keys.ENTER)
				.build().perform();

		boolean result = ops.downloadFileUsingElement(downloadElement,
				new WebDriverWait(driver, Duration.ofSeconds(20)), 3);
		assertEquals(true, result);
	}

	@AfterTest
	public void destroyEverything() {

//		driver.close();
	}
}
