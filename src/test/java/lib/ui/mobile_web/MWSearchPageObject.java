package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
        static {
            SEARCH_INIT_ELEMENT = "css:button#searchIcon";
            SEARCH_INPUT = "css:form>input[type='search']";
            SEARCH_CANCEL_BUTTON = "css:button.cancel";
            SEARCH_RESULT = "xpath://div[@class='results-list-container view-border-box']//li";
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(text(),'{SUBSTRING}')]";
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION = "xpath://li[contains(@title,'{TITLE}')]//div[contains(text(),'{DESCRIPTION}')]";
            SERARH_RESULT_CONTAINING_SUBSTRING_TPL = "xpath://li/a/div[contains(text(),'{SUBSTRING}')]";
            SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
            EMPTY_RESULTS_LABEL = "css:p.without-results";
        }

        public MWSearchPageObject(RemoteWebDriver driver)
        {
            super(driver);
        }
}
