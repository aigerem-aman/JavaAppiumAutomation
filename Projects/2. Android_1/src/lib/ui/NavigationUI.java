package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.time.Duration;

public class NavigationUI extends MainPageObject {

    private static final String
            NAVIGATE_UP_BUTTON = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]",
            MY_LIST_BUTTON = "(//android.widget.LinearLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_content_container\"])[2]",
            SKIP_BUTTON = "//*[contains(@text, 'Skip')]",
            CLOSE_BUTTON = "//android.widget.ImageView[@content-desc=\"Close\"]",
            GOT_IT_BUTTON = "//*[contains(@text, 'Got it')]";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void pressNavigateUp() {
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));
    }

    public void clickMyListButton() {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_BUTTON),
                "Cannot find Save button",
                Duration.ofSeconds(5));
    }

    public void pressSkipButtonIfPresent()
    {
        this.clickIfPresent(By.xpath(SKIP_BUTTON), 2);
    }

    public void pressCloseButtonIfPresent()
    {
        this.clickIfPresent(By.xpath(CLOSE_BUTTON), 2);
    }

    public void pressGotItButtonIfPresent()
    {
        this.clickIfPresent(By.xpath(GOT_IT_BUTTON  ), 2);
    }
}



