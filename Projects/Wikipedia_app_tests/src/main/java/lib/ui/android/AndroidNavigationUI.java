package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        NAVIGATE_UP_BUTTON = "xpath~//android.widget.ImageButton[@content-desc='Navigate up']";
        MY_LIST_BUTTON = "xpath~(//android.widget.LinearLayout[@resource-id='org.wikipedia:id/navigation_bar_item_content_container'])[2]";
        CLOSE_BUTTON = "xpath~//android.widget.ImageView[@content-desc='Close']";
        GOT_IT_BUTTON = "xpath~//*[contains(@text, 'Got it')]";
       DONE_BUTTON =  "xpath~//*[contains(@text, 'Done')]";
       SKIP_BUTTON =  "xpath~//*[contains(@text, 'Skip')]";
    }

    public  AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}