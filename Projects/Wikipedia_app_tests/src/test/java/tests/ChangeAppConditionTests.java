package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import test.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Epic("Tests for articles")
    @Features(value={@Feature(value="Search")})
    @DisplayName("Article title doesn't change when the screen has been rotated")
    @Description("We open found article and check that its title is the same before and after rotation")
    @Severity(value= SeverityLevel.MINOR)
    @Step("Starting test testArticleTitleShouldNotChangeWhenRotated")
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

            Assert.assertEquals(
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
    }

    @Test
    @Epic("Tests for search results")
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search results do not change after sending app to background and opening it back")
    @Description("We search for article and check that it is still present in search results after getting the app back from background")
    @Severity(value= SeverityLevel.MINOR)
    @Step("Starting test testCheckSearchArticleInBackground")
    public void testCheckSearchArticleInBackground() {
        if  (Platform.getInstance().isMW()) {return;}
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");

    }
}
