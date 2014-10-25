package com.lazerycode.selenium.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import com.google.inject.Inject;
import com.lazerycode.selenium.pages.internal.BasePage;
import com.lazerycode.selenium.pages.internal.PageFactory;

public class HomePage extends BasePage<HomePage> {

	@Inject
	private WebDriver driver;

	@Inject
	private WebDriverWait wait;

	@Name("Search field")
	@FindBy(id = "searchLocation")
	private TextInput searchField;

	@Name("Recent Search links")
	@FindBy(css = "a[id^='recent-search-']")
	private List<HtmlElement> recentSearchLinks;

	public HomePage get() {
		HtmlElementLoader.populatePageObject(this, driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("searchLocation")));
		return this;
	}

	@Override
	public HomePage get(String url) {
		driver.get(url);
		return get();
	}

	@Step("Navigate to http://www.rightmove.co.uk")
	public static HomePage open() {
		return PageFactory.newInstance(HomePage.class, "http://www.rightmove.co.uk");
	}

	@Step("Search properties for sale near \"{0}\".")
	public ForSaleFilterPage searchPropertiesForSale(String location) {
		// Clear field then perform search
		searchField.clear();
		searchField.sendKeys(location + Keys.RETURN);

		return PageFactory.getInstance(ForSaleFilterPage.class);
	}

	@Step("Get the recent searches.")
	public List<String> getRecentSearches() {
		List<String> recentSearches = new ArrayList<String>();
		for (HtmlElement link : recentSearchLinks) {
			recentSearches.add(link.getText());
		}
		return recentSearches;
	}
}
