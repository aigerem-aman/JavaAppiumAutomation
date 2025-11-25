package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testArticleTitleShouldContainExpected() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.clickOnArticleBySubstring("Object-oriented programming language");
        String article_title = articlePageObject.getArticleTitle("Java (programming language)");
        assertEquals(
                "Java (programming language)",
                article_title);
    }

    @Test
    public void testArticleCanBeSwipedUp() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Appium");
        searchPageObject.clickOnArticleBySubstring("Appium");
        articlePageObject.waitForTitleElement("Appium");
        navigationUI.pressCloseButtonIfPresent();
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testArticleShouldHaveTitle() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.clickOnArticleBySubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement("Java (programming language)");
    }

}

