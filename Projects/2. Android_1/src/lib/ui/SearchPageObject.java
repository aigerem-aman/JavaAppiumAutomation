package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "xpath~//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath~//*[contains(@text, 'Search Wikipedia')]", //org.wikipedia:id/search_src_text
        SEARCH_CANCEL_BUTTON = "id~org.wikipedia:id/search_close_btn", //Clear query
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath~//*[@resource-id=\"org.wikipedia:id/search_results_list\"]//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "xpath~//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
        FIRST_SEARCH_RESULT =  "xpath~//*[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[1]",
        RESULT_CONTAINER = "id~org.wikipedia:id/page_list_item_title",
        EMPTY_RESULTS_CONTAINER = "xpath~//*[@resource-id='org.wikipedia:id/results_text']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        NavigationUI navigationUI = new NavigationUI(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
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
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                searchLine,
                "Cannot find search input field",
                Duration.ofSeconds(5));
    }

    public void clearSearchInput(String search_query1)
    {
        this.waitForElementAndClear(
              "xpath~//*[contains(@text, '" +  search_query1 + "')]",
                "Cannot find " + search_query1 + "in search input field",
                Duration.ofSeconds(10));
    }

    public void waitForSearchResult(String subString) {
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result"
        );
    }

    public void clickOnFirstArticle() {
        NavigationUI navigationUI = new NavigationUI(driver);

        this.waitForElementAndClick(
                FIRST_SEARCH_RESULT,
                "Cannot find search result",
                Duration.ofSeconds(15));

        navigationUI.pressCloseButtonIfPresent();
    }

    public void clickOnArticleBySubstring(String subString) {
        NavigationUI navigationUI = new NavigationUI(driver);

        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find search result",
                Duration.ofSeconds(10));

        navigationUI.pressCloseButtonIfPresent();
    }

    public int getAmountOfFoundArticles() {
        try {
            this.waitForElementPresent(
                    SEARCH_RESULT_ELEMENT,
                    "No search results",
                    Duration.ofSeconds(5)
            );
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
        assertElementHasText(
                EMPTY_RESULTS_CONTAINER,
                "No results",
                "Expected text: \"No results\", but " + driver.findElement(by).getText());
    }

    public List<WebElement> getAllSearchResults() {
        By by = this.getLocatorByString(RESULT_CONTAINER);
        return driver.findElements(by);
    }

}
