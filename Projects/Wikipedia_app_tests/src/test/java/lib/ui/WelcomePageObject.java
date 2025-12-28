package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for 'Learn more' link")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK ,
                "Cannot find 'Learn more about Wikipedia'",
                10);
    }

    @Step("Waiting for 'New ways to explore' text")
    public void waitForNewWaysToExploreText()
    {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT ,
                "Cannot find 'New ways to explore'",
                10);
    }

    @Step("Waiting for 'Add or edit preferred languages'")
    public void waitForAddOrEditPreferredLanguagesText()
    {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE_TEXT,
                "Cannot find 'Add or edit preferred languages'",
                10);
    }

    @Step("Waiting for 'Learn more about our privacy policy and terms of use' link")
    public void waitForLearnMoreAboutOurPrivacyPolicyLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_OUR_PRIVACY_LINK,
                "Cannot find 'Learn more about our privacy policy and terms of use'",
                15);
    }

    @Step("Clicking 'Next' button")
    public void ClickNextButton()
    {
        this.waitForElementAndClick(
                NEXT_LINK ,
                "Cannot find 'Next' button'",
                10);
    }

    @Step("Clicking 'Get started' button")
    public void ClickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find 'Get started' button'",
                10);
    }

    @Step("Clicking 'Skip' button")
    public void clickSkipButton()
    {
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find 'Skip' button",
                5);
    }

    @Step("Waiting for main text on page")
    public void waitForPrimaryText(String expected_text) {
        String actual_text = waitForElementAndGetAttribute(
                PRIMARY_TEXT_LOCATOR,
                "text",
                "Cannot find primary text locator",
                10);
        assertElementHasText(
                PRIMARY_TEXT_LOCATOR,
                expected_text,
                "Text: " + actual_text + " did not match expected text: " + expected_text);
    }

    @Step("Swiping through welcome pages")
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
