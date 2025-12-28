package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void clickWhilePresent(String locator, int timeoutInSeconds) {
        By by = this.getLocatorByString(locator);

        while (true) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

                element.click();

                try {
                    sleep(200);
                } catch (InterruptedException ignored) {}

                if (driver.findElements(by).isEmpty()) {
                    break;
                }

            } catch (TimeoutException e) {
                break;
            }
        }
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementClickable(String locator, String errorMessage, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

        try {
            return wait.until(
                    ExpectedConditions.elementToBeClickable(by)
            );
        } catch (Exception e) {
            throw new AssertionError(errorMessage, e);
        }
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public  WebElement assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator, "Cannot find element", 5);

        String actualText;
        if (Platform.getInstance().isAndroid()) {
            actualText = element.getText();
        } else {
            actualText = element.getAttribute("label");
            if (actualText == null || actualText.isEmpty()) {
                actualText = element.getAttribute("value");
            }
            if (actualText == null || actualText.isEmpty()) {
                actualText = element.getAttribute("name");
            }
        }

        if (!expected_text.equals(actualText)) {
            throw new AssertionError(error_message + ". Actual: " + actualText);
        }
        return element;
    }

    public void swipeUp()
    {
        if (driver instanceof AppiumDriver<?>) {
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, start_y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofMillis(200)));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x, end_y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(List.of(swipe));
        } else {
            System.out.println("Method swipeUp() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }


    public void swipeUpQuick()
    {
        swipeUp();
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
                        5);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes)
    {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;

        while (!isElementVisibleOnScreen(by)) {
            if (already_swiped >= max_swipes) {
                throw new AssertionError(error_message);
            }
            swipeUp();
            already_swiped++;
        }
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor js = driver;
            js.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);

        while (!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped >= max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    private boolean isElementVisibleOnScreen(By by) {
        List<WebElement> elements = driver.findElements(by);
        if (elements.isEmpty()) return false;

        WebElement el = elements.get(0);
        Rectangle rect = el.getRect();

        int screenHeight = driver.manage().window().getSize().height;

        return rect.y >= 0 && (rect.y + rect.height) <= screenHeight;
    }

    public void swipeElementToLeft(String locator, String error_message)
    {
        if (driver instanceof AppiumDriver<?>) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    5);

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
        } else {
            System.out.println("Method swipeUp() does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

    public int getNumberOfElements(By by)
    {
        List elements;
        elements = driver.findElements(by);
        return elements.size();
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote("~"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        return switch (by_type) {
            case "xpath" -> By.xpath(locator);
            case "id" -> By.id(locator);
            case "css" -> By.cssSelector(locator);
            default -> throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        };
    }
    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_by_y = this.waitForElementPresent(
                locator,
                "Cannot find element by locator," + locator,
                15).getLocation().getY();
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y = Integer.parseInt(js_result.toString()) ;
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }

    public boolean isElementPresent(String locator)
    {
        By by = getLocatorByString(locator);
        return getNumberOfElements(by) > 0;
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts)
    {
        int current_attempt = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts) {
            try {
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e) {
                if (current_attempt > amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++current_attempt;
        }

    }
}