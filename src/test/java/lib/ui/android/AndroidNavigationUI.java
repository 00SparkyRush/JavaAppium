package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
                BACK_FROM_SEARCH_BUTTON = "xpath://android.widget.ImageButton";
                SAVED_ARTICLES_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
        OPEN_NAVIGATION="";
    }

    public AndroidNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
