package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        NAVIGATE_UP_BUTTON = "";
        MY_LIST_BUTTON = "";
        CLOSE_BUTTON = "";
        GOT_IT_BUTTON = "";
        DONE_BUTTON = "";
        SKIP_BUTTON = "";

    }
    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    };

}
