package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE_BY_SUBSTRING_TPL,
            FOOTER,
            SAVE_BUTTON,
            ADD_TO_MY_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            EXISTING_LIST,
            CONFIRM_ADDING_LIST,
            REMOVE_FROM_MY_LIST;

    public ArticlePageObject(RemoteWebDriver driver) {
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

    @Step("Waiting for title element")
    public WebElement waitForTitleElement(String subString) {
        String article_title_xpath = getArticleTitleElement(subString);
        return this.waitForElementPresent(
                article_title_xpath,
                "Cannot find article title",
                15);
    }

    @Step("Getting article title")
    public String getArticleTitle(String subString) {
        WebElement title_element = waitForTitleElement(subString);
        screenshot(this.takeScreenshot("article_page"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swiping till footer can be seen")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER,
                    "Cannot find the end of the article",
                    200);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER,
                    "Cannot find the end of the article",
                    200);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER,
                    "Cannot find the end of the article",
                    200);
        }
    }

    @Step("Adding article to a new list on Android")
    public void addArticleToNewListOnAndroid(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save button",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to list button",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find list name input field",
                5);

        this.waitForElementAndClick(
                CONFIRM_ADDING_LIST,
                "Cannot find button to confirm adding",
                5);
    }

    @Step("Adding article to an existing list on Android")
    public void addArticleToExistingListOnAndroid(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save button",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to list button",
                5);

        String list_by_name = getListTitleElement(name_of_folder);

        this.waitForElementAndClick(
                list_by_name,
                "Cannot find list by name " + name_of_folder,
                15);
    }

    @Step("Adding article to a new list on iOS")
    public void addArticleToNewListOniOS(String article_title, String name_of_folder) {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        String add_to_list_text = getAddToListText(article_title);
        this.waitForElementAndClick(
                add_to_list_text,
                "Cannot find option to add article to reading list",
                5);

        this.waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot find 'Create new list' button",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find list name input field",
                5);

        this.waitForElementAndClick(
                CONFIRM_ADDING_LIST,
                "Cannot find Add to list button",
                5);
    }

    @Step("Adding article to an existing list on iOS")
    public void addArticleToExistingListOniOS(String article_title, String name_of_folder) {
        this.waitForElementAndClick(SAVE_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        String add_to_list_text = getAddToListText(article_title);
        this.waitForElementAndClick(
                add_to_list_text,
                "Cannot find confirmation to add article to reading list",
                5);

        String list_by_name = getListTitleElement(name_of_folder);

        this.waitForElementAndClick(
                list_by_name,
                "Cannot find list by name " + name_of_folder,
                15);
    }

    private void removeArticleFromSavedIfAlreadySaved() {
        if (this.isElementPresent(REMOVE_FROM_MY_LIST)) {
            this.waitForElementAndClick(
                    REMOVE_FROM_MY_LIST,
                    "Cannot find remove from save button",
                    5
            );
            this.waitForElementNotPresent(
                    REMOVE_FROM_MY_LIST,
                    "Remove from watchlist button is still present",
                    10);
        }
    }

    @Step("Adding article to Watchlist on Mobile Web")
    public void addArticleToWatchlist() {
        this.removeArticleFromSavedIfAlreadySaved();
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find option to add article to  Watchlist",
                10);
        this.waitForElementPresent(
                REMOVE_FROM_MY_LIST,
                "The article wasn't added to Watchlist",
                15);
    }
}