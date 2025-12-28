package tests;

import test.CoreTestCase;
import lib.Platform;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchResultShouldContainExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearchButtonShouldCancelSearch() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testTextInSearchBarShouldBeExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.assertTextInSearchElement("Search Wikipedia");
    }
    @Test
    public void testSearchResultsArePluralAndDisappearAfterCancel() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        int number_of_found_articles = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(number_of_found_articles > 0);
        searchPageObject.clickCancelSearch();
        int number_of_found_articles_after_cancel = searchPageObject.getAmountOfFoundArticles();
        Assert.assertEquals(0, number_of_found_articles_after_cancel);
    }
    @Test
    public void testSearchResultsShouldContainSearchQuery() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");

        List<WebElement> results = searchPageObject.getAllSearchResults();

        for (WebElement element : results) {
            String title = element.getAttribute("text");
            Assert.assertTrue(
                    "Text '" + title + "' does not contain 'Java'",
                    title.toLowerCase().contains("java")
            );
        }
    }
    @Test
    public void testArticlesAreFoundWhenQueryIsCorrect() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        String search_query = "Linkin park Discography";
        searchPageObject.typeInSearchLine(search_query);

        int amount_of_articles_found = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(amount_of_articles_found > 0);
    }
    @Test
    public void testNoResultsWhenQueryIsNotCorrect() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        if (!Platform.getInstance().isMW()) {
            navigationUI.closeAllPopups();
            searchPageObject.initSearchInput();
            String search_query = "xsdfsfsdfsfsdf";
            searchPageObject.typeInSearchLine(search_query);
            searchPageObject.checkNoArticlesFound();
        }
    }

}
