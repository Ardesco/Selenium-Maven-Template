package io.iljapavlovs.oldcucumber.pageobjects;


import io.iljapavlovs.oldcucumber.config.ApplicationProperties;
import io.iljapavlovs.oldcucumber.config.ApplicationProperties.ApplicationProperty;
import io.iljapavlovs.oldcucumber.core.WebElementHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GooglePage extends Page {

    @FindBy(how = How.NAME, using = "q")
    private WebElement inputField;

    public GooglePage(){
        waitUntilLoaded();
    }

    public static GooglePage navigate() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(ApplicationProperty.APP_URL));
        return new GooglePage();
    }

    public GoogleSearchResultPage searchFor(String textToSearchFor) {
        WebElementHelper.sendKeys(inputField, textToSearchFor, Keys.ENTER);
        return new GoogleSearchResultPage();
    }

    @Override
    protected WebElement getControlElement() {
        return inputField;
    }

}
