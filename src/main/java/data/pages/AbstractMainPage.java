package data.pages;

import data.elements.MainPageElement;
import data.pages.android.MainPageAndroid;
import data.pages.ios.MainPageIos;
import io.qameta.allure.Step;

import static data.Constants.Configuration.IS_IOS;
import static data.Constants.Words.WORD1;

public abstract class AbstractMainPage extends MainPageElement {

    public static AbstractMainPage getInstance() {
        return IS_IOS ? new MainPageIos() : new MainPageAndroid();
    }

    @Step("isOpen")
    @Override
    public abstract boolean isOpen();

    public abstract AbstractMainPage open();

    public void clickSearch() {
        fldSearch.sendKeys(WORD1);
        btnSearch.click();
    };
}
