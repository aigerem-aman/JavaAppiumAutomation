package test;

import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lib.Platform;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {

        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    public void tearDown()  {
        driver.quit();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else if (driver instanceof IOSDriver) {
            ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }


    protected void backgroundApp(int seconds) {
        if (driver instanceof InteractsWithApps) {
            ((InteractsWithApps) driver)
                    .runAppInBackground(Duration.ofSeconds(seconds));
            rotateScreenPortrait();
        } else {
            System.out.println(
                    "backgroundApp does nothing for platform "
                            + Platform.getInstance().getPlatformVar()
            );
        }
    }



    protected void openWikiWebPageForMobileWeb() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.wikipedia.org/?mobileaction=toggle_view_mobile");
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

}
