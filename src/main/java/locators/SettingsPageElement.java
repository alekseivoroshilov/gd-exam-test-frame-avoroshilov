package locators;

import core.page.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

import static constants.Constants.Locators.*;
import static constants.Constants.SettingsTexts.FULL_SEARCH;
import static constants.Constants.SettingsTexts.SEARCH_TYPE;
import static helpers.MobileElementFinder.findElement;
import static helpers.MobileElementFinder.findElements;
import static java.lang.String.format;

public abstract class SettingsPageElement extends BasePage {

    @AndroidFindBy(id = ANDROID_ID + "home")
    protected WebElement iconHome;

    @AndroidFindBy(id = ANDROID_ID + "action_bar_title")
    protected WebElement txtActionBarTitle;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, '" + FULL_SEARCH + "')]")
    protected WebElement txtFullSearch;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, '" + SEARCH_TYPE + "')]")
    protected WebElement txtSearchType;

    @AndroidFindBy(id = ANDROID_ID + "title")
    protected List<WebElement> txtOptions;

    @AndroidFindBy(id = ANDROID_ID + "summary")
    protected List<WebElement> txtSummary;

    @AndroidFindBy(id = ANDROID_ID + "checkbox")
    protected List<WebElement> btnCheckbox;

    @AndroidFindBy(className = "android.widget.RelativeLayout")
    protected WebElement btnSettingsCell;

    @AndroidFindBy(id = ANDROID_ID + "text1")
    protected WebElement btnPopUpCell;

    @AndroidFindBy(id = ANDROID_ID + "alertTitle")
    protected WebElement txtAlertTitle;

    @AndroidFindBy(accessibility = "Settings")
    protected WebElement btnSettings;

    protected WebElement getOptionElementByText(String option) {
        return findElement(MobileBy.xpath(
                format(("/" + RELATIVE_LAYOUT + TEXT_VIEW + "[1]" + "[contains(@text, '" + "%s" + "')]"), option)));
    }
}
