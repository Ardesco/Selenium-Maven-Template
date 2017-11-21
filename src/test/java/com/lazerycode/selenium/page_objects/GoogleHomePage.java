package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

public class GoogleHomePage {

    Query searchBar = new Query(By.name("q"));
    Query googleSearch = new Query(By.name("btnK"));
    Query imFeelingLucky = new Query(By.name("btnI"));

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
