package lib.tests;

import lib.CoreTestCase;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchResultShouldContainExpected() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearchButtonShouldCancelSearch() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testTextInSearchBarShouldBeExpected() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        searchPageObject.assertTextInSearchElement("Search Wikipedia");
    }
    @Test
    public void testSearchResultsArePluralAndDisappearAfterCancel() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        int number_of_found_articles = searchPageObject.getAmountOfFoundArticles();
        assertTrue(number_of_found_articles > 0);
        searchPageObject.clickCancelSearch();
        int number_of_found_articles_after_cancel = searchPageObject.getAmountOfFoundArticles();
        assertEquals(0, number_of_found_articles_after_cancel);
    }
    @Test
    public void testSearchResultsShouldContainSearchQuery() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");

        List<WebElement> results = searchPageObject.getAllSearchResults();

        for (WebElement element : results) {
            String title = element.getAttribute("text");
            assertTrue(
                    "Text '" + title + "' does not contain 'Java'",
                    title.toLowerCase().contains("java")
            );
        }
    }
    @Test
    public void testArticlesAreFoundWhenQueryIsCorrect() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        navigationUI.pressSkipButtonIfPresent();
        navigationUI.pressCloseButtonIfPresent();
        searchPageObject.initSearchInput();
        String search_query = "Linkin park Discography";
        searchPageObject.typeInSearchLine(search_query);

        int amount_of_articles_found = searchPageObject.getAmountOfFoundArticles();

        assertTrue(amount_of_articles_found > 0);
    }
    @Test
    public void testNoResultsWhenQueryIsNotCorrect() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        String search_query = "xsdfsfsdfsfsdf";
        searchPageObject.typeInSearchLine(search_query);
        searchPageObject.checkNoArticlesFound();
    }

}
