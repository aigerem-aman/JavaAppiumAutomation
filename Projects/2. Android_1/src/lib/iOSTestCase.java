package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

import java.net.URL;
import java.util.Map;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("iOS");
        options.setDeviceName("iPhone 17 Pro");
        options.setAutomationName("XCUITest");
        options.setApp("/Users/aamanzhulova/Library/Developer/Xcode/DerivedData/Wikipedia-cvglufkzkfrbmgdzmluqzxatsgvt/Build/Products/Debug-iphonesimulator/Wikipedia.app");


        driver = new IOSDriver(new URL(AppiumURL), options);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroudApp(int seconds)
    {
        driver.executeScript("mobile: backgroundApp", Map.of("seconds", seconds));
        rotateScreenPortrait();
    }
}
