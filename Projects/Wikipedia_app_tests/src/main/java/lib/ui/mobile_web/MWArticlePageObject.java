package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_BY_SUBSTRING_TPL = "xpath~//h1[@id='firstHeading']/span[contains(text(),'{SUBSTRING}')]";
        FOOTER = "css~footer";
        SAVE_BUTTON = "css~#ca-watch";
        REMOVE_FROM_MY_LIST = "css~a[href*='action=unwatch']";

    }
    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
