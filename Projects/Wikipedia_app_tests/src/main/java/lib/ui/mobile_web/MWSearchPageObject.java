package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css~#searchIcon";
        SEARCH_INPUT = "css~input[aria-label='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "css~button[aria-label='Close search dialog']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath~//bdi[contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath~//*[contains(@id,'v-2-')]";
        FIRST_SEARCH_RESULT = "xpath~(//ul[@class='cdx-menu__listbox']/li)[1]";
        RESULT_TITLE_CONTAINER = "css~.cdx-menu-item__text__label";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
