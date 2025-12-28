package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SavedPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            READING_LISTS_SWITCHER,
            DELETE_ARTICLE_BUTTON,
            REMOVE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{name_of_folder}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{title}", title);
    }

    private static String getRemoveButtonByTitle(String title)
    {
        return REMOVE_BY_TITLE_TPL.replace("{title}", title);
    }

    public SavedPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Opening folder by name '{name_of_folder}'")
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find  folder by name " + name_of_folder,
                5);
    }

    @Step("Checking that correct article was deleted")
    public void waitForArticleToDisappearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title " + title,
                5);
    }

    @Step("Locating article by title")
    public void waitForArticleToAppearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + title,
                15);
    }

    @Step("Deleting article from saved")
    public void deleteArticleFromSaved(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);

        if (!Platform.getInstance().isMW()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find the article to delete");
            if (Platform.getInstance().isIOS()) {
                this.waitForElementAndClick(
                        DELETE_ARTICLE_BUTTON,
                        "Cannot find delete button",
                        5);
            }
        } else {
            String remove_locator = getRemoveButtonByTitle(title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot find remove button",
                    15
            );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(title);
    }

    @Step("Switching to Reading lists")
    public void switchToReadingLists() {
        this.waitForElementAndClick(
                READING_LISTS_SWITCHER,
                "Cannot find reading lists switcher",
                5);
    }
}

