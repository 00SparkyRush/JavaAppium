package lib.ui;

import lib.Platform;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    protected static String
    BACK_FROM_SEARCH_BUTTON,
    SAVED_ARTICLES_BUTTON,
    OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMW())
        {
            this.waitForElementAndClick(OPEN_NAVIGATION, "cant open navigation menu",5);
        }
        else {
            System.out.println("Method openNavigation() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void goBackFromSearch()
    {
        if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    BACK_FROM_SEARCH_BUTTON,
                    "Coudn`t return to main screen"
            );
        } else {
            System.out.println("Method goBackFromSearch() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    public void goToSavedArticles()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(SAVED_ARTICLES_BUTTON, "Coudn`t open saved articles",100);
        } else {
        this.waitForElementAndClick(
                SAVED_ARTICLES_BUTTON,
                "Coudn`t open saved articles"
        );}
    }
}
