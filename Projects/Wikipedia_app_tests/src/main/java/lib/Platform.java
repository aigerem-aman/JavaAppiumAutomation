package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredOptions());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    private BaseOptions<?> getAndroidDesiredOptions() throws Exception {
        String platform = System.getenv("PLATFORM").toLowerCase();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("AndroidTestDevice");
        options.setAutomationName("uiautomator2");
        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setApp("/Users/aamanzhulova/Desktop/JavaAppiumAutomation/APKs/org_wikipedia_v2.7.50552r20251015-3.apk");

        return options;
    }

    private BaseOptions<?> getIOSDesiredOptions() throws Exception {
        String platform = System.getenv("PLATFORM").toLowerCase();

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setDeviceName("iPhone 17 Pro");
        options.setAutomationName("XCUITest");
        options.setApp("/Users/aamanzhulova/Library/Developer/Xcode/DerivedData/Wikipedia-cvglufkzkfrbmgdzmluqzxatsgvt/Build/Products/Debug-iphonesimulator/Wikipedia.app");

        return options;
    }

    private boolean isPlatform(String my_platform) {
        String  platform = this.getPlatformVar();
        return my_platform.equalsIgnoreCase(platform);
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM").toLowerCase();
    }
}






