package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SavedArticlesPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SaveArticleTests extends CoreTestCase {

    private static final String
        login="WikiTwst",
        password="WikiTest";

    @Test
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
            assertEquals("page is not the same after login", article_title, ArticlePageObject.getArticleTitle());

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
