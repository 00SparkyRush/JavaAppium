package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            ADD_TO_SAVED_LIST_ELEMENT,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            BACK_BUTTON_ELEMENT,
            ARTICLE_BODY_TEXT;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(
                TITLE,
                "can`t open an article",
                10
        );
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }
        else {
            return title_element.getText();
        }
    }

    public String getArticleText()
    {
        if (Platform.getInstance().isMW())
        {
            WebElement element = waitForElementPresent(ARTICLE_BODY_TEXT,"elemeni is not present");
            return element.getText();
        } else {
            return "method does nothing for "+Platform.getInstance().getPlatformVar();
        }
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "can`t find article end",
                20);
    }

    public void tryToSaveAnArticle()
    {
        this.waitForElementPresent(ADD_TO_SAVED_LIST_ELEMENT,"cant locate save button",10);
        this.waitForElementAndClick(
                ADD_TO_SAVED_LIST_ELEMENT,
                "Coudn`t save an article"
        );
    }
    public void addArticleToSavedList()
    {
        if (Platform.getInstance().isMW())
        {
            this.removeArticlesFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                ADD_TO_SAVED_LIST_ELEMENT,
                "Coudn`t save an article"
        );
    }

    public void removeArticlesFromSavedIfItAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON))
        {
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "cant click delete article button", 1);
            this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "cant find add button after remowing an article",1);
        }
    }

    public void goBackFromArticle()
    {
        if (Platform.getInstance().isAndroid()){
        this.waitForElementAndClick(
                BACK_BUTTON_ELEMENT,
                "Coudn`t return back to search"
        );} else {
            System.out.println("Method goBackFromArticle() does nothing for platform "+
                    Platform.getInstance().getPlatformVar());
        }
    }
    public void assertArticleHasTitle()
    {
        this.assertElementPresent(
                TITLE,
        "An article has no title");
    }
}
