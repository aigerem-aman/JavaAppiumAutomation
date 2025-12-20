package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.Factories.WelcomePageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase {
    @Test
    public void testSwipeThroughWelcome() {
        if (Platform.getInstance().isAndroid()) {
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
            NavigationUI navigationUI = NavigationUIFactory.get(driver);
            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

            welcomePageObject.waitForPrimaryText("The Free Encyclopedia\n…in over 300 languages");
            welcomePageObject.swipeOnboardingLeft();
            welcomePageObject.waitForPrimaryText("New ways to explore");
            welcomePageObject.swipeOnboardingLeft();
            welcomePageObject.waitForPrimaryText("Reading lists with sync");
            welcomePageObject.swipeOnboardingLeft();
            welcomePageObject.waitForPrimaryText("Data & Privacy");
            welcomePageObject.ClickGetStartedButton();
            navigationUI.closeAllPopups();
            searchPageObject.searchBarPresent();
        } else {}
    }

    @Test
    public void testPassThroughWelcome() {
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);

            welcomePageObject.waitForLearnMoreLink();
            welcomePageObject.ClickNextButton();

            welcomePageObject.waitForNewWaysToExploreText();
            welcomePageObject.ClickNextButton();

            welcomePageObject.waitForAddOrEditPreferredLanguagesText();
            welcomePageObject.ClickNextButton();

            welcomePageObject.waitForLearnMoreAboutOurPrivacyPolicyLink();
            welcomePageObject.ClickGetStartedButton();
        } else {}
    }

    @Test
    public void testSkipWelcome() {
        if (Platform.getInstance().isMW()) {
        } else {
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
            NavigationUI navigationUI = NavigationUIFactory.get(driver);
            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

            welcomePageObject.clickSkipButton();
            navigationUI.closeAllPopups();
            searchPageObject.searchBarPresent();
        }
    }
}
