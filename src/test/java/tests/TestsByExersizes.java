package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SavedArticlesPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;



public class TestsByExersizes extends CoreTestCase {

    private static final String
            login="WikiTwst",
            password="WikiTest";

    @Test
    public void testSearchFieldText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        String actual_input_text = SearchPageObject.getSearchInputText();

        assertEquals(
                "Search input text is incottect",
                "Search Wikipedia",
                actual_input_text
        );
    }

    @Test
    public void testSearchMultipleArticesAndClear() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        int search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        assertTrue("found only one article or less",search_result>1);
        SearchPageObject.clearSearchInput();
        int cleared_search_result = SearchPageObject.getTotalAmountOfArticlesInSearch();
        assertTrue("searsh result was not cleared",cleared_search_result==0);
    }

    @Test
    public void testConfirmMultipleArticlesByKeyword() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
        SearchPageObject.skipInitialLanguageSelect();}

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();

        int elements_overall_amount = SearchPageObject.getTotalAmountOfArticlesInSearch();
        int elements_with_keyword_amount = SearchPageObject.getTotalAmountOfArticlesInSearchBysubstring("Java");
        assertTrue("not every result contains corresponding keyword", elements_with_keyword_amount == elements_overall_amount);
    }

    @Test
    public void testSaveArticlesToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        SavedArticlesPageObject SavedArticlesPageObject = SavedArticlesPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.skipInitialLanguageSelect();}

        String search_querry1 = "java";
        String search_article1_title = "Java (programming language)";
        String search_querry2 = "appium";
        String search_article2_title = "Appium";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry1);
        SearchPageObject.clickByArticleWithSubstring(search_article1_title);
        ArticlePageObject.waitForTitleElement();
        String article1_title_actual = ArticlePageObject.getArticleTitle();
        ArticlePageObject.tryToSaveAnArticle();
        if (Platform.getInstance().isMW())
        {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            Auth.returnToPreviousPage();

            ArticlePageObject.waitForTitleElement();
            assertEquals("page is not the same after login", article1_title_actual
                    , ArticlePageObject.getArticleTitle());

            //ArticlePageObject.addArticleToSavedList();
        }
        ArticlePageObject.goBackFromArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_querry2);
        SearchPageObject.clickByArticleWithSubstring(search_article2_title);
        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.tryToSaveAnArticle();
        ArticlePageObject.goBackFromArticle();

        NavigationUI.goBackFromSearch();
        NavigationUI.openNavigation();
        NavigationUI.goToSavedArticles();
        SavedArticlesPageObject.openSavedArticlesList();

        SavedArticlesPageObject.deleteArticleByTitleWithSwipe(search_article1_title);
        SavedArticlesPageObject.openArticleByTitle(search_article2_title);
        ArticlePageObject.waitForTitleElement();

        String result_article2_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "titles does not match",
                search_article2_title,
                result_article2_title
        );
        String article_text = ArticlePageObject.getArticleText();
        assertTrue(article_text.contains("tool for running scripts"));
    }

    @Test
    public void testAssertArticleHasTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        ArticlePageObject.assertArticleHasTitle();
    }

    @Test
    public void testConfirmFirstThreeResultsOfSearch()
    {
        String title_result1 = "java";
        String description_result1 = "Island in Southeast Asia";
        String title_result2 = "JavaScript";
        String description_result2 = "High-level programming language";
        String title_result3 = "Java (programming language)";
        String description_result3 = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipInitialLanguageSelect();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForAnySearchResult();
        SearchPageObject.waitForElementByTitleAndDescription(title_result1, description_result1);
        SearchPageObject.waitForElementByTitleAndDescription(title_result2, description_result2);
        SearchPageObject.waitForElementByTitleAndDescription(title_result3, description_result3);
    }
}

