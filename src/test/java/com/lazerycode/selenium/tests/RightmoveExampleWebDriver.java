package com.lazerycode.selenium.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.lazerycode.selenium.DriverFactory;
import com.lazerycode.selenium.pages.ForSaleResultsPage;
import com.lazerycode.selenium.pages.HomePage;

public class RightmoveExampleWebDriver extends DriverFactory {

	@Test(description = "Perform search then check recent searches updated")
	public void testRecentSearches() {
		// Search for property for sale in 'SE16'
		ForSaleResultsPage resultsPage = HomePage.open().then()
				.searchPropertiesForSale("SE16").then().findProperties();

		// Click logo to go back to the home page
		HomePage homePage = resultsPage.header().clickLogoToGoHome();

		// Check 'SE16' is listed in recent searches
		assertTrue(homePage.getRecentSearches().contains("SE16"),
				"Expected SE16 in recent searches");
	}
}