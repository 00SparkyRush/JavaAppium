package lib.ui.android;

import lib.ui.SavedArticlesPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedArticlesPageObject extends SavedArticlesPageObject {


     static {
         SAVED_ARTICLES_LIST_ELEMENT = "id:org.wikipedia:id/item_reading_list_statistical_description";
         ARTICLE_TITLE = "xpath://*[@text='{TITLE}']";
     }
    public AndroidSavedArticlesPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
