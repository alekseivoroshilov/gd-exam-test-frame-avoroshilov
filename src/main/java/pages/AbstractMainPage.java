package pages;

import locators.MainPageElement;
import org.openqa.selenium.WebElement;
import pages.android.MainPageAndroid;
import pages.ios.MainPageIos;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import static constants.Constants.Configuration.IS_IOS;
import static constants.Constants.Texts.*;
import static helpers.MobileElementFinder.*;

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
        for (WebElement resultListOfLabel : resultListOfLabels) {
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

    @Step("When I Click on Share application button")
    public AbstractMainPage clickShareApp() {
        btnKebabShareApplication.click();
        return this;
    }

    @Step("When I Click on About button")
    public AbstractMainPage openAboutApp() {
        openKebabMenu();
        btnKebabAbout.click();
        return this;
    }

    @Step("Then I verify MainPage Elements presence")
    public AbstractMainPage verifyMainPageElementsPresence() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(iconDictionary.isDisplayed(), "Icon is not present");
        softAssert.assertTrue(btnLike.isDisplayed(), "Like button is not present");
        softAssert.assertTrue(btnSettings.isDisplayed(), "Settings button is not present");
        softAssert.assertTrue(btnMoreOptions.isDisplayed(), "More Options button is not present");
        softAssert.assertTrue(txtActionBarTitle.isDisplayed(), "Action Bar Title is not present");
        softAssert.assertAll();
        return this;
    }

    @Step("Then I verify Share App menu's Elements presence")
    public AbstractMainPage verifyShareAppChooserElements() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(txtShareAppContentPreviewText.isDisplayed(), "App link is not present");
        softAssert.assertTrue(txtChooserHeader.isDisplayed(), "App Header is not present");
        softAssert.assertAll();
        return this;
    }

    @Step("Then I verify About App menu's Elements presence")
    public AbstractMainPage verifyAboutAppElements() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(txtAboutAppTitle.getText().contains(STARTUP_TITLE_POPUP), "About app title is not present");
        softAssert.assertTrue(txtAboutAppText.getText().contains(ABOUT_MSG), "About app text is not present");
        softAssert.assertTrue(txtAboutAppLink.isDisplayed(), "About app link is not present");
        softAssert.assertTrue(txtAboutAppVersion.isDisplayed(), "About app version is not present");
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
        click(btnSettings);
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
