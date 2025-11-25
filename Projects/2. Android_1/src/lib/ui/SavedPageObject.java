package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.time.Duration;

public class SavedPageObject extends MainPageObject {
    private static final String
            FOLDER_BY_NAME_TPL = "//*[contains(@text, '{name_of_folder}')]",
            ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']/*[contains(@text, '{title}')]";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{name_of_folder}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{title}", title);
    }

    public SavedPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find  folder by name " + name_of_folder,
                Duration.ofSeconds(5));
    }

    public void waitForArticleToDisappearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article is still present with title " + title,
                Duration.ofSeconds(5));
    }

    public void waitForArticleToAppearByTitle(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " + title,
                Duration.ofSeconds(5));
    }

    public void clickArticleByTitle(String title) {
        this.waitForArticleToAppearByTitle(title);
    }

    public void swipeArticleToDelete(String title) {
        String article_xpath = getSavedArticleXpathByTitle(title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find the article to delete");
        this.waitForArticleToDisappearByTitle(title);
    }
}

