package lib.ui.ios;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "//android.widget.TextView[1]";
        FOOTER_ELEMENT = "//*[@text = 'View article in browser']";
        ADD_TO_SAVED_LIST_ELEMENT = "org.wikipedia:id/article_menu_bookmark";
        BACK_BUTTON_ELEMENT = "//android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public IOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
