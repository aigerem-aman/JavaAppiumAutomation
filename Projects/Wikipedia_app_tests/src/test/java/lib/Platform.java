package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private URL getAppiumUrl() throws Exception {
        String port = System.getenv("APPIUM_PORT");

        if (port == null || port.isEmpty()) {
            throw new Exception("APPIUM_PORT environment variable is not set");
        }
        return new URL("http://127.0.0.1:" + port);
    }


    public RemoteWebDriver getDriver() throws Exception {
        if (this.isAndroid()) {
            URL url = this.getAppiumUrl();
            return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            URL url = this.getAppiumUrl();
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        } else if (this.isMW()) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    new File("drivers/chrome/chromedriver").getAbsolutePath()
            );
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("app", "/Users/aamanzhulova/Desktop/JavaAppiumAutomation/APKs/org_wikipedia_v2.7.50552r20251015-3.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 17 Pro");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/aamanzhulova/Library/Developer/Xcode/DerivedData/Wikipedia-cvglufkzkfrbmgdzmluqzxatsgvt/Build/Products/Debug-iphonesimulator/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 390);
        deviceMetrics.put("height", 844);
        deviceMetrics.put("pixelation", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 13; Pixel 7)" +
                "AppleWebKit/537.36 (KHTML, like Gecko)" +
                "Chrome/120.0.0.0 Mobile Safari/537.36");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-request-desktop-site");
        chromeOptions.addArguments("window-size=390,844");

        return chromeOptions;
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equalsIgnoreCase(platform);
    }

    public String getPlatformVar() {
        String platform = System.getenv("PLATFORM");
        return platform != null ? platform.toLowerCase() : "";
    }
}
