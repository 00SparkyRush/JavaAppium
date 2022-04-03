package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import lib.Platform;
import org.junit.Assert;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import static io.appium.java_client.touch.offset.PointOption.point;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    public WebElement skipInitialLanguageSelect()
    {
        WebElement element = waitForElementPresent(
                "xpath://*[contains(@text, 'SKIP')]",
                "Can`t perform initial skip",
                2);
         element.click();
         return element;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message+"\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresent(String locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 5);
    }


    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInseconds)
    {
        WebElement element = waitForElementPresent( locator,  error_message,  timeoutInseconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClick(String locator, String error_message)
    {
        return waitForElementAndClick( locator,  error_message, 5);
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts)
    {
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts)
        {
            try {
                this.waitForElementAndClick(locator,error_message,1);
                need_more_attempts = false;
            }
            catch (Exception e) {
                if(current_attempts > amount_of_attempts)
                {
                    this.waitForElementAndClick(locator,error_message,1);
                }
            }
            ++current_attempts;
        }
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInseconds)
    {
        WebElement element = waitForElementPresent( locator,  error_message,  timeoutInseconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message)
    {
        return waitForElementAndSendKeys(locator, value, error_message, 5);
    }


    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds)
    {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, error_message,timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(locator, "An element is not present", 5);
        String actual_text = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                expected_text,
                actual_text
        );
    }

    public void swipeUp(int timeOfSwipe)
    {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y =(int) (size.height * 0.8);
            int end_y =(int) (size.height * 0.2);

            action
                    .press(point(x,start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(point(x,end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothins for platform "+
                    Platform.getInstance().getPlatformVar());
        }

    }

    public void quickSwipeUp()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator,String error_message, int max_swipes)
    {
        By by = getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0)
        {
            if (already_swiped > max_swipes)
            {
                waitForElementPresent(locator,"Can`t find element by swiping up \n" + error_message, 0);
                return;
            }
            quickSwipeUp();
            already_swiped++;
        }
    }

    public void scrollWebPageUp()
    {
        if (Platform.getInstance().isMW())
        {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        }
        else {
            System.out.println("Method scrollWebPageUp() does nothins for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }
/*
    public void scrollWebPageTillElementNotWisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.waitForElementPresent(l))
    }
    */

    public void swipeElementLeft( String locator, String error_message)
    {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y  + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                    .moveTo(point(left_x, middle_y))
                    .release()
                    .perform();
        }
        else {
            System.out.println("Method swipeElementLeft() does nothins for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    public int getAmountOfElements(String locator)
    {
        By by = getLocatorByString(locator);
        List elements =driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator)
    {
        return getAmountOfElements(locator)>0;
    }

    public void assertElementPresent(String locator, String error_message)
    {
        By by = getLocatorByString(locator);
        int amount_of_elements = getAmountOfElements(locator);

        if (amount_of_elements == 0)
        {
            String default_message = "An element "+by.toString()+" supposed to be present";
            throw new AssertionError(default_message + " "+ error_message);
        }
    }

    public void assertElementNotPresent(String locator, String error_message)
    {
        By by = getLocatorByString(locator);
        int amount_of_elements = getAmountOfElements(locator);

        if (amount_of_elements > 0)
        {
            String default_message = "An element "+by.toString()+" supposed to be not present";
            throw new AssertionError(default_message + " "+ error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeputInSeconds)
    {
        WebElement element = waitForElementPresent(locator,error_message,timeputInSeconds);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if (by_type.equals("id"))
        {
            return By.id(locator);
        }
        else if (by_type.equals("css"))
        {
            return By.cssSelector(locator);
        }
        else
        {
            throw new IllegalArgumentException("Can`t get locator type. Locator: "+locator_with_type);
        }
    }
}
