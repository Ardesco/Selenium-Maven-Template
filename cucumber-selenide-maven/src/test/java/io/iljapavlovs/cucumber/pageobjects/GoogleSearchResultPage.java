package io.iljapavlovs.cucumber.pageobjects;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;


public class GoogleSearchResultPage {

    private ElementsCollection searchResultElements = $$(".g");


    public ElementsCollection getSearchResultElements() {
        return searchResultElements;
    }
}
