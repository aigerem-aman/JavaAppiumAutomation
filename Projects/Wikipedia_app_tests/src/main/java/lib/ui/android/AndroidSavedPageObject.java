package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedPageObject extends SavedPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath~//*[contains(@text, '{name_of_folder}')]";
        ARTICLE_BY_TITLE_TPL = "xpath~//*[@resource-id='org.wikipedia:id/page_list_item_container']/*[contains(@text, '{title}')]";

    }

    public AndroidSavedPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
