package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_BY_SUBSTRING_TPL = "xpath~//android.widget.TextView[@text='{SUBSTRING}']",
            FOOTER = "xpath~//*[@text='View article in browser']",
            SAVE_BUTTON = "id~org.wikipedia:id/page_save",
            ADD_TO_MY_LIST_BUTTON = "xpath~//*[contains(@text, 'Add to list')]",
            MY_LIST_NAME_INPUT = "id~org.wikipedia:id/text_input",
            EXISTING_LIST = "xpath~//*[@resource-id='org.wikipedia:id/item_title' and @text='{SUBSTRING}']",
            CONFIRM_ADDING_LIST = "xpath~//*[contains(@text,'OK')]";

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
                article_title_xpath,
                "Cannot find article title",
                Duration.ofSeconds(15));
    }

    public String getArticleTitle(String subString) {
        WebElement title_element = waitForTitleElement(subString);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER,
                "Cannot find the end of the article",
                20);
    }

    public void addArticleToMyNewList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save button",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find list name input field",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                CONFIRM_ADDING_LIST,
                "Cannot find Add to list button",
                Duration.ofSeconds(5));
    }

    public void addArticleToExistingList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save button",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        String list_by_name = getListTitleElement(name_of_folder);

        this.waitForElementAndClick(
                list_by_name,
                "Cannot find list by name " + name_of_folder,
                Duration.ofSeconds(15));
    }

}
