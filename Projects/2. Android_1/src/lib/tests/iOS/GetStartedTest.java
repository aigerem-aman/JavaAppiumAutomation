package lib.tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {
    @Test
    public void testPassTroughWelcome() {
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.ClickNextButton();

        welcomePage.waitForNewWaysToExploreText();
        welcomePage.ClickNextButton();

        welcomePage.waitForAddOrEditPreferredLanguagesText();
        welcomePage.ClickNextButton();

        welcomePage.waitForLearnMoreAboutOurPrivacyPolicyLink();
        welcomePage.ClickGetStartedButton();
    }
}
