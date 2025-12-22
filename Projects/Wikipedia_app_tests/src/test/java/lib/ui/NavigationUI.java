package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            NAVIGATE_UP_BUTTON,
            MY_LIST_BUTTON,
            CLOSE_BUTTON,
            GOT_IT_BUTTON,
            DONE_BUTTON,
            SKIP_BUTTON;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void pressNavigateUp() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find Navigate up button",
                5);
    }

    public void pressClose(){
        this.waitForElementAndClick(
                CLOSE_BUTTON,
                "Cannot find Close button",
                5);
}

    public void clickMyListButton() {
        this.waitForElementAndClick(
                MY_LIST_BUTTON,
                "Cannot find Save button",
                5);
    }

    public void closeAllPopups() {
        this.clickWhilePresent(SKIP_BUTTON, 5);
        this.clickWhilePresent(DONE_BUTTON, 5);
        this.clickWhilePresent(CLOSE_BUTTON, 5);
        this.clickWhilePresent(GOT_IT_BUTTON, 5);
    }
}



