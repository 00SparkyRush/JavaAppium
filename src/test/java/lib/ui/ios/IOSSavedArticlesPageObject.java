package lib.ui.ios;

import lib.ui.SavedArticlesPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSavedArticlesPageObject extends SavedArticlesPageObject {
    static {
        SAVED_ARTICLES_LIST_ELEMENT = "org.wikipedia:id/item_reading_list_statistical_description";
        ARTICLE_TITLE = "//*[@text='{TITLE}']";
    }
    public IOSSavedArticlesPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
