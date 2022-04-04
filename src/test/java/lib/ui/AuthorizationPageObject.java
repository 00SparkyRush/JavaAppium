package lib.ui;

import io.qameta.allure.Step;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{

    private static final String
        LOGIN_BUTTON="xpath://a[contains(text(),'Log in')]",
        LOGIN_INPUT="css:input[name='wpName']",
        PASSWORD_INPUT="css:input[name='wpPassword']",
        SUBMIT_BUTTON="css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Open login form")
    public void clickAuthButton()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.waitForElementPresent(LOGIN_BUTTON,"cant find auth button",5);
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON,"cant find and click auth button", 10);
    }

    @Step("enter login and password:'{login}/{password}'")
    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"cant input login",5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT,password,"cant enter password",5);
    }

    @Step("tap authorize button")
    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON,"cant click submit button",5);
    }

    @Step("return to previous page")
    public void returnToPreviousPage()
    {
        this.waitForElementAndClick("xpath://a[contains(text(),'Return to the previous page.')]","error");
    }
}
