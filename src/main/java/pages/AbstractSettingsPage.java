package pages;

import locators.SettingsPageElement;
import org.openqa.selenium.WebElement;
import pages.android.SettingsPageAndroid;
import pages.ios.SettingsPageIos;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import static constants.Constants.Configuration.IS_IOS;
import static constants.Constants.Pages.SETTINGS;
import static constants.Constants.SettingsTexts.*;
import static helpers.MobileElementFinder.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    @Step("Then I verify Settings Page elements")
    public AbstractSettingsPage verifySettingsPageElements() {
        waitUntilVisibilityOf(txtActionBarTitle);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(txtFullSearch.isDisplayed(), "Element " + FULL_SEARCH + " not displayed");
        softAssert.assertTrue(txtSearchType.isDisplayed(), "Element " + SEARCH_TYPE + " not displayed");
        softAssert.assertAll();
        return this;
    }

    @Step("When I change checkbox state")
    public AbstractSettingsPage setCheckBoxState(String option, boolean state) {
        waitUntilVisibilityOf(txtActionBarTitle);
        int optionY = 0;
        for (WebElement found_option : txtOptions) {
            if (found_option.getText().contains(option)) {
                optionY = found_option.getRect().y;
                break;
            }
        }

        if (optionY == 0)
            return this;

        WebElement resultCheckbox = null;
        for (WebElement found_checkbox : btnCheckbox) {
            int checkboxY = found_checkbox.getRect().y;
            if (checkboxY >= optionY - 50 && checkboxY <= optionY + 50) {
                resultCheckbox = found_checkbox;
                break;
            }
        }

        if (resultCheckbox == null)
            return this;

        String checkBoxAttribute = resultCheckbox.getAttribute("checked");
        if (Boolean.parseBoolean(checkBoxAttribute) != state) {
            resultCheckbox.click();
        }

        return this;
    }

    @Step("Then I verify Disabled Speech section on Settings Page")
    public AbstractSettingsPage verifySpeechSectionIsBlocked() {
        waitUntilVisibilityOf(txtActionBarTitle);
        WebElement speechAccentTxt = getOptionElementByText(SPEECH_ACCENT);
        assertFalse(speechAccentTxt.isEnabled(), "Option " + SPEECH_ACCENT + " is enabled");
        return this;
    }

    @Step("Then I verify Enabled Speech section on Settings Page")
    public AbstractSettingsPage verifySpeechSectionIsNotBlocked() {
        waitUntilVisibilityOf(txtActionBarTitle);
        WebElement speechAccentTxt = getOptionElementByText(SPEECH_ACCENT);
        assertTrue(speechAccentTxt.isEnabled(), "Option " + SPEECH_ACCENT + " is disabled");
        return this;
    }
}
