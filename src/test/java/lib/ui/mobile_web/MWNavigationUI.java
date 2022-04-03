package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        BACK_FROM_SEARCH_BUTTON = "xpath://android.widget.ImageButton";
        SAVED_ARTICLES_BUTTON = "xpath://a[@class='menu__item--watchlist']";
        OPEN_NAVIGATION = "xpath://label[contains(@title,'Open main menu')]";
    }

    public MWNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
