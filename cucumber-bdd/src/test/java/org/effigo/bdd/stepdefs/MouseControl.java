package org.effigo.bdd.stepdefs;

import java.awt.AWTException;
import java.awt.Robot;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MouseControl {
	static Robot robot;

	@BeforeAll
	public static void initRobot() throws AWTException {
		robot = new Robot();
	}

	@Given("init robot")
	public void startRobot() throws InterruptedException {
		Thread.sleep(2000);
	}

	@Then("move mouse")
	public void moveMouse() throws InterruptedException {
		for (int i = 1; i < 100; i++) {
			Thread.sleep(100);
			robot.mouseMove(500 - i, 500 - i);
		}
	}
}
