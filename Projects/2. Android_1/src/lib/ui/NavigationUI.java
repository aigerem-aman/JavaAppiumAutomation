package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.time.Duration;

public class NavigationUI extends MainPageObject {

    private static final String
            NAVIGATE_UP_BUTTON = "xpath~//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            MY_LIST_BUTTON = "xpath~(//android.widget.LinearLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_content_container\"])[2]",
            SKIP_BUTTON = "xpath~//*[contains(@text, 'Skip')]",
            CLOSE_BUTTON = "xpath~//android.widget.ImageView[@content-desc=\"Close\"]",
            GOT_IT_BUTTON = "xpath~//*[contains(@text, 'Got it')]";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void pressNavigateUp() {
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));
    }

    public void clickMyListButton() {
        this.waitForElementAndClick(
                MY_LIST_BUTTON,
                "Cannot find Save button",
                Duration.ofSeconds(5));
    }

    public void pressSkipButtonIfPresent()
    {
        this.clickIfPresent(SKIP_BUTTON, 2);
    }

    public void pressCloseButtonIfPresent()
    {
        this.clickIfPresent(CLOSE_BUTTON, 2);
    }

    public void pressGotItButtonIfPresent()
    {
        this.clickIfPresent(GOT_IT_BUTTON, 2);
    }
}



