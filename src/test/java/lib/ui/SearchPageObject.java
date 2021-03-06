package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION,
            SERARH_RESULT_CONTAINING_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            EMPTY_RESULTS_LABEL;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    //TEMPLATE METHODS
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementContainintSubstring(String substring)
    {
        return SERARH_RESULT_CONTAINING_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementContaininsTitleAndDescription(String title, String description)
    {
        String title_apply =  SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION.replace("{TITLE}", title);
        String description_apply = title_apply.replace("{DESCRIPTION}", description);
        return description_apply;
    }
    //TEMPLATE METHODS

    public String getSearchInputText()
    {
        return this.waitForElementPresent(
                SEARCH_INPUT,
                "can`t find and search init element",
                5).
                getAttribute("text");
    }

    @Step("Initializing search field")
    public void initSearchInput()
    {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "can`t find and search init element",
                5
        );
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "can`t find and click search init element"
        );
    }
    @Step("Type '{search_line}' in search")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Can`t find and type into search input"
        );
    }
    @Step("Wait for search results list to appaer")
    public void waitForAnySearchResult()
    {
        this.waitForElementPresent(
                SEARCH_RESULT,
                "search result is empty",
                15
        );
    }
    @Step("wait for search result '{substring}'")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Can`t find search result with subsctring "+substring,
                5
        );
    }
    @Step("wait for search result containing text '{substring}'")
    public void waitForSearchResultContainsText(String substring)
    {
        String search_result_xpath = getResultSearchElementContainintSubstring(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Can`t find search result with subsctring "+substring,
                5
        );
    }
    @Step("Confirm that article  title '{title}' and description '{description}' was found")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultSearchElementContaininsTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Can`t find search result with title "+title+" and description "+description,
                15
        );
    }
    @Step("Open an article '{substring}'")
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElementContainintSubstring(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Can`t find and click search result with subsctring " + substring,
                10
        );
    }
    @Step("Wait for button to cancel search result appear")
    public void waitFroCancelButtonToAppear()
    {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Can`t find search cancel button"
        );
    }
    @Step("Wait for button to cancel search result disappear")
    public void waitFroCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5
        );
    }

    @Step("cancel search")
    public void clickCancelButton()
    {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "can`t click by cancel button",
                5
        );
    }

    @Step("Get amount of articles found in search")
    public int getTotalAmountOfArticlesInSearch()
    {
        return driver.findElements(By.xpath(SEARCH_RESULT)).size();
    }

    @Step("Get amount of articles with '{substring}' text in header found in search")
    public int getTotalAmountOfArticlesInSearchBysubstring(String substring)
    {
        String search_results_by_substring = getResultSearchElementContainintSubstring(substring);
        return driver.findElements(By.xpath(search_results_by_substring)).size();
    }
    public void clearSearchInput()
    {
        waitForElementAndClear(
                SEARCH_INPUT,
                "can`t clear search input field",
                5
        );
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Can`t find anything by request ",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT );
    }

    @Step("Confrim that nothing was found")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                EMPTY_RESULTS_LABEL,
                "Can`t find 'No results' label",
                15
        );
    }
    public void assertThereIsNoResult()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "there are some search results");
    }
}
