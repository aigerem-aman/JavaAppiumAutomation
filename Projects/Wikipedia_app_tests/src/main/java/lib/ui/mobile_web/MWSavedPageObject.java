package lib.ui.mobile_web;

import lib.ui.SavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedPageObject extends SavedPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath~//*[@name='{title}']/..";
        REMOVE_BY_TITLE_TPL = "xpath~//li[@title='{title}']//a[contains(@href,'action=unwatch')]";
//                "xpath~//ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class, 'watched')]";
        DELETE_ARTICLE_BUTTON = "css~a[href*='action=unwatch']";
    }

    public MWSavedPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
