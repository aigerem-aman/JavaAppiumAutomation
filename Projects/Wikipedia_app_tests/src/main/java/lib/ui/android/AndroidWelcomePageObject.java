package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject {
    static{

//        STEP_LEARN_MORE_LINK = "xpath~//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
//        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id~New ways to explore";
//        STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT = "xpath~//XCUIElementTypeStaticText[@name='Add or edit preferred languages']";
//        STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK = "xpath~//XCUIElementTypeStaticText[@name='Learn more about our privacy policy and terms of use']";
//        NEXT_LINK = "xpath~//XCUIElementTypeStaticText[@name='Next']";
        GET_STARTED_BUTTON = "id~org.wikipedia:id/fragment_onboarding_done_button";
        SKIP_BUTTON = "xpath~//*[contains(@text, 'Skip')]";
        PRIMARY_TEXT_LOCATOR = "id~org.wikipedia:id/primaryTextView";
    }
    public  AndroidWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }


}
