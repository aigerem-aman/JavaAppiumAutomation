package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.URL;
import java.util.Map;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        BaseOptions<?> options = getOptionsByPlatformEnv();
        driver = getDriverByPlatformEnv(options);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        }
    }

    protected void backgroudApp(int seconds) {
        driver.executeScript("mobile: backgroundApp", Map.of("seconds", seconds));
        rotateScreenPortrait();
    }

    private BaseOptions<?> getOptionsByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM").toLowerCase();

        if (platform.equals(PLATFORM_ANDROID)) {
            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName("Android");
            options.setDeviceName("AndroidTestDevice");
            options.setAutomationName("uiautomator2");
            options.setAppPackage("org.wikipedia");
            options.setAppActivity("org.wikipedia.main.MainActivity");
            options.setApp("/Users/aamanzhulova/Desktop/JavaAppiumAutomation/APKs/org_wikipedia_v2.7.50552r20251015-3.apk");

            return options;

        } else if (platform.equals(PLATFORM_IOS)) {
            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("iOS");
            options.setDeviceName("iPhone 17 Pro");
            options.setAutomationName("XCUITest");
            options.setApp("/Users/aamanzhulova/Library/Developer/Xcode/DerivedData/Wikipedia-cvglufkzkfrbmgdzmluqzxatsgvt/Build/Products/Debug-iphonesimulator/Wikipedia.app");

            return options;

        } else {
            throw new Exception("Cannot get platform from env variable. Platform value " + platform);
        }
    }

    private AppiumDriver getDriverByPlatformEnv(BaseOptions<?> options) throws Exception {
        String platform = System.getenv("PLATFORM").toLowerCase();

        if (platform.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(new URL(AppiumURL), options);
        } else if (platform.equals(PLATFORM_IOS)) {
            return new IOSDriver(new URL(AppiumURL), options);
        } else {
            throw new Exception("Cannot get platform from env variable. Platform value " + platform);
        }
    }
}
