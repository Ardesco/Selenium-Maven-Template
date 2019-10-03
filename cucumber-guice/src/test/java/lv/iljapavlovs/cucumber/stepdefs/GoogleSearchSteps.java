package lv.iljapavlovs.cucumber.stepdefs;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.pageobjects.GooglePage;
import lv.iljapavlovs.cucumber.util.DataHolder;

@ScenarioScoped
public class GoogleSearchSteps {

  private GooglePage googlePage;

  @Inject
  DataHolder dataHolder;

  @Given("^I navigate to Google page")
  public void iNavigateToGoogleCom() throws Throwable {
    googlePage = GooglePage.navigate();
  }

  @When("^I search for \"([^\"]*)\"$")
  public void iSearchFor(String searchItem) throws Throwable {
    googlePage.searchFor(searchItem);
  }

  @When("^I set variable in one class$")
  public void iSetVariableInOneClass() throws Throwable {
    dataHolder.setSharedVariable("SHARED VARIABLE");
  }
}
