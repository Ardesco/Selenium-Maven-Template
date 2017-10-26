package lv.iljapavlovs.cucumber.pageobjects;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage extends Page {

    @FindBy(how = How.NAME, using = "q")
    private WebElement inputField;

    public GooglePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOf(inputField));
    }

    public GoogleSearchResultPage searchFor(String textToSearchFor) {
        WebElementHelper.sendKeys(inputField, textToSearchFor, Keys.ENTER);
        return new GoogleSearchResultPage(driver);
    }

}