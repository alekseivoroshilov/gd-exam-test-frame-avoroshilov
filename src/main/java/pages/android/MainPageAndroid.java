package pages.android;

import pages.AbstractMainPage;

import static helpers.MobileElementFinder.usePhoneBackButton;
import static helpers.MobileElementFinder.waitUntilVisibilityOf;

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
