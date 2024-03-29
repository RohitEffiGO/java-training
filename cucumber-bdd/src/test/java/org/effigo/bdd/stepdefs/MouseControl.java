package org.effigo.bdd.stepdefs;

import java.awt.AWTException;
import java.awt.Robot;

import org.effigo.bdd.utils.Loader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MouseControl extends Loader {

	Robot robot;

	@Before
	public void initRobot() throws AWTException {
		robot = new Robot();
	}

	@Given("init robot")
	public void startRobot() throws InterruptedException {
		Thread.sleep(2000);
//		driver.get("");
	}

	@Then("move mouse")
	public void moveMouse() throws InterruptedException {
		for (int i = 1; i < 100; i++) {
			Thread.sleep(100);
//			robot.mouseMove(i, i);
//			Thread.sleep(100);
			robot.mouseMove(500 - i, 500 - i);
		}
	}

	@After
	public void destroy() {
		driver.close();
	}
}
