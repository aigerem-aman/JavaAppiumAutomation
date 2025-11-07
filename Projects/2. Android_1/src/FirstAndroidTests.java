import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
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
        clickIfPresent(By.id("org.wikipedia:id/page_web_view"),5);

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
    public void SearchResultsArePluralAndDisappearAfterCancel() {
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

    @Test
    public void SearchResultsShouldContainSearchQuery() {
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

        List<WebElement> results = driver.findElements(By.xpath(
                "//*[contains(@resource-id, 'org.wikipedia:id/page_list_item_title')]"));

        for (WebElement element : results) {
            String title = element.getAttribute("text");
            Assert.assertTrue(
                    "Текст '" + title + "' не содержит ожидаемое слово 'Java'",
                    title.toLowerCase().contains("java")
            );
        }
    }

    @Test
    public void ArticleCanBeSwipedUp() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search results",
                Duration.ofSeconds(15));

        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);
        clickIfPresent(By.id("org.wikipedia:id/page_web_view"),5);

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );

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

    private WebElement waitForElementAndClear(By by, String error_message, Duration timeoutInSeconds) {
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

    protected void swipeUp(int timeOfSwipe)
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

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).isEmpty()){
            if (already_swiped >= max_swipes){
                waitForElementNotPresent(
                        by,
                        "Cannot find element by swiping up. \n" + error_message,
                        Duration.ofSeconds(0));
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }


}

