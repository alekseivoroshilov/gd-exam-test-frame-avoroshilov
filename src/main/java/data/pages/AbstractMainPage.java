package data.pages;

import data.elements.MainPageElement;
import data.pages.android.MainPageAndroid;
import data.pages.ios.MainPageIos;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import static data.constants.Constants.Configuration.IS_IOS;
import static data.constants.Constants.Texts.AD_FREE_VERSION;
import static data.constants.Constants.Texts.PLEASE_SUPPORT_DEVELOPMENT;
import static data.helpers.MobileElementFinder.*;

public abstract class AbstractMainPage extends MainPageElement {

    public static AbstractMainPage getInstance() {
        return IS_IOS ? new MainPageIos() : new MainPageAndroid();
    }

    @Step("isOpen")
    @Override
    public abstract boolean isOpen();

    public abstract AbstractMainPage open();

    @Step("clickSearch")
    public AbstractMainPage clickSearch() {
        waitUntilVisibilityOf(btnSearch, 9).click();
        return this;
    }

    @Step("sendKeysToField")
    public AbstractMainPage sendKeysToField(String text) {
        waitUntilVisibilityOf(fldSearch, 9);
        fldSearch.sendKeys(text);
        return this;
    }

    @Step("getAmountOfSearchResults")
    public Integer getAmountOfSearchResults() {
        return resultList.size();
    }

    @Step("addWordsWithPartsOfSpeechToFavs")
    public AbstractMainPage addWordsWithPartsOfSpeechToFavs(String partOfSpeech) {
        for (int i = 0; i < resultListOfLabels.size(); i++) {
            if (resultListOfLabels.get(i).getText().contains(partOfSpeech)) {
                btnAddToFavs(i).click();
            }
        }
        return this;
    }

    @Step("verifyWordsWithSinglePartOfSpeechInFavs")
    public AbstractMainPage verifyWordsWithSinglePartOfSpeechInFavs(String partOfSpeech) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(resultListOfLabels.size() != 0,
                "There are no results with part of speech: " + partOfSpeech);
        for (org.openqa.selenium.WebElement resultListOfLabel : resultListOfLabels) {
            softAssert.assertTrue(resultListOfLabel.getText().contains(partOfSpeech),
                    resultListOfLabel.getText() + " does not contain - " + partOfSpeech);
        }
        softAssert.assertAll();
        return this;
    }

    @Step("clearSearch")
    public AbstractMainPage clearSearch() {
        click(btnClear);
        return this;
    }

    @Step("verifyMainPageElementsPresence")
    public AbstractMainPage verifyMainPageElementsPresence() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(iconDictionary.isDisplayed());
        softAssert.assertTrue(btnLike.isDisplayed());
        softAssert.assertTrue(btnSettings.isDisplayed());
        softAssert.assertTrue(btnMoreOptions.isDisplayed());
        softAssert.assertTrue(txtActionBarTitle.isDisplayed());
        softAssert.assertAll();
        return this;
    }

    @Step("clickRateApp")
    public AbstractMainPage clickRateApp() {
        btnLike.click();
        return this;
    }

    @Step("verifyRateAppPopUpElements")
    public AbstractMainPage verifyRateAppPopUpElements() {
        if (isNotDisplayed(txtAdFree) && isNotDisplayed(txtPleaseSupport)) {
            usePhoneBackButton();
            clickRateApp();
        }
        SoftAssert softAssert = new SoftAssert();
        if (isNotDisplayed(txtAdFree) || isNotDisplayed(txtPleaseSupport)) {
            softAssert.assertTrue(txtAdFree.isDisplayed(), "Ad-Free label not displayed");
            softAssert.assertTrue(txtPleaseSupport.isDisplayed(), "Please support the app text not displayed");
        } else {
            softAssert.assertTrue(getElementText(txtAdFree).equals(AD_FREE_VERSION), "Ad-Free label not correct");
            softAssert.assertTrue(getElementText(txtPleaseSupport).equals(PLEASE_SUPPORT_DEVELOPMENT), "Please support the app text not correct");
            usePhoneBackButton();
        }
        softAssert.assertAll();
        return this;
    }

    @Step("openSettingsPage")
    public AbstractSettingsPage openSettingsPage() {
        click(waitUntilVisibilityOf(btnSettings));
        return AbstractSettingsPage.getInstance();
    }

    @Step("openKebabMenu")
    public AbstractMainPage openKebabMenu() {
        waitUntilVisibilityOf(btnMoreOptions).click();
        return this;
    }

    @Step("clearFavorites")
    public AbstractMainPage clearFavorites() {
        if (!isOpen())
            usePhoneBackButton();
        openKebabMenu();
        click(btnKebabFavorites);
        waitUntilVisibilityOf(btnClearFavs).click();
        waitUntilVisibilityOf(btnYesOk).click();
        return this;
    }

    @Step("clearHistory")
    public AbstractMainPage clearHistory() {
        if (!isOpen())
            usePhoneBackButton();
        openKebabMenu();
        click(btnKebabClearHistory);
        return this;
    }
}
