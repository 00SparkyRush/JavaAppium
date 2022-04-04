package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @DisplayName("Rotate phone with search result")
    @Description("Open an article, rotate screen, check that article is shown correctly")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotatoin =  ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "title changed after rotatoin",
                title_before_rotation,
                title_after_rotatoin
        );
        this.rotateScreenPortrait();
        String title_after_second_rotatoin =ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "title changed after rotatoin",
                title_before_rotation,
                title_after_second_rotatoin
        );
    }



    @Test
    @DisplayName("Correct app work at background")
    @Description("move app to background and back to confirm it works correctly")
    @Step("Starting test testCheckSearchArticleInBackground")
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        this.backgroundApp(Duration.ofSeconds(3));
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }
}
