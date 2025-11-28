package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clickIfPresent(String locator, int timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            element.click();
        } catch (TimeoutException e) {
            System.out.println("Could not find element by locator: " + by);
        }
    }


    public WebElement waitForElementPresent(String locator, String error_message, Duration timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, Duration.ofSeconds(5));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, Duration timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, Duration timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, Duration timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, Duration timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator, "Cannot find element", Duration.ofSeconds(5));
        String actual_text = element.getAttribute("text");
        Assert.assertEquals("Texts do not match", actual_text, expected_text);
        return element;
    }

    public void swipeUp(int timeOfSwipe)
    {
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, start_y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(200)));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, end_y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).isEmpty()){
            if (already_swiped >= max_swipes){
                waitForElementNotPresent(
                        locator,
                        "Cannot find element by swiping up. \n" + error_message,
                        Duration.ofSeconds(0));
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator, String error_message)
    {
        WebElement element = waitForElementPresent(
                locator,
                error_message,
                Duration.ofSeconds(5));

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), right_x, middle_y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(200)));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), left_x, middle_y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }

    public int getNumberOfElements(By by)
    {
        List elements;
        elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getNumberOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + ' ' + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, Duration timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementPresent(By by, String error_message)
    {
        int amount_of_elements = getNumberOfElements(by);
        if (amount_of_elements < 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + ' ' + error_message);
        }
    }

    public By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote("~"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
}