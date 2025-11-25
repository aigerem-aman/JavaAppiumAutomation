package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;

import java.net.URL;
import java.util.Map;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("AndroidTestDevice");
        options.setAutomationName("uiautomator2");
        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setApp("/Users/aamanzhulova/Desktop/JavaAppiumAutomation/APKs/org_wikipedia_v2.7.50552r20251015-3.apk");


        driver = new AndroidDriver(new URL(AppiumURL), options);
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
