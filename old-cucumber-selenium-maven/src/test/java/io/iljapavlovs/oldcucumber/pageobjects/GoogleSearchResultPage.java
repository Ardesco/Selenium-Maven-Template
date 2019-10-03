package io.iljapavlovs.oldcucumber.pageobjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class GoogleSearchResultPage extends Page {

    @FindBy(how = How.CLASS_NAME, using = "g")
    private List<WebElement> searchResultElements;

    @Override
    protected WebElement getControlElement() {
        return searchResultElements.get(0);
    }

    public GoogleSearchResultPage(){
        waitUntilLoaded();
    }

    public List<WebElement> getSearchResultElements(){
        return searchResultElements;
    };
}
