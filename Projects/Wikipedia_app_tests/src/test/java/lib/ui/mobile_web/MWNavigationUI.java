package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        NAVIGATE_UP_BUTTON = "";
        MY_LIST_BUTTON = "css~a[href*='Special:EditWatchlist']";
        NAVIGATION_BUTTON = "css~#main-menu-input";
        CLOSE_BUTTON = "";
        GOT_IT_BUTTON = "";
        DONE_BUTTON = "";
        SKIP_BUTTON = "";
        LOGIN_SIDEBAR_BUTTON = "";
    }
    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }

}
