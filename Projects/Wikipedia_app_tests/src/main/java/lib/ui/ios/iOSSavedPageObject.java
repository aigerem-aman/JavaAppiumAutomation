package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedPageObject;

public class iOSSavedPageObject extends SavedPageObject {
    static {
        FOLDER_BY_NAME_TPL = "id~{name_of_folder}";
        ARTICLE_BY_TITLE_TPL = "xpath~//*[@name='{title}']/..";
        READING_LISTS_SWITCHER = "id~Reading lists";
        DELETE_ARTICLE_BUTTON = "id~swipe action delete";
    }
    public iOSSavedPageObject(AppiumDriver driver){
        super(driver);
    }
}
