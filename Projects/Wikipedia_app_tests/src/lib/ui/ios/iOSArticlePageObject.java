package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE_BY_SUBSTRING_TPL = "id~{SUBSTRING}";
        FOOTER = "id~View article in browser";
        SAVE_BUTTON = "id~Save for later";
        ADD_TO_MY_LIST_BUTTON = "id~Add “{SUBSTRING}” to a reading list?";
        CREATE_NEW_LIST_BUTTON =  "xpath~//XCUIElementTypeButton[@name='Create a new list']";
        MY_LIST_NAME_INPUT = "xpath~//XCUIElementTypeTextField[@value='reading list title']";
        EXISTING_LIST = "xpath~//*[@resource-id='org.wikipedia:id/item_title' and @text='{SUBSTRING}']";
        CONFIRM_ADDING_LIST = "xpath~//XCUIElementTypeStaticText[@name='Create reading list']";
    }
    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    };
}
