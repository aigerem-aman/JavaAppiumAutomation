package lib.ui.Factories;

import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.android.AndroidWelcomePageObject;
import lib.ui.ios.iOSWelcomePageObject;
import lib.ui.mobile_web.MWWelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObjectFactory extends WelcomePageObject {
    static{
        SKIP_BUTTON = "//XCUIElementTypeStaticText[@name='Skip']";
    }

    public WelcomePageObjectFactory(RemoteWebDriver driver) {
        super(driver);
    }

    public static WelcomePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidWelcomePageObject(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSWelcomePageObject(driver);
        } else {
            return new MWWelcomePageObject(driver);
        }
    }
}
