package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleHomePage {

    private final RemoteWebDriver driver = DriverBase.getDriver();

    private Query searchBar = new Query(By.name("q"), driver);
    private Query googleSearch = new Query(By.name("btnK"), driver);
    private Query imFeelingLucky = new Query(By.name("btnI"), driver);

    public GoogleHomePage() throws Exception {
    }

    public GoogleHomePage enterSearchTerm(String searchTerm) {
        searchBar.findWebElement().clear();
        searchBar.findWebElement().sendKeys(searchTerm);

        return this;
    }

    public GoogleHomePage submitSearch() {
        googleSearch.findWebElement().submit();

        return this;
    }

    public void getLucky() {
        imFeelingLucky.findWebElement().click();
    }

}