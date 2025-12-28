package lib.ui;

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

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find  folder by name " + name_of_folder,
                5);
    }

    public void waitForArticleToDisappearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title " + title,
                5);
    }

    public void waitForArticleToAppearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + title,
                15);
    }

    public void swipeArticleToDelete(String title) {
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

    public void switchToReadingLists() {
        this.waitForElementAndClick(
                READING_LISTS_SWITCHER,
                "Cannot find reading lists switcher",
                5);
    }
}

