package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://android.widget.TextView[1]";
        FOOTER_ELEMENT = "xpath://*[@text = 'View article in browser']";
        ADD_TO_SAVED_LIST_ELEMENT = "id:org.wikipedia:id/article_menu_bookmark";
        BACK_BUTTON_ELEMENT = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
