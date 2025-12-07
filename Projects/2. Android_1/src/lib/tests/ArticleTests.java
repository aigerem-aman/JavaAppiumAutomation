package lib.tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testArticleTitleShouldContainExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Iberian horse");
        searchPageObject.clickOnArticleBySubstring("Horses breeds native to Spain and Portugal");
        String article_title = articlePageObject.getArticleTitle("Iberian horse");
        assertEquals(
                "Iberian horse",
                article_title);
    }

    @Test
    public void testArticleCanBeSwipedUp() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Iberian horse");
        searchPageObject.clickOnArticleBySubstring("Horses breeds native to Spain and Portugal");
        articlePageObject.waitForTitleElement("Iberian horse");
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testArticleShouldHaveTitle() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Iberian horse");
        searchPageObject.clickOnArticleBySubstring("Horses breeds native to Spain and Portugal");
        articlePageObject.waitForTitleElement("Iberian horse");
    }

}

