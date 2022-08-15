package data.elements;

import data.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static data.Constants.Locators.ANDROID_ID;
import static data.Constants.Locators.ID;

public abstract class MainPageElement extends BasePage {

    public MainPageElement() {
        super();
    }

    @AndroidFindBy(id = ID + "edit_text_search")
    protected WebElement fldSearch;

    @AndroidFindBy(id = ID + "btn_clear")
    protected WebElement btnClear;

    @AndroidFindBy(id = ID + "btn_search")
    protected WebElement btnSearch;

    @AndroidFindBy(id = ANDROID_ID + "empty")
    protected WebElement txtEmpty;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout")
    protected WebElement iconDictionary;

    @AndroidFindBy(id = ANDROID_ID + "action_bar_title")
    protected WebElement txtActionBarTitle;

    @AndroidFindBy(accessibility = "More options")
    protected WebElement btnMoreOptions;

    @AndroidFindBy(accessibility = "Ad-free version")
    protected WebElement btnLike;

    @AndroidFindBy(accessibility = "Settings")
    protected WebElement btnSettings;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[1]")
    protected WebElement txtAdFree;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView[2]")
    protected WebElement txtPleaseSupport;

    @AndroidFindBy(id = ID + "button_no_thanks")
    protected WebElement btnNoThanks;

    @AndroidFindBy(id = ID + "button_pro")
    protected WebElement btnPro;

    @AndroidFindBy(id = ID + "button_retore")
    protected WebElement btnRestorePurchase;

    @AndroidFindBy(id = ANDROID_ID + "content")
    protected WebElement btnContent;

    @AndroidFindBy(id = ANDROID_ID + "title")
    protected WebElement txtTitle;

    @AndroidFindBy(id = ANDROID_ID + "alertTitle")
    protected WebElement txtAlertTitle;

    @AndroidFindBy(id = ANDROID_ID + "text1")
    protected WebElement btnPopUpCell;

    @AndroidFindBy(id = ANDROID_ID + "chooser_header")
    protected WebElement txtChooserHeader;

    @AndroidFindBy(id = ANDROID_ID + "message")
    protected WebElement txtPopUpMessage;

    @AndroidFindBy(id = ANDROID_ID + "button1")
    protected WebElement btnYesOk;

    @AndroidFindBy(id = ANDROID_ID + "button2")
    protected WebElement btnNo;

    @AndroidFindBy(className = "android.widget.CheckBox")
    protected WebElement btnMoveToFavs;
}
