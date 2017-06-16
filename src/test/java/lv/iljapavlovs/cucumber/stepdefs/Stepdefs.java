package lv.iljapavlovs.cucumber.stepdefs;

import lv.iljapavlovs.cucumber.core.DriverBase;
import lv.iljapavlovs.cucumber.pageobjects.GooglePage;
import lv.iljapavlovs.cucumber.pageobjects.GoogleSearchResultPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class Stepdefs {
    private WebDriver driver;
    private GooglePage googlePage;
    private GoogleSearchResultPage googleSearchResultPage;

    /*
    Driver injection is not needed since driver is injected via Dependency Injection by piccocontainer
        public Stepdefs(SharedDriver driver) {
            this.driver = driver;
        }
    */

    @Given("^I navigate to \"([^\"]*)\"$")
    public void iNavigateToGoogleCom(String url) throws Throwable {
        driver = DriverBase.getDriver();
        driver.get("https://" + url);
        googlePage = new GooglePage(driver);
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String searchItem) throws Throwable {
        googleSearchResultPage = googlePage.searchFor(searchItem);
    }

    @Then("^first result should contain word \"([^\"]*)\"$")
    public void firstResultShouldContainWord(String searchResult) throws Throwable {
        assertThat(googleSearchResultPage.getSearchResultElements().get(0).getText().toLowerCase(), containsString(searchResult));
    }

    @And("^I purposefully fail this scenario to get a screenshot")
    public void iPurposefullyFailThisFeature() throws Throwable {
        fail();
    }

    @And("^I wait for (\\d+) seconds$")
    public void iWaitForSeconds(int waitTimeInSeconds) throws Throwable {
        Thread.sleep(waitTimeInSeconds*1000);
    }
}
