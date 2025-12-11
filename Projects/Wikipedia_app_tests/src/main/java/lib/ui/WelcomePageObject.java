package lib.ui;


import io.appium.java_client.AppiumDriver;
import junit.framework.Assert;
import lib.Platform;

import java.time.Duration;

abstract public class WelcomePageObject extends MainPageObject
{
    protected static  String
    STEP_LEARN_MORE_LINK,
    STEP_NEW_WAYS_TO_EXPLORE_TEXT,
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT ,
    STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK,
    PRIMARY_TEXT_LOCATOR,
    NEXT_LINK,
    GET_STARTED_BUTTON,
    SKIP_BUTTON;

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

    public void clickSkipButton()
    {
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find 'Skip' button",
                Duration.ofSeconds(5));
    }

    public void waitForPrimaryText(String expected_text) {
        String actual_text = waitForElementAndGetAttribute(
                PRIMARY_TEXT_LOCATOR,
                "text",
                "Cannot find primary text locator",
                Duration.ofSeconds(10));
        assertElementHasText(
                PRIMARY_TEXT_LOCATOR,
                expected_text,
                "Text: " + actual_text + " did not match expected text: " + expected_text);
    }

    public void swipeOnboardingLeft() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(PRIMARY_TEXT_LOCATOR, "Cannot swipe left");
//        } else {
//            this.swipeElementToLeft(STEP_LEARN_MORE_LINK, "Cannot swipe 'Learn more' page");
//            this.swipeElementToLeft(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot swipe 'New ways to explore' page");
//            this.swipeElementToLeft(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT, "Cannot swipe 'Add or edit language' page");
//            this.swipeElementToLeft(STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK, "Cannot swipe 'Privacy' page");
            }
        }
    }
