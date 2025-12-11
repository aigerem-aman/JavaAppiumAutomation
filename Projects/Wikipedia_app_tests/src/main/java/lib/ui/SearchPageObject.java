package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.WelcomePageObjectFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static java.awt.SystemColor.text;

abstract public class SearchPageObject extends MainPageObject {

    protected static  String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        FIRST_SEARCH_RESULT,
        RESULT_TITLE_CONTAINER,
        EMPTY_RESULTS_CONTAINER,
        EMPTY_RESULTS_TEXT;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void searchBarPresent(){
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search bar",
                Duration.ofSeconds(10));
    }

    public void initSearchInput() {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search bar",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input field",
                Duration.ofSeconds(5));
    }

    public void assertTextInSearchElement(String expected_text)
    {
        assertElementHasText(
                SEARCH_INIT_ELEMENT,
                expected_text,
                "Texts do not match");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "X button is still present",
                Duration.ofSeconds(5));
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));
    }

    public void typeInSearchLine(String searchLine) {
        if (Platform.getInstance().isAndroid()) {
            waitForElementAndSendKeys(SEARCH_INPUT,
                    searchLine,
                    "Cannot find search input",
                    Duration.ofSeconds(5));
        } else {
            By by = getLocatorByString(SEARCH_INPUT);
            WebElement element = driver.findElement(by);

            for (char c : searchLine.toCharArray()) {
                element.sendKeys(String.valueOf(c));
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ignored) {
                }
            }
            element.sendKeys("\n");
        }
    }


    public void clearSearchInput(String search_query1)
    {
        String search_bar_with_query1;

        if (Platform.getInstance().isAndroid()){
            search_bar_with_query1 = "xpath~//*[contains(@text, '" +  search_query1 + "')]";
        } else {
            search_bar_with_query1 = "xpath~//*[contains(@value, '" +  search_query1 + "')]";
        };

        this.waitForElementAndClear(
                search_bar_with_query1,
                "Cannot find " + search_query1 + " in search input field",
                Duration.ofSeconds(10));
    }

    public void waitForSearchResult(String subString) {
        String search_result = getResultSearchElement(subString);
        this.waitForElementPresent(
                search_result,
                "Cannot find search result",
                Duration.ofSeconds(20));
    }

    public void clickOnFirstArticle() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        this.waitForElementAndClick(
                FIRST_SEARCH_RESULT,
                "Cannot find search result",
                Duration.ofSeconds(20));

        navigationUI.closeAllPopups();
    }

    public void clickOnArticleBySubstring(String subString) {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        this.waitForElementAndClick(
                getResultSearchElement(subString),
                "Cannot find search result",
                Duration.ofSeconds(10));

        navigationUI.closeAllPopups();
    }

    public int getAmountOfFoundArticles() {
        try {
            this.waitForElementPresent(
                    SEARCH_RESULT_ELEMENT,
                    "No search results",
                    Duration.ofSeconds(5));
        } catch (Exception e) {
            return 0; // если не найдено - возвращаем 0
        }

        By by = this.getLocatorByString(SEARCH_RESULT_ELEMENT);
        return driver.findElements(by).size();

    }

    public void checkNoArticlesFound() {
        this.waitForElementPresent(
                EMPTY_RESULTS_CONTAINER,
                "Cannot find empty results container",
                Duration.ofSeconds(15));
        By by = this.getLocatorByString(EMPTY_RESULTS_CONTAINER);
        if (Platform.getInstance().isAndroid()) {
            assertElementHasText(
                    EMPTY_RESULTS_CONTAINER,
                    EMPTY_RESULTS_TEXT,
                    "Expected text: \"No results\", but actual text was different"
            );
        } else {
            assertElementHasText(
                    EMPTY_RESULTS_CONTAINER,
                    EMPTY_RESULTS_TEXT,
                    "Expected text: \"No results\", but actual text was different"
            );
        }
    }

    public List<WebElement> getAllSearchResults() {
        By by = this.getLocatorByString(RESULT_TITLE_CONTAINER);
        return driver.findElements(by);
    }
}