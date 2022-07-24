package data.helpers;

import data.BaseClass;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;


public class MobileElementFinder extends BaseClass {
    private static final boolean DEFAULT_SCROLL_TO_THE_TOP_REQUIRED = true;

    public boolean isElementDisplayed(MobileElement element){
        try {
            return element.isDisplayed();

        } catch(Exception e){
            System.out.println(e.getCause());

            return false;
        }
    }
}
