package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SavedArticlesPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for saving articles")
public class SaveArticleTests extends CoreTestCase {

    private static final String
        login="WikiTwst",
        password="WikiTest";

    @Test
    @Features(value = {@Feature(value="Search"), @Feature(value="Article"), @Feature(value="Watchlist")})
    @DisplayName("add and remove article to favorites")
    @Description("Find an app put it to favorites, confirm it was added and then remove it")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        SavedArticlesPageObject SavedArticlesPageObject = SavedArticlesPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject.waitForTitleElement();

        //ArticlePageObject.takeScreenshot("article_page");

        String article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.tryToSaveAnArticle();

        if (Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            Auth.returnToPreviousPage();

            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals("page is not the same after login", article_title, ArticlePageObject.getArticleTitle());

            //ArticlePageObject.addArticleToSavedList();
        }

        NavigationUI.openNavigation();

        ArticlePageObject.goBackFromArticle();
        NavigationUI.goBackFromSearch();
        NavigationUI.goToSavedArticles();
        SavedArticlesPageObject.openSavedArticlesList();
        SavedArticlesPageObject.deleteArticleByTitleWithSwipe(article_title);
    }
}
