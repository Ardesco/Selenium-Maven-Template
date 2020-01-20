package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class GoogleHomePage {

    private Query searchBar = new Query().defaultLocator(By.name("q"));
    private Query googleSearch = new Query().defaultLocator(By.name("btnK"));
    private Query imFeelingLucky = new Query().defaultLocator(By.name("btnI"));

    public GoogleHomePage() throws Exception {
        initQueryObjects(this, DriverBase.getDriver());
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