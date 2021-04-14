package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.GoogleHomePage;
import com.lazerycode.selenium.page_objects.GoogleSearchPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Execution(CONCURRENT)
public class GoogleExample2IT extends DriverBase {


    @Test
    public void googleBreadExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface, not the implementation.
        WebDriver driver = getDriver();

        // First of all, let's navigate to the google home page
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        //Instantiate an instance of our GoogleHomePage page object
        GoogleHomePage googleHomePage = new GoogleHomePage();

        // First we agree to Google's cookie usage terms
        googleHomePage.acceptCookies();

        // Then we perform a google search for Cheese
        GoogleSearchPage googleSearchPage = googleHomePage.enterSearchTerm("Bread").submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // We wait for up to 15 seconds for the page to load, an exception is thrown if it doesn't
        googleSearchPage.waitForPageTitleToStartWith("Bread");

        //Normally you would have some assertions to check things that you really care about
        assertThat(googleSearchPage.getPageTitle()).isEqualTo("Bread - Google Search");
    }

    @Test
    public void googleCarExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface, not the implementation.
        WebDriver driver = getDriver();

        // First of all, let's navigate to the google home page
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        //Instantiate an instance of our GoogleHomePage page object
        GoogleHomePage googleHomePage = new GoogleHomePage();

        // First we agree to Google's cookie usage terms
        googleHomePage.acceptCookies();

        // Then we perform a google search for Cheese
        GoogleSearchPage googleSearchPage = googleHomePage.enterSearchTerm("Car").submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // We wait for up to 15 seconds for the page to load, an exception is thrown if it doesn't
        googleSearchPage.waitForPageTitleToStartWith("Car");

        //Normally you would have some assertions to check things that you really care about
        assertThat(googleSearchPage.getPageTitle()).isEqualTo("Car - Google Search");
    }
}
