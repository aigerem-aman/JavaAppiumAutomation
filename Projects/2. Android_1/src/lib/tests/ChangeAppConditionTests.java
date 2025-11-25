package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testArticleTitleShouldNotChangeWhenRotated() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
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
    }
    @Test
    public void testCheckSearchArticleInBackgroud() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroudApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }
}
