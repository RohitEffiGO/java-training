package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import com.scenario.automate.utils.RunFailedOnly;

@SpringBootTest
//@Listeners(com.scenario.automate.utils.RunFailedOnly.class)
public class RunOnlyFailedTests {
	@Test
	public void failTest() {
		assertEquals(true, true);
	}

	@Test
	public void anotherFailure() {
		assertEquals(2, 2);
	}

	/*
	 * Execute failed tasks from testng-failed.xml Pass file path of the xml file.
	 * Also if appilications.properties is in different directory then its file path
	 * can also be looked into.
	 */
	@Test
	public void testRunOnlyTest() throws IOException {
		RunFailedOnly run = new RunFailedOnly();
		run.runTestLoader("D:\\Login\\work\\demo\\test-output\\testng-failed.xml");
	}
}
