package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath~//XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath~//XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id~Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id~{SUBSTRING}";
        SEARCH_RESULT_ELEMENT = "xpath~//XCUIElementTypeCell[@visible='true']";
        FIRST_SEARCH_RESULT =
                "xpath~(//XCUIElementTypeOther[@name='Activate to dismiss']" +
                        "//XCUIElementTypeCollectionView//XCUIElementTypeCell[@visible='true'])[1]";
        RESULT_TITLE_CONTAINER = "id~org.wikipedia:id/page_list_item_title";
        EMPTY_RESULTS_CONTAINER = "id~No results found";
        EMPTY_RESULTS_TEXT = "No results found";
    }

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
