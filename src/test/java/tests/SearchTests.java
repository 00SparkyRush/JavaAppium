package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    @DisplayName("search by querry")
    @Description("check that querry finds correct result")
    @Step("Starting test testSearch")
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @DisplayName("cancel search")
    @Description("Check that search can be canceled")
    @Step("Starting test testCancelSearch")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitFroCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitFroCancelButtonToDisappear();
    }

    @Test
    @DisplayName("Check that search works")
    @Description("Find anything, confirm that there is more than one result")
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch() {
        String search_querry = "Linkin park discography";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();};

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry);

        int amount_of_search_elements = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "Too few results were found",
                amount_of_search_elements > 0
        );
    }

    @Test
    @DisplayName("check correct 'no results behaviour'")
    @Description("Check that if there`s no results an app acts correctly")
    @Step("Starting test testAmountofEmtySearch")
    public void testAmountofEmtySearch() {
        String search_querry = "fsfghgfhrg";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResult();
    }
}
