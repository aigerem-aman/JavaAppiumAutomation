package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testArticleTitleShouldNotChangeWhenRotated() {
        if (Platform.getInstance().isAndroid()) {NavigationUI navigationUI = NavigationUIFactory.get(driver);
            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
            ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

            navigationUI.closeAllPopups();
            searchPageObject.initSearchInput();
            searchPageObject.typeInSearchLine("Java");
            searchPageObject.waitForSearchResult("Object-oriented programming language");
            searchPageObject.clickOnArticleBySubstring("Object-oriented programming language");
            String article_title_before_rotation = articlePageObject.getArticleTitle("Java (programming language)");
            this.rotateScreenLandscape();
            String article_title_after_rotation = articlePageObject.getArticleTitle("Java (programming language)");

            assertEquals(
                    "The title of the article is not the same after rotation",
                    article_title_before_rotation,
                    article_title_after_rotation);

            this.rotateScreenPortrait();
            String article_title_after_second_rotation = articlePageObject.getArticleTitle("Java (programming language)");

            assertEquals(
                    "The title of the article is not the same after rotation",
                    article_title_before_rotation,
                    article_title_after_second_rotation);
        } else {};
    }

    @Test
    public void testCheckSearchArticleInBackgroud() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroudApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }
}
