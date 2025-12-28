package lib.ui;

import io.qameta.allure.Step;
import lib.ui.Factories.NavigationUIFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BUTTON = "css~a[href*='Special:UserLogin']",
            USERNAME_INPUT = "css~#wpName1",
            PASSWORD_INPUT = "css~#wpPassword1",
            SUBMIT_BUTTON = "css~#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Opening Login page")
    public void goToAuthPage() {
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openSidebar();
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    @Step("Entering login data")
    public void enterLogInData(String login, String password) {
        this.waitForElementAndSendKeys(
                USERNAME_INPUT,
                login,
                "Cannot find login button",
                5);
        this.waitForElementAndSendKeys(
                PASSWORD_INPUT,
                password,
                "Cannot find password button",
                5);
    }

    @Step("Submitting login data")
    public void SubmitForm() {
        this.waitForElementAndClick(
                SUBMIT_BUTTON,
                "Cannot find and click submit button",
                5);
    }

}
