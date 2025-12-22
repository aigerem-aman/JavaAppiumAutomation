package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        NAVIGATE_UP_BUTTON = "id~BackButton";
        MY_LIST_BUTTON = "id~Saved";
        CLOSE_BUTTON = "id~Close";
        GOT_IT_BUTTON = "id~Got it";
        DONE_BUTTON = "id~Done";
        SKIP_BUTTON = "id~Skip";

    }
    public iOSNavigationUI(RemoteWebDriver driver){
        super(driver);
    };
}
