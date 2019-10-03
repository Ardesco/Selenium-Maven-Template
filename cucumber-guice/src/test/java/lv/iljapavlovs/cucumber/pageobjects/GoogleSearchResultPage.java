package lv.iljapavlovs.cucumber.pageobjects;

import java.util.List;
import lv.iljapavlovs.cucumber.core.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class GoogleSearchResultPage extends Page {

    @FindBy(how = How.CLASS_NAME, using = "g")
    private List<WebElement> searchResultElements;

    public GoogleSearchResultPage() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("g")));
    }

    public List<WebElement> getSearchResultElements(){
        return searchResultElements;
    };

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(searchResultElements.get(0));
    }

}
