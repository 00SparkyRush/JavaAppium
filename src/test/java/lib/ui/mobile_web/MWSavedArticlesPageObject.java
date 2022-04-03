package lib.ui.mobile_web;

import lib.ui.SavedArticlesPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedArticlesPageObject extends SavedArticlesPageObject {
    static {
        SAVED_ARTICLES_LIST_ELEMENT = "id:org.wikipedia:id/item_reading_list_statistical_description";
        ARTICLE_TITLE = "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watched')]";

    }
    public MWSavedArticlesPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
