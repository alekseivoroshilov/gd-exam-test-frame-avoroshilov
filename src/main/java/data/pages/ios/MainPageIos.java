package data.pages.ios;

import data.pages.AbstractMainPage;
import io.appium.java_client.AppiumDriver;

public class MainPageIos extends AbstractMainPage {

    @Override
    public AbstractMainPage open() {
        System.out.println("HEY YO, WE TEST AN ANDROID APP, NOT IOS");
        return null;
    }

    @Override
    public boolean isOpen(){
        return btnSearch.isDisplayed();
    }
}
