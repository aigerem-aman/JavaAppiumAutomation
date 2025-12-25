package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SavedPageObjectFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SavedTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveArticleToMyListThenDeleteArticle() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        SavedPageObject savedPageObject = SavedPageObjectFactory.get(driver);
        AuthorizationPageObject auth = new AuthorizationPageObject(driver);

        navigationUI.closeAllPopups();
        if (Platform.getInstance().isMW()) {
            auth.goToAuthPage();
            auth.enterLogInData("TokyoTower1958", "dibru6-bobmyx-kexdYc");
            auth.SubmitForm();
        }
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine("Java");
        searchPageObject.clickOnArticleBySubstring("Object-oriented programming language");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewListOnAndroid(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticleToNewListOniOS("Java (programming language)", name_of_folder);
        } else {
            articlePageObject.addArticleToWatchlist();
        }

        if (!Platform.getInstance().isMW()) {
            navigationUI.pressNavigateUp();
        }

        if (Platform.getInstance().isAndroid()) {
            navigationUI.pressNavigateUp();
        } else if (Platform.getInstance().isIOS()) {
            navigationUI.pressClose();
        }

        navigationUI.clickMyListButton();
        navigationUI.closeAllPopups();
        if (Platform.getInstance().isIOS()) {
            savedPageObject.switchToReadingLists();
        }
        if (!Platform.getInstance().isMW()) {
            savedPageObject.openFolderByName(name_of_folder);
            navigationUI.closeAllPopups();
            String title = "Java (programming language)";
            savedPageObject.swipeArticleToDelete(title);
        } else {
            articlePageObject.removeArticleFromSaved();
        }
    }


    @Test
    public void testOnlyDeletedArticleGetsDeleted() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        SavedPageObject savedPageObject = SavedPageObjectFactory.get(driver);

        navigationUI.closeAllPopups();
        searchPageObject.initSearchInput();

        String[] words = {"Apple", "Banana", "Cherry", "Grape", "Tomato", "Melon"};
        List<String> list_of_queries = new ArrayList<>(Arrays.asList(words));
        Random random = new Random();
        String search_query1 = list_of_queries.remove(random.nextInt(list_of_queries.size()));
        String search_query2 = list_of_queries.remove(random.nextInt(list_of_queries.size()));

        searchPageObject.typeInSearchLine(search_query1);
        searchPageObject.clickOnFirstArticle();

        String name_of_folder = "To eat";
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewListOnAndroid(name_of_folder);
        } else {
            articlePageObject.addArticleToNewListOniOS(search_query1, name_of_folder);
        }
        navigationUI.pressNavigateUp();
        searchPageObject.clearSearchInput(search_query1);
        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine(search_query2);
        searchPageObject.clickOnFirstArticle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingListOnAndroid(name_of_folder);
        } else {
            articlePageObject.addArticleToExistingListOniOS(search_query2, name_of_folder);
        }
        navigationUI.pressNavigateUp();

        if (Platform.getInstance().isAndroid()) {
            navigationUI.pressNavigateUp();
        } else {
            navigationUI.pressClose();
        }

        navigationUI.clickMyListButton();
        if (Platform.getInstance().isIOS()) {
            savedPageObject.switchToReadingLists();
        }
        savedPageObject.openFolderByName(name_of_folder);
        navigationUI.closeAllPopups();
        savedPageObject.swipeArticleToDelete(search_query1);
        savedPageObject.waitForArticleToAppearByTitle(search_query2);
        savedPageObject.clickArticleByTitle(search_query2);
        String article_title = articlePageObject.getArticleTitle(search_query2);
        assertEquals(
                search_query2,
                article_title);
    }
}