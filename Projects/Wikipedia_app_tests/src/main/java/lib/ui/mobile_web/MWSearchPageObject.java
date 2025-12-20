package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css~#searchIcon";
        SEARCH_INPUT = "css~#searchInput";
        SEARCH_CANCEL_BUTTON = "css~a.BUTTON";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath~//div[contains(@class, 'wikipedia-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css~li.cdx-menu-item";
        FIRST_SEARCH_RESULT = "css~li.cdx-menu-item";
        RESULT_TITLE_CONTAINER = "css~.cdx-menu-item__text__label";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
