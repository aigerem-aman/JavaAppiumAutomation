package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import test.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Article has title")
    @Description("We open found article and check that its title is present")
    @Severity(value=SeverityLevel.TRIVIAL)
    @Step("Starting test testArticleShouldHaveTitle")
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

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Article title matches expected")
    @Description("We search for article and check that its title is correct")
    @Severity(value=SeverityLevel.NORMAL)
    @Step("Starting test testArticleTitleShouldContainExpected")
    public void testArticleTitleShouldContainExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Iberian horse");
        searchPageObject.clickOnArticleBySubstring("Horses breeds native to Spain and Portugal");
        String article_title = articlePageObject.getArticleTitle("Iberian horse");
        articlePageObject.takeScreenshot("article_page");
        Assert.assertEquals(
                "Iberian horse",
                article_title);
    }

    @Test
    @Features(value={@Feature(value="Article")})
    @DisplayName("Article can be swiped to footer")
    @Description("We open found article and swipe it till footer can be seen")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testArticleCanBeSwipedUp")
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
}

