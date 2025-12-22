package lib.ui.ios;

import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSWelcomePageObject extends WelcomePageObject {

    static {
        STEP_LEARN_MORE_LINK = "xpath~//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
                STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id~New ways to explore";
                STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT = "xpath~//XCUIElementTypeStaticText[@name='Add or edit preferred languages']";
                STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK = "xpath~//XCUIElementTypeStaticText[@name='Learn more about our privacy policy and terms of use']";
                NEXT_LINK = "xpath~//XCUIElementTypeStaticText[@name='Next']";
                GET_STARTED_BUTTON = "xpath~//XCUIElementTypeButton[@name='Get started']";
                SKIP_BUTTON = "xpath~//XCUIElementTypeStaticText[@name='Skip']";
    }

    public iOSWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
