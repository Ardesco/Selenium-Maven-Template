package com.lazerycode.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import com.google.inject.Inject;
import com.lazerycode.selenium.pages.components.Header;
import com.lazerycode.selenium.pages.internal.BasePage;

public class ForSaleResultsPage extends BasePage<ForSaleResultsPage> {

	@Inject
	private WebDriver driver;

	@SuppressWarnings("unused")
	@Inject
	private WebDriverWait wait;
	
	@FindBy(id = "siteheader")
	private Header header;

	public ForSaleResultsPage get() {
		HtmlElementLoader.populatePageObject(this, driver);
		return this;
	}
	
	public Header header() {
		return header;
	}

}
