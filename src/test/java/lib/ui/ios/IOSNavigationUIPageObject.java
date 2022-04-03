package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUIPageObject extends NavigationUI {
    static {
        BACK_FROM_SEARCH_BUTTON = "//android.widget.ImageButton";
        SAVED_ARTICLES_BUTTON = "//android.widget.FrameLayout[@content-desc='Saved']";
    }

    public IOSNavigationUIPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
