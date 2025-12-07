package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

import java.time.Duration;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE_BY_SUBSTRING_TPL,
            FOOTER,
            SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            EXISTING_LIST,
            CONFIRM_ADDING_LIST;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getArticleTitleElement(String substring) {
        return TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getAddToListText(String substring) {
        return ADD_TO_MY_LIST_BUTTON.replace("{SUBSTRING}", substring);
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
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER,
                    "Cannot find the end of the article",
                    200);
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER,
                    "Cannot find the end of the article",
                    200);
        }
    }

    public void addArticleToNewListOnAndroid(String name_of_folder) {
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

    public void addArticleToExistingListOnAndroid(String name_of_folder) {
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

    public void addArticleToNewListOniOS(String article_title, String name_of_folder) {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                Duration.ofSeconds(5));

        String add_to_list_text = getAddToListText(article_title);
        this.waitForElementAndClick(
                add_to_list_text,
                "Cannot find option to add article to reading list",
                Duration.ofSeconds(5));

        this.waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot find 'Create new list' button",
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
}