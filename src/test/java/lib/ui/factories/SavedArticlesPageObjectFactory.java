package lib.ui.factories;

import lib.Platform;
import lib.ui.SavedArticlesPageObject;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSavedArticlesPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSSavedArticlesPageObject;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobile_web.MWArticlePageObject;
import lib.ui.mobile_web.MWSavedArticlesPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedArticlesPageObjectFactory {
    public static SavedArticlesPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid())
        {
            return new AndroidSavedArticlesPageObject(driver);
        }
        else if (Platform.getInstance().isIOS())
        {
            return new SavedArticlesPageObject(driver);
        }
        else {
            return new MWSavedArticlesPageObject(driver);
        }
    }
}
