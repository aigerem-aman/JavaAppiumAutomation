package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            NAVIGATE_UP_BUTTON,
            MY_LIST_BUTTON,
            NAVIGATION_BUTTON,
            CLOSE_BUTTON,
            GOT_IT_BUTTON,
            DONE_BUTTON,
            SKIP_BUTTON,
            LOGIN_SIDEBAR_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Pressing 'Navigate up'")
    public void pressNavigateUp() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find Navigate up button",
                5);
    }

    @Step("Pressing 'Close'")
    public void pressClose(){
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot find Close button",
                5);
    }

    @Step("Opening Saved articles")
    public void clickMyListButton() {
        if (!Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    MY_LIST_BUTTON,
                    "Cannot find Save button",
                    5);
        } else {
            this.openSidebar();
            this.waitForElementAndClick(
                    MY_LIST_BUTTON,
                    "Cannot find Watchlist button",
                    5);
        }
    }

    @Step("Closing all popups")
    public void closeAllPopups() {
        if (!Platform.getInstance().isMW()) {
            this.clickWhilePresent(SKIP_BUTTON, 5);
            this.clickWhilePresent(DONE_BUTTON, 5);
            this.clickWhilePresent(CLOSE_BUTTON, 5);
            this.clickWhilePresent(GOT_IT_BUTTON, 5);
        }
    }

    @Step("Opening side bar")
    public void openSidebar() {
        this.tryClickElementWithFewAttempts(
                NAVIGATION_BUTTON,
                "Cannot find Navigation Button",
                10);
    }
}



