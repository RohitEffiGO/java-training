package org.effigo.bdd.stepdefs.demoqa;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.effigo.bdd.pages.demoqa.AccordianPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccordianStepDef {
	AccordianPage accordianPage;

	/*
	 * Setting up background for the scenario.
	 */
	@Before
	public void load() {
		this.accordianPage = new AccordianPage();
	}

	@Then("redirect to demoqa")
	public void redirect() {
		assertEquals(accordianPage.redirect(), true);
	}

	@And("click on widgets and go to accordian")
	public void goTo() {
		assertEquals(accordianPage.goToWidgetFully(), true);
	}

	/*
	 * Starting test from here.
	 */
	@Given("path of all card headers get it and write it into some file")
	public void getDataFromCards(DataTable data) {
		List<String> dataList = data.asList();
		assertEquals(accordianPage.writeToFile(dataList.get(1)), true);
	}

	@Then("show the extracted data from file manual validation")
	public void manualValidation() {
		accordianPage.readFromFile();
		assertEquals(accordianPage.readFromFile(), true);
	}

	@After
	public void destroy() {
		accordianPage.destroy();
	}
}
