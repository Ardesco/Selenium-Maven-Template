package io.iljapavlovs.cucumber.pageobjects;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;

public class GooglePage {

    //    SIMPLE
    private SelenideElement inputField = $("[name='q']") ;


    public static GooglePage navigate() {
        return open("", GooglePage.class).waitPageIsDisplayed();
    }

    public GooglePage waitPageIsDisplayed() {
        //'should*' automatically waits for condition
        inputField.should(appear);
        return this;
    }


    public GoogleSearchResultPage searchFor(String textToSearchFor) {
        inputField.val(textToSearchFor).pressEnter();

        // No need in PageFactory.initElements()
        return page(GoogleSearchResultPage.class);
    }

}
