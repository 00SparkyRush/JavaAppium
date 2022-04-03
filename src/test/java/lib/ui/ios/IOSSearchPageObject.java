package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT = "org.wikipedia:id/search_src_text";
        SEARCH_CANCEL_BUTTON = "//*[@class = 'android.widget.ImageButton']";
        SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@class='android.view.ViewGroup']//*[@text= '{SUBSTRING}']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "//*[@class='android.view.ViewGroup']//*[contains(@text,'{TITLE}')]//following::*[contains(@text,'{DESCRIPTION}')]";
        SERARH_RESULT_CONTAINING_SUBSTRING_TPL = "//*[@class='android.view.ViewGroup']//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        EMPTY_RESULTS_LABEL = "//*[@text='No results']";
    }

    public IOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
