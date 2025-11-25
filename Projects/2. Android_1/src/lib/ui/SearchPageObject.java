package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']",
        FIRST_SEARCH_RESULT =  "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[1]",
        RESULT_CONTAINTER = "org.wikipedia:id/page_list_item_title",
        EMPTY_RESULTS_CONTAINER = "//*[@resource-id='org.wikipedia:id/results_text']";

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
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search bar",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input field",
                Duration.ofSeconds(5));
    }

    public void assertTextInSearchElement(String expected_text)
    {
        assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT),
                expected_text,
                "Texts do not match");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "X button is still present",
                Duration.ofSeconds(5));
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));
    }

    public void typeInSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                searchLine,
                "Cannot find search input field",
                Duration.ofSeconds(5));
    }

    public void clearSearchInput(String search_query1)
    {
        this.waitForElementAndClear(
                By.xpath("//*[contains(@text, '" +  search_query1 + "')]"),
                "Cannot find " + search_query1 + "in search input field",
                Duration.ofSeconds(5));
    }

    public void waitForSearchResult(String subString) {
        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result"
        );
    }

    public void clickOnFirstArticle() {
        NavigationUI navigationUI = new NavigationUI(driver);

        this.waitForElementAndClick(
                By.xpath(FIRST_SEARCH_RESULT),
                "Cannot find search result",
                Duration.ofSeconds(15));

        navigationUI.pressCloseButtonIfPresent();
    }

    public void clickOnArticleBySubstring(String subString) {
        NavigationUI navigationUI = new NavigationUI(driver);

        String search_result_xpath = getResultSearchElement(subString);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find search result",
                Duration.ofSeconds(10));

        navigationUI.pressCloseButtonIfPresent();
    }

    public int getAmountOfFoundArticles() {
        try {
            this.waitForElementPresent(
                    By.xpath(SEARCH_RESULT_ELEMENT),
                    "No search results",
                    Duration.ofSeconds(5)
            );
        } catch (Exception e) {
            return 0; // если не найдено - возвращаем 0
        }

        return driver.findElements(By.xpath(SEARCH_RESULT_ELEMENT)).size();
    }

    public void checkNoArticlesFound() {
        this.waitForElementPresent(By.xpath(EMPTY_RESULTS_CONTAINER),
                "Cannot find empty results container",
                Duration.ofSeconds(15));
        assertElementHasText(
                By.xpath(EMPTY_RESULTS_CONTAINER),
                "No results",
                "Expected text: \"No results\", but " + driver.findElement(By.xpath(EMPTY_RESULTS_CONTAINER)).getText());
    }

    public List<WebElement> getAllSearchResults() {
        return driver.findElements(By.id(RESULT_CONTAINTER));
    }

}
