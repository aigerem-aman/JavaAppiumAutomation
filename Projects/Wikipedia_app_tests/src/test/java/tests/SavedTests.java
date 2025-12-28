package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import test.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.Factories.ArticlePageObjectFactory;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SavedPageObjectFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SavedPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Epic("Tests for Saved articles")
public class SavedTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    @Features(value={@Feature(value="Article"), @Feature(value="Saved")})
    @DisplayName("Delete article from Saved")
    @Description("We add article to Saved and then delete it from the list")
    @Severity(value=SeverityLevel.CRITICAL)
    @Step("Starting test testSaveArticleToMyListThenDeleteArticle")
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
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        navigationUI.clickMyListButton();
        navigationUI.closeAllPopups();
        if (Platform.getInstance().isIOS()) {
            savedPageObject.switchToReadingLists();
        }
        if (!Platform.getInstance().isMW()) {
            savedPageObject.openFolderByName(name_of_folder);
            navigationUI.closeAllPopups();
        }
            String title = "Java (programming language)";
            savedPageObject.deleteArticleFromSaved(title);

            if (Platform.getInstance().isMW()) {
                driver.navigate().refresh();
            }
    }

    @Test
    @Features(value={@Feature(value="Article"), @Feature(value="Saved")})
    @DisplayName("Only Deleted Article Gets Deleted")
    @Description("We add 2 articles to Saved and then delete one from the list")
    @Severity(value=SeverityLevel.CRITICAL)
    @Step("Starting test testOnlyDeletedArticleGetsDeleted")
    public void testOnlyDeletedArticleGetsDeleted() {
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
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticleToNewListOniOS(search_query1, name_of_folder);
        } else {
            articlePageObject.addArticleToWatchlist();
        }
        if (!Platform.getInstance().isMW()) {
            navigationUI.pressNavigateUp();
            searchPageObject.clearSearchInput(search_query1);
        }

        searchPageObject.initSearchInput();
        searchPageObject.typeInSearchLine(search_query2);
        searchPageObject.clickOnFirstArticle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToExistingListOnAndroid(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            articlePageObject.addArticleToExistingListOniOS(search_query2, name_of_folder);
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
        if (Platform.getInstance().isIOS()) {
            savedPageObject.switchToReadingLists();
        }

        if (!Platform.getInstance().isMW()) {
            savedPageObject.openFolderByName(name_of_folder);
            navigationUI.closeAllPopups();
        }
        savedPageObject.deleteArticleFromSaved(search_query1);

        savedPageObject.waitForArticleToAppearByTitle(search_query2);
    }
}