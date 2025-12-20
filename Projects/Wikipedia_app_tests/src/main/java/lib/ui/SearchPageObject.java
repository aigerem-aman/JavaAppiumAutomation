package lib.ui;

import lib.Platform;
import lib.ui.Factories.NavigationUIFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

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

    public SearchPageObject(RemoteWebDriver driver)
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
                10);
    }

    public void initSearchInput() {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search bar",
                5);

        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input field",
                5);
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
                5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "X button is still present",
                5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find X button to cancel search",
                5);
    }

    public void typeInSearchLine(String searchLine) {
        if (Platform.getInstance().isAndroid()) {
            waitForElementAndSendKeys(SEARCH_INPUT,
                    searchLine,
                    "Cannot find search input",
                    5);
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
                10);
    }

    public void waitForSearchResult(String subString) {
        String search_result = getResultSearchElement(subString);
        this.waitForElementPresent(
                search_result,
                "Cannot find search result",
                20);
    }

    public void clickOnFirstArticle() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        this.waitForElementAndClick(
                FIRST_SEARCH_RESULT,
                "Cannot find search result",
                20);

        navigationUI.closeAllPopups();
    }

    public void clickOnArticleBySubstring(String subString) {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);

        this.waitForElementAndClick(
                getResultSearchElement(subString),
                "Cannot find search result",
                10);

        navigationUI.closeAllPopups();
    }

    public int getAmountOfFoundArticles() {
        try {
            this.waitForElementPresent(
                    SEARCH_RESULT_ELEMENT,
                    "No search results",
                    5);
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
                15);
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