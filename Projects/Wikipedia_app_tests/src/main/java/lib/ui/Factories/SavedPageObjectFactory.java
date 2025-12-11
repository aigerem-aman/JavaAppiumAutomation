package lib.ui.Factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SavedPageObject;
import lib.ui.android.AndroidSavedPageObject;
import lib.ui.ios.iOSSavedPageObject;

public class SavedPageObjectFactory {
    public static SavedPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedPageObject(driver);
        } else {
            return new iOSSavedPageObject(driver);
        }
    }
}
