package data.elements;

import data.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

import static data.Constants.Locators.ANDROID_ID;
import static data.Constants.Locators.ID;

public abstract class SettingsPageElement extends BasePage {

    @AndroidFindBy(id = ANDROID_ID + "home")
    protected WebElement iconHome;

    @AndroidFindBy(id = ANDROID_ID + "action_bar_title")
    protected WebElement txtActionBarTitle;

    @AndroidFindBy(id = ANDROID_ID + "title")
    protected WebElement txtTitle;

    @AndroidFindBy(id = ANDROID_ID + "summary")
    protected WebElement txtSummary;

    @AndroidFindBy(id = ANDROID_ID + "checkbox")
    protected WebElement txtCheckbox;

    @AndroidFindBy(className = "android.widget.RelativeLayout")
    protected WebElement btnSettingsCell;

    @AndroidFindBy(id = ANDROID_ID + "text1")
    protected WebElement btnPopUpCell;

    @AndroidFindBy(id = ANDROID_ID + "alertTitle")
    protected WebElement txtAlertTitle;

    @AndroidFindBy(accessibility = "Settings")
    protected WebElement btnSettings;
}
