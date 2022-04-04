package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedArticlesPageObject extends MainPageObject {

    protected static String
    SAVED_ARTICLES_LIST_ELEMENT,
    ARTICLE_TITLE,
    REMOVE_FROM_SAVED_BUTTON;

    public SavedArticlesPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
    //TEMPLATE METHODS
    private static String getArticleTitle(String title_of_article)
    {
        return ARTICLE_TITLE.replace("{TITLE}", title_of_article);
    }

    private static String getRemoveButtonbyTitle(String title_of_article)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", title_of_article);
    }

    //TEMPLATE METHODS
    @Step("Open saved articles list(does nothing for mobile web)")
    public void openSavedArticlesList()
    {
        if (Platform.getInstance().isAndroid()) {
        this.waitForElementAndClick(
                SAVED_ARTICLES_LIST_ELEMENT,
                "Coudn`t open saved articles list"
        );} else {
            System.out.println("Method getInstance() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }

    @Step("check that article '{title}' appeared")
    public void waitForArticletoApearByTitle(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForElementPresent(
                title_by_subsstring,
                "Can`t find an article in saved list",
                5
        );
    }

    @Step("check that article '{title}' disappeared")
    public void waitForArticletoDissapearByTitle(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForElementNotPresent(
                title_by_subsstring,
                "Can`t find an article in saved list",
                15
        );
    }

    @Step("Swipe to delete an article '{title}'")
    public void deleteArticleByTitleWithSwipe(String title)
    {
        String title_by_subsstring = getArticleTitle(title);
        this.waitForArticletoApearByTitle(title);
        if (Platform.getInstance().isAndroid()) {
        this.swipeElementLeft(
                title_by_subsstring,
                "can`t delete an article"
        );} else {
            String remove_locator = getRemoveButtonbyTitle(title);
            this.waitForElementAndClick(remove_locator, "cant click button to remove article",10);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (Platform.getInstance().isMW())
        {
            driver.navigate().refresh();
        }
        this.waitForArticletoDissapearByTitle(title);
    }

    @Step("open article '{title}'")
    public void openArticleByTitle(String title)
    {
        String title_by_subsstring = getArticleTitle(title);

        this.waitForArticletoApearByTitle(title);
        this.waitForElementAndClick(
                title_by_subsstring,
                "can`t open saved article",
                15
        );
    }
}
