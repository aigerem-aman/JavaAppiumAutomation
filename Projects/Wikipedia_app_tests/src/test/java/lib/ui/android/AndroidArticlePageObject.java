package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_BY_SUBSTRING_TPL = "xpath~//android.widget.TextView[@text='{SUBSTRING}']";
                FOOTER = "xpath~//*[@text='View article in browser']";
                SAVE_BUTTON = "id~org.wikipedia:id/page_save";
                ADD_TO_MY_LIST_BUTTON = "xpath~//*[contains(@text, 'Add to list')]";
                MY_LIST_NAME_INPUT = "id~org.wikipedia:id/text_input";
                EXISTING_LIST = "xpath~//*[@resource-id='org.wikipedia:id/item_title' and @text='{SUBSTRING}']";
                CONFIRM_ADDING_LIST = "xpath~//*[contains(@text,'OK')]";

    }
    public  AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
