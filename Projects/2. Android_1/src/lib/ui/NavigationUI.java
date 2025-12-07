package lib.ui;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            NAVIGATE_UP_BUTTON,
            MY_LIST_BUTTON,
            CLOSE_BUTTON,
            GOT_IT_BUTTON,
            DONE_BUTTON,
            SKIP_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void pressNavigateUp() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));
    }

    public void pressClose(){
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot find Close button",
                Duration.ofSeconds(5));
}

    public void clickMyListButton() {
        this.waitForElementAndClick(
                MY_LIST_BUTTON,
                "Cannot find Save button",
                Duration.ofSeconds(5));
    }

    public void closeAllPopups() {
        this.clickWhilePresent(SKIP_BUTTON, 5);
        this.clickWhilePresent(DONE_BUTTON, 5);
        this.clickWhilePresent(CLOSE_BUTTON, 5);
        this.clickWhilePresent(GOT_IT_BUTTON, 5);
    }
}



