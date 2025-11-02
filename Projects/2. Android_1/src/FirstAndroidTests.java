import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirstAndroidTests {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("AndroidTestDevice");
        options.setAutomationName("uiautomator2");
        options.setAppPackage("org.wikipedia");
        options.setAppActivity("org.wikipedia.main.MainActivity");
        options.setApp("/Users/aamanzhulova/Desktop/JavaAppiumAutomation/APKs/org_wikipedia_v2.7.50552r20251015-3.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultShouldContainExpected() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search bar",
                Duration.ofSeconds(5));

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementPresent(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot find search result",
                Duration.ofSeconds(15));
    }

    @Test
    public void cancelSearchButtonShouldCancelSearch() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X button is still present",
                Duration.ofSeconds(5));
    }

    @Test
    public void articleTitleShouldContainExpected() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
                "Cannot find search result",
                Duration.ofSeconds(15));

        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_web_view"),
                "text",
                Duration.ofSeconds(5));

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find article title",
                Duration.ofSeconds(15));
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Java (programming language)",
                article_title);
    }

    @Test
    public void textInSearchBarShouldBeExpected() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        assertElementHasText(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Search Wikipedia",
                "Texts do not match");
    }

    @Test
    public void SearchResultsArePluralAndDisappearAfterCancel()
    {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search bar",
                Duration.ofSeconds(5));

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementPresent(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "There is one or less search results",
                Duration.ofSeconds(15));

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X button to cancel search",
                Duration.ofSeconds(5));

        waitForElementNotPresent(
                By.id("//androidx.recyclerview.widget.RecyclerView[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Search results is still present",
                Duration.ofSeconds(5));
    }

    public void clickIfPresent(By by, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            element.click();
        } catch (TimeoutException e) {
            System.out.println("Could not find element by locator: " + by);
        }
    }

    private WebElement waitForElementPresent(By by, String error_message, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, Duration.ofSeconds(5));
    }

    private WebElement waitForElementAndClick(By by, String error_message, Duration timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, Duration timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClear(By by, String error_message, Duration timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    private WebElement assertElementHasText(By by, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(by, "Cannot find element", Duration.ofSeconds(5));
        String actual_text = element.getAttribute("text");
        Assert.assertEquals("Texts do not match", actual_text, expected_text);
        return element;
    }
}

