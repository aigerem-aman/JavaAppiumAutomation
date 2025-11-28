package lib.ui;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    STEP_LEARN_MORE_LINK = "xpath~//XCUIElementTypeStaticText[@name=\"Learn more about Wikipedia\"]",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id~New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT = "xpath~//XCUIElementTypeStaticText[@name=\"Add or edit preferred languages\"]",
    STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK = "xpath~//XCUIElementTypeStaticText[@name=\"Learn more about our privacy policy and terms of use\"]",
    NEXT_LINK = "xpath~//XCUIElementTypeStaticText[@name=\"Next\"]",
    GET_STARTED_BUTTON = "xpath~//XCUIElementTypeButton[@name=\"Get started\"]";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK ,
                "Cannot find 'Learn more about Wikipedia'",
                Duration.ofSeconds(10));
    }
    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT ,
                "Cannot find 'New ways to explore'",
                Duration.ofSeconds(10));
    }
    public void waitForAddOrEditPreferredLanguagesText()
    {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT,
                "Cannot find 'Add or edit preferred languages'",
                Duration.ofSeconds(10));
    }
    public void waitForLearnMoreAboutOurPrivacyPolicyLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK,
                "Cannot find 'Learn more about our privacy policy and terms of use'",
                Duration.ofSeconds(15));
    }

    public void ClickNextButton()
    {
        this.waitForElementAndClick(
                NEXT_LINK ,
                "Cannot find 'Next' button'",
                Duration.ofSeconds(10));
    }
    public void ClickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find 'Get started' button'",
                Duration.ofSeconds(10));
    }

}
