package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "xpath://h1";
        FOOTER_ELEMENT = "css:footer";
        ADD_TO_SAVED_LIST_ELEMENT = "xpath://ul[@id='page-actions']//a[@id='ca-watch']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://ul[@id='page-actions']//a[@title='Add this page to your watchlist']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://ul[@id='page-actions']//a[@title='Remove this page from your watchlist']";
        BACK_BUTTON_ELEMENT = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        ARTICLE_BODY_TEXT = "xpath://section[@class='mf-section-0']/p";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
