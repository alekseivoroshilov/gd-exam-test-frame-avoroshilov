package data.pages.android;

import data.pages.AbstractMainPage;
import io.appium.java_client.AppiumDriver;

import static data.helpers.MobileElementFinder.usePhoneBackButton;
import static data.helpers.MobileElementFinder.waitUntilVisibilityOf;

public class MainPageAndroid extends AbstractMainPage {

    @Override
    public AbstractMainPage open() {
        if (!isOpen()) {
            goBack();
        }
        return getInstance();
    }
    public AbstractMainPage goBack() {
        usePhoneBackButton();
        return getInstance();
    }

    public boolean isOpen(){
        return waitUntilVisibilityOf(btnSearch).isDisplayed();
    }
}
