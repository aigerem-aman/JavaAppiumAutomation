package lib.ui.Factories;

import lib.Platform;
import lib.ui.SavedPageObject;
import lib.ui.android.AndroidSavedPageObject;
import lib.ui.ios.iOSSavedPageObject;
import lib.ui.mobile_web.MWSavedPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedPageObjectFactory {
    public static SavedPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new iOSSavedPageObject(driver);
        } else {
            return new MWSavedPageObject(driver);
        }
    }
}
