package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class CoreTestCase {

    protected AppiumDriver driver;

    @Before
    public void setUp() throws Exception {


        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
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
        driver.runAppInBackground(Duration.ofSeconds(seconds));
        rotateScreenPortrait();
    }

}
