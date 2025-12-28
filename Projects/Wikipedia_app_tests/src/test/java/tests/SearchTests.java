package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
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

@Epic("Tests for search results")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Placeholder in search input is correct")
    @Description("We check placeholder in active search input field")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testTextInSearchBarShouldBeExpected")
    public void testTextInSearchBarShouldBeExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.assertTextInSearchElement("Search Wikipedia");
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Cancel button cancels search")
    @Description("We initiate search but then press cancel button and check that it disappears")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testCancelSearchButtonShouldCancelSearch")
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
    @Features(value={@Feature(value="Search")})
    @DisplayName("Articles do not appear in search results for unsearchable queries")
    @Description("We validate that the system does not return articles in search results for invalid search query")
    @Severity(value=SeverityLevel.TRIVIAL)
    @Step("Starting test testArticlesAreFoundWhenQueryIsCorrect")
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

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search results are correct")
    @Description("We type in search query and check that there is an expected article among them")
    @Severity(value=SeverityLevel.CRITICAL)
    @Step("Starting test testSearchResultShouldContainExpected")
    public void testSearchResultShouldContainExpected() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Articles appear in search results for searchable queries")
    @Description("We validate that the system returns articles in search results for valid search query")
    @Severity(value=SeverityLevel.NORMAL)
    @Step("Starting test testArticlesAreFoundWhenQueryIsCorrect")
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
    @Features(value={@Feature(value="Search")})
    @DisplayName("Each search result contains search query")
    @Description("We validate that search results match the search query")
    @Severity(value=SeverityLevel.CRITICAL)
    @Step("Starting test testSearchResultsShouldContainSearchQuery")
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
                    title.toLowerCase().contains("java"));
        }
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search results are multiple and are not visible after cancellation")
    @Description("We check search results before and after canceling search")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testSearchResultsArePluralAndDisappearAfterCancel")
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
}
