package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_BY_SUBSTRING_TPL = "//android.widget.TextView[@text='{SUBSTRING}']",
            FOOTER = "//*[@text='View article in browser']",
            SAVE_BUTTON = "org.wikipedia:id/page_save",
            ADD_TO_MY_LIST_BUTTON = "//*[contains(@text, 'Add to list')]",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            EXISTING_LIST = "//*[@resource-id='org.wikipedia:id/item_title' and @text='{SUBSTRING}']",
            CONFIRM_ADDING_LIST = "//*[contains(@text, 'OK')]";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getArticleTitleElement(String substring) {
        return TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getListTitleElement(String substring) {
        return TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public WebElement waitForTitleElement(String subString) {
        String article_title_xpath = getArticleTitleElement(subString);
        return this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find article title",
                Duration.ofSeconds(15));
    }

    public String getArticleTitle(String subString) {
        WebElement title_element = waitForTitleElement(subString);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER),
                "Cannot find the end of the article",
                20);
    }

    public void addArticleToMyNewList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find list name input field",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                By.xpath(CONFIRM_ADDING_LIST),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));
    }

    public void addArticleToExistingList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        String list_by_name = getListTitleElement(name_of_folder);

        this.waitForElementAndClick(
                By.xpath(list_by_name),
                "Cannot find list by name " + name_of_folder,
                Duration.ofSeconds(15));
    }

}
