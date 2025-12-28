package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import test.CoreTestCase;
import lib.Platform;
import lib.ui.Factories.NavigationUIFactory;
import lib.ui.Factories.SearchPageObjectFactory;
import lib.ui.Factories.WelcomePageObjectFactory;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for starting page")
public class GetStartedTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Welcome Page")})
    @DisplayName("Swipe through welcome pages")
    @Description("We go through welcome pages by swiping")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testSwipeThroughWelcome")
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
        }
    }

    @Test
    @Features(value={@Feature(value="Welcome Page")})
    @DisplayName("Pass through welcome pages")
    @Description("We go through welcome pages by pressing buttons to continue")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testPassThroughWelcome")
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
        }
    }

    @Test
    @Features(value={@Feature(value="Welcome Page")})
    @DisplayName("Skip welcome pages")
    @Description("We skip welcome pages")
    @Severity(value=SeverityLevel.MINOR)
    @Step("Starting test testSkipWelcome")
    public void testSkipWelcome() {
        if (!Platform.getInstance().isMW()) {
            WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);
            NavigationUI navigationUI = NavigationUIFactory.get(driver);
            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

            welcomePageObject.clickSkipButton();
            navigationUI.closeAllPopups();
            searchPageObject.searchBarPresent();
        }
    }
}
