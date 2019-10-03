package io.iljapavlovs.cucumber.stepdefs;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.fail;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.iljapavlovs.cucumber.pageobjects.GooglePage;
import io.iljapavlovs.cucumber.pageobjects.GoogleSearchResultPage;

public class Stepdefs {
    private GooglePage googlePage;
    private GoogleSearchResultPage googleSearchResultPage;

    /*
    Driver injection is not needed since driver is injected via Dependency Injection by piccocontainer
        public Stepdefs(SharedDriver driver) {
            this.driver = driver;
        }
    */

    @Given("^I navigate to Google page")
    public void iNavigateToGoogleCom() throws Throwable {
        googlePage = GooglePage.navigate();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchItem) throws Throwable {
        googleSearchResultPage = googlePage.searchFor(searchItem);
    }

    @Then("^first result should contain word \"([^\"]*)\"$")
    public void firstResultShouldContainWord(String searchResult) throws Throwable {
        assertThat(googleSearchResultPage.getSearchResultElements().get(0).getText().toLowerCase()).contains(searchResult);
    }

    @Then("^I purposefully fail this scenario to get a screenshot")
    public void iPurposefullyFailThisFeature() throws Throwable {
        fail();
    }

    @When("^I wait for (\\d+) seconds$")
    public void iWaitForSeconds(int waitTimeInSeconds) throws Throwable {
        Thread.sleep(waitTimeInSeconds*1000);
    }
}
