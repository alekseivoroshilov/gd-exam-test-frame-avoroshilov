package data.pages;

import data.elements.SettingsPageElement;
import data.pages.android.SettingsPageAndroid;
import data.pages.ios.SettingsPageIos;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import static data.constants.Constants.Configuration.IS_IOS;
import static data.constants.Constants.Pages.SETTINGS;
import static data.constants.Constants.SettingsTexts.FULL_SEARCH;
import static data.constants.Constants.SettingsTexts.SEARCH_TYPE;
import static data.helpers.MobileElementFinder.*;

public class AbstractSettingsPage extends SettingsPageElement {

    public static AbstractSettingsPage getInstance() {
        return IS_IOS ? new SettingsPageIos() : new SettingsPageAndroid();
    }

    @Override
    public boolean isOpen() {
        return getElementText(waitUntilVisibilityOf(txtActionBarTitle)).equals(SETTINGS);
    }

    public AbstractMainPage goBackToMainPage() {
        usePhoneBackButton();
        return AbstractMainPage.getInstance();
    }

    @Step("verifySettingsPageElements")
    public AbstractSettingsPage verifySettingsPageElements() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(txtFullSearch.isDisplayed(), "Element " + FULL_SEARCH + " not displayed");
        softAssert.assertTrue(txtSearchType.isDisplayed(), "Element " + SEARCH_TYPE + " not displayed");
        softAssert.assertAll();
        return this;
    }
}
