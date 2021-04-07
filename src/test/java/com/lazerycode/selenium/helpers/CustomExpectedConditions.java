package com.lazerycode.selenium.helpers;

import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {

    static public ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }
}
