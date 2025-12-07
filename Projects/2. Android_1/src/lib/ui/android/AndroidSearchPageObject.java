package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath~//*[contains(@text, 'Search Wikipedia')]";
                SEARCH_INPUT = "xpath~//*[contains(@text, 'Search Wikipedia')]";
                SEARCH_CANCEL_BUTTON = "id~org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath~//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING}']";
                SEARCH_RESULT_ELEMENT = "xpath~//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
                FIRST_SEARCH_RESULT = "xpath~//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]";
                RESULT_TITLE_CONTAINER = "id~org.wikipedia:id/page_list_item_title";
                EMPTY_RESULTS_CONTAINER = "xpath~//*[@resource-id='org.wikipedia:id/results_text']";
                EMPTY_RESULTS_TEXT = "No results";

    }

    public  AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
