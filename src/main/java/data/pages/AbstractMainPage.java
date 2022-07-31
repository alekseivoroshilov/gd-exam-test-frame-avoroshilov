package data.pages;

import data.elements.MainPageElement;
import data.pages.android.MainPageAndroid;
import data.pages.ios.MainPageIos;

import static data.Constants.Configuration.IS_IOS;

public abstract class AbstractMainPage extends MainPageElement {
    public static AbstractMainPage getInstance() {
        return IS_IOS ? new MainPageIos() : new MainPageAndroid();
    }
    public abstract AbstractMainPage open();
}
