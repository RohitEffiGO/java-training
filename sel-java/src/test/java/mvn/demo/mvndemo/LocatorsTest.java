package mvn.demo.mvndemo;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocatorsTest {
	private WebDriver driver;
	private String user_id = "rohit.thakur@effigo.tech";
	private String user_password = "R@hit202002";
	@Before
	public void setup() {
//		Init webdriver and firefox windows in incognito mode
		
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-private");
		this.driver = new FirefoxDriver(options);
	}
	
	@Test
	public void googleSearchGitHub() {
//		Getting google.com
        driver.get("https://www.google.com/");

//      Finding google search box and typing github in it.
        WebElement searchBox = driver.findElement(By.cssSelector("#APjFqb"));
        searchBox.sendKeys("github");
        searchBox.sendKeys(Keys.ENTER);

//      Clicking search button.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".eKjLze > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > a:nth-child(1) > h3:nth-child(2)")));
        searchResult.click();
        
//      Getting redirecting git url and finding username and password field and authenticating on it.
        WebElement gitURL = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".HeaderMenu-link--sign-in")));
        gitURL.click();
        WebElement gitLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_field")));
        gitLogin.sendKeys(this.user_id);
        gitLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        gitLogin.sendKeys(this.user_password);
        gitLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.btn")));
        gitLogin.click();
        
//      After successfully login in now login out using dashboard.
        WebElement gitLogout = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.Button-label img")));
        gitLogout.click();
        gitLogout = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.ActionListItem-label:nth-child(1)")));
        gitLogout.click();
        gitLogout = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form.inline-form:nth-child(1)")));
        gitLogout.click();
        
//      Closing the session.
        driver.close();
    }
}
