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

    @Step("When I click search")
    public AbstractMainPage clickSearch() {
        waitUntilVisibilityOf(btnSearch, 9).click();
        return this;
    }

    @Step("When I send Keys to Field")
    public AbstractMainPage sendKeysToField(String text) {
        waitUntilVisibilityOf(fldSearch, 9);
        fldSearch.sendKeys(text);
        return this;
    }

    @Step("When I get an amount of search results")
    public Integer getAmountOfSearchResults() {
        return resultList.size();
    }

    @Step("When I add words with parts of speech to Favorites")
    public AbstractMainPage addWordsWithPartsOfSpeechToFavs(String partOfSpeech) {
        for (int i = 0; i < resultListOfLabels.size(); i++) {
            if (resultListOfLabels.get(i).getText().contains(partOfSpeech)) {
                btnAddToFavs(i).click();
            }
        }
        return this;
    }

    @Step("Then I verify words with a single part of speech in Favorites list")
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

    @Step("When I tap on Clear Search")
    public AbstractMainPage clearSearch() {
        click(btnClear);
        return this;
    }

    @Step("Then I verify MainPage Elements presence")
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

    @Step("When I Click on RateApp button")
    public AbstractMainPage clickRateApp() {
        btnLike.click();
        return this;
    }

    @Step("Then I Verify elements inside RateApp Pop-up")
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

    @Step("When I open settings page")
    public AbstractSettingsPage openSettingsPage() {
        click(waitUntilVisibilityOf(btnSettings));
        return AbstractSettingsPage.getInstance();
    }

    @Step("When I open kebab-menu")
    public AbstractMainPage openKebabMenu() {
        waitUntilVisibilityOf(btnMoreOptions).click();
        return this;
    }

    @Step("When I Clear favorites list")
    public AbstractMainPage clearFavorites() {
        if (!isOpen())
            usePhoneBackButton();
        openKebabMenu();
        click(btnKebabFavorites);
        waitUntilVisibilityOf(btnClearFavs).click();
        waitUntilVisibilityOf(btnYesOk).click();
        return this;
    }

    @Step("When I Clear history")
    public AbstractMainPage clearHistory() {
        if (!isOpen())
            usePhoneBackButton();
        openKebabMenu();
        click(btnKebabClearHistory);
        return this;
    }
}
