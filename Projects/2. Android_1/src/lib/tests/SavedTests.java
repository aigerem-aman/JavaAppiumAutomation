package lib.tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SavedPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SavedTests extends CoreTestCase {
    @Test
    public void testSaveArticleToMyListThenDeleteArticle() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        SavedPageObject savedPageObject = new SavedPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.clickOnArticleBySubstring("Object-oriented programming language");
        navigationUI.pressCloseButtonIfPresent();
        String name_of_folder = "Learning Programming";
        articlePageObject.addArticleToMyNewList(name_of_folder);
        navigationUI.pressNavigateUp();
        navigationUI.pressNavigateUp();
        navigationUI.clickMyListButton();
        savedPageObject.openFolderByName(name_of_folder);
        navigationUI.pressGotItButtonIfPresent();
        String title = "Java (programming language)";
        savedPageObject.swipeArticleToDelete(title);
    }
    @Test
    public void testOnlyDeletedArticleGetsDeleted() {
        NavigationUI navigationUI = new NavigationUI(driver);
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        SavedPageObject savedPageObject = new SavedPageObject(driver);

        searchPageObject.initSearchInput();

        String[] words = {"Apple", "Banana", "Cherry", "Grape", "Tomato", "Melon"};
        List<String> list_of_queries = new ArrayList<>(Arrays.asList(words));
        Random random = new Random();
        String search_query1 = list_of_queries.remove(random.nextInt(list_of_queries.size()));
        String search_query2 = list_of_queries.remove(random.nextInt(list_of_queries.size()));

        searchPageObject.typeInSearchLine(search_query1);
        searchPageObject.clickOnFirstArticle();

        String name_of_folder = "To eat";
        articlePageObject.addArticleToMyNewList(name_of_folder);
        navigationUI.pressNavigateUp();
        searchPageObject.clearSearchInput(search_query1);

        searchPageObject.typeInSearchLine(search_query2);
        searchPageObject.clickOnFirstArticle();
        articlePageObject.addArticleToExistingList(name_of_folder);
        navigationUI.pressNavigateUp();
        navigationUI.pressNavigateUp();
        navigationUI.clickMyListButton();
        savedPageObject.openFolderByName(name_of_folder);
        navigationUI.pressGotItButtonIfPresent();
        savedPageObject.swipeArticleToDelete(search_query1);
        savedPageObject.waitForArticleToAppearByTitle(search_query2);
        savedPageObject.clickArticleByTitle(search_query2);
        String article_title = articlePageObject.getArticleTitle(search_query2);
        assertEquals(
                search_query2,
                article_title);
    }
}
