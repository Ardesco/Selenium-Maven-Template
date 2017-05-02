package lv.iljapavlovs.cucumber.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.NAME, using = "q")
    private WebElement inputField;

    @FindBy(how = How.NAME, using = "btnG")
    private WebElement searchIconButton;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(inputField));
    }

    public GoogleSearchResultPage searchFor(String textToSearchFor) {
        inputField.clear();
        inputField.sendKeys(textToSearchFor);
        searchIconButton.click();
        return new GoogleSearchResultPage(driver);
    }

}