package org.effigo.bdd.stepdefs;

import org.effigo.bdd.pages.RedirectPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class RedirectStepDef extends RedirectPage {
	@Given("google url")
	public void initSite(DataTable url) {
		driver.get(url.asLists().get(1).get(0).strip());
	}

	@And("try to validate")
	public boolean validate() {
		return validateText();
	}

	@After
	public void destroy() {
		driver.close();
	}
}
