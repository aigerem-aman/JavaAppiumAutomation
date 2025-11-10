import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
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
    public void searchResultsArePluralAndDisappearAfterCancel() {
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
    public void searchResultsShouldContainSearchQuery() {
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
    public void articleCanBeSwipedUp() {
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

        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveArticleToMyListThenDeleteArticle() {
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
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find list name input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_content_container\"])[2]"),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Couldn't swipe list button",
                Duration.ofSeconds(5));

        clickIfPresent(By.xpath("//*[contains(@text, 'Got it')]"), 3);

        swipeElementToLeft(
                By.xpath("//*[contains (@text, 'Java (programming language)')]"),
                "Cannot find the article to delete");

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete the saved article",
                Duration.ofSeconds(5));
    }

    @Test
    public void articlesAreFoundWhenQueryIsCorrect() {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        String search_query = "Linkin park Discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_query,
                "Cannot find search input field",
                Duration.ofSeconds(5));

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find search result by the request " + search_query,
                Duration.ofSeconds(15));

        int amount_of_articles_found = getNumberOfElements(By.xpath(search_result_locator));

        Assert.assertTrue(amount_of_articles_found > 0);
    }

    @Test
    public void noArticlesAreFoundWhenQueryIsNotCorrect()
    {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        String search_query = "xsdfsfsdfsfsdf";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_query,
                "Cannot find search input field",
                Duration.ofSeconds(5));

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty result label by the request " + search_query,
                Duration.ofSeconds(15));

        assertElementNotPresent(
                By.xpath(search_result_locator),
                "we found some results by request " + search_query);
    }

//    @Test
//    public void articleTitleShouldNotChangeWhenRotated()
//    {
//        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
//        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);
//
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                "Cannot find search field",
//                Duration.ofSeconds(5));
//
//        String search_query = "Java";
//        waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                search_query,
//                "Cannot find search input field",
//                Duration.ofSeconds(5));
//
//        waitForElementAndClick(
//                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[2]//*[@text='Object-oriented programming language']"),
//                "Cannot find search result by the request " + search_query,
//                Duration.ofSeconds(15));
//
//        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);
//        clickIfPresent(By.id("org.wikipedia:id/page_web_view"),5);
//
//        String title_before_rotation = waitForElementAndGetAttribute(
//                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
//                "text",
//                "Cannot find article title",
//                Duration.ofSeconds(15));
//
//        rotateDevice(true);
//
//        String title_after_rotation = waitForElementAndGetAttribute(
//                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
//                "text",
//                "Cannot find article title",
//                Duration.ofSeconds(15));
//
//        Assert.assertEquals(
//                "The title of the article is not the same after rotation",
//                title_before_rotation,
//                title_after_rotation);
//
//    }

    @Test
    public void onlyDeletedArticleGetsDeleted()
    {
        clickIfPresent(By.xpath("//*[contains(@text, 'Skip')]"), 3);
        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search field",
                Duration.ofSeconds(5));

        String[] words = {"Apple", "Banana", "Cherry", "Grape", "Tomato", "Melon"};
        List<String> list = new ArrayList<>(Arrays.asList(words));
        Random random = new Random();
        String search_query1 = words[random.nextInt(words.length)];
        list.remove(search_query1);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_query1,
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[1]"),
                "Cannot find search result",
                Duration.ofSeconds(15));

        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        String name_of_folder = "To eat";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot find list name input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'OK')]"),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));

        waitForElementAndClear(
                By.xpath("//*[contains(@text, '" +  search_query1 + "')]"),
                "Cannot find " + search_query1 + "in search input field",
                Duration.ofSeconds(5));

        String search_query2 = words[random.nextInt(words.length)];

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_query2,
                "Cannot find search input field",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/search_results_list\"]/android.view.ViewGroup[1]"),
                "Cannot find search result",
                Duration.ofSeconds(15));

        clickIfPresent(By.xpath("//android.widget.ImageView[@content-desc=\"Close\"]"), 3);

        waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Add to list')]"),
                "Cannot find Add to list button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_folder + "']"),
                "Cannot find created list",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
                "Cannot find Navigate up button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("(//android.widget.LinearLayout[@resource-id=\"org.wikipedia:id/navigation_bar_item_content_container\"])[2]"),
                "Cannot find Save button",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '" + name_of_folder + "')]"),
                "Couldn't swipe list button",
                Duration.ofSeconds(5));

        clickIfPresent(By.xpath("//*[contains(@text, 'Got it')]"), 3);

        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '" + search_query1 + "')]"),
                "Cannot find the article to delete");

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '" + search_query1 + "')]"),
                "Cannot delete the saved article",
                Duration.ofSeconds(5));

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '" + search_query2 + "')]"),
                "Cannot find the article that was not deleted",
                Duration.ofSeconds(5));

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' and contains(@text, '" + search_query2 + "')]"),
                "Cannot find the article that was not deleted",
                Duration.ofSeconds(15));

        WebElement title_element = waitForElementPresent(
                By.xpath("//android.widget.TextView[@text='" + search_query2 + "']"),
                "Cannot find article title",
                Duration.ofSeconds(15));

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                search_query2,
                article_title);
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

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
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

    private int getNumberOfElements(By by)
    {
        List elements;
        elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getNumberOfElements(by);
        if (amount_of_elements > 0){
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + ' ' + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, Duration timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}

