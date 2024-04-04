package org.effigo.bdd.pages.demoqa;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccordianPage {
	FirefoxDriver driver;
	Robot robot;

	@FindBy(how = How.XPATH, using = "(//div[@class=\"card mt-4 top-card\"])[4]")
	WebElement widgetElement;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Accordian')]/parent::li")
	WebElement accordianElement;

	Logger log;

	public AccordianPage() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		this.log = Logger.getLogger(AccordianPage.class.getName());

		Path extensionPath = Paths
				.get(System.getProperty("user.dir") + "\\src\\test\\resources\\extensions\\ublock_origin.xpi");

		this.driver = new FirefoxDriver();
		driver.installExtension(extensionPath);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
	}

	public boolean redirect() {
		try {
			driver.get("https://demoqa.com");
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public boolean goToWidgetFully() {
		try {
			widgetElement.click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			robot.mouseWheel(2);
			wait.until(ExpectedConditions.elementToBeClickable(accordianElement));

			accordianElement.click();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	public boolean writeToFile(String xpath) {
		try {
			String filePathString = System.getProperty("user.dir") + "\\src\\test\\resources\\dump\\accordian.txt";

			File file = new File(filePathString);
			if (!file.exists() && file.createNewFile()) {
				log.info("file created");
			}

			try (FileWriter fileWriter = new FileWriter(file)) {
				int elementIndex = 1;

				while (elementIndex < 7) {
					try {
						String alteredXpath = xpath + "[" + elementIndex + "]";
						WebElement element = driver.findElement(By.xpath(alteredXpath));

						if (elementIndex != 1 && elementIndex % 2 != 0) {
							JavascriptExecutor js = driver;
							js.executeScript("arguments[0].scrollIntoView(true);", element);
							Thread.sleep(2000);
							element.click();
						}

						String text = element.getText();
						log.info("DATA EXTRACTED: " + text + "\n");
						fileWriter.write(text + "\n");
						elementIndex += 1;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
			}

			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean readFromFile() {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\dump\\accordian.txt";

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			log.info("Reading data from file:");
			while ((line = reader.readLine()) != null) {
				log.info(line);
			}
		} catch (IOException e) {
			log.severe("Error reading from file: " + e.getMessage());
			return false;
		}

		return true;
	}

	public void destroy() {
		driver.close();
	}

}
