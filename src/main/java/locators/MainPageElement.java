package locators;

import core.page.BasePage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

import static constants.Constants.Locators.*;
import static helpers.MobileElementFinder.findElement;
import static java.lang.String.format;

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

    @AndroidFindBy(id = ANDROID_ID + "list")
    protected List<WebElement> resultList;

    @AndroidFindBy(xpath = "/" + LIST_VIEW + LINEAR_LAYOUT + LINEAR_LAYOUT + TEXT_VIEW + "[1]")
    protected List<WebElement> resultListOfLabels;

    @AndroidFindBy(xpath = "/" + LIST_VIEW + LINEAR_LAYOUT + LINEAR_LAYOUT + TEXT_VIEW + "[2]")
    protected List<WebElement> resultListOfDescriptions;

    @AndroidFindBy(id = ANDROID_ID + "empty")
    protected WebElement txtEmpty;

    @AndroidFindBy(id = ANDROID_ID + "home")
    protected WebElement iconDictionary;

    @AndroidFindBy(id = ANDROID_ID + "action_bar_title")
    protected WebElement txtActionBarTitle;

    @AndroidFindBy(accessibility = "More options")
    protected WebElement btnMoreOptions;

    @AndroidFindBy(accessibility = "Ad-free version")
    protected WebElement btnLike;

    @AndroidFindBy(accessibility = "Settings")
    protected WebElement btnSettings;

    @AndroidFindBy(xpath = "/" + LINEAR_LAYOUT + TEXT_VIEW + "[1]")
    protected WebElement txtAdFree;

    @AndroidFindBy(xpath = "/" + LINEAR_LAYOUT + TEXT_VIEW + "[2]")
    protected WebElement txtPleaseSupport;

    @AndroidFindBy(id = ID + "button_no_thanks")
    protected WebElement btnNoThanks;

    @AndroidFindBy(id = ID + "button_pro")
    protected WebElement btnPro;

    @AndroidFindBy(id = ID + "button_retore")
    protected WebElement btnRestorePurchase;

    @AndroidFindBy(id = ANDROID_ID + "content")
    protected List<WebElement> listBtnsContent;

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

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, 'Favorites')]")
    protected WebElement btnKebabFavorites;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, 'Clear history')]")
    protected WebElement btnKebabClearHistory;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, 'Share application')]")
    protected WebElement btnKebabShareApplication;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, 'More apps')]")
    protected WebElement btnKebabMoreApps;

    @AndroidFindBy(xpath = "/" + RELATIVE_LAYOUT + TEXT_VIEW + "[contains(@text, 'About')]")
    protected WebElement btnKebabAbout;

    @AndroidFindBy(xpath = "/" + TEXT_VIEW + "[contains(@text, 'Export favorites')]")
    protected WebElement btnExportFavs;

    @AndroidFindBy(xpath = "/" + TEXT_VIEW + "[contains(@text, 'Clear favorites')]")
    protected WebElement btnClearFavs;

    @AndroidFindBy(xpath = "/" + TEXT_VIEW + "[contains(@text, 'Backup favorites')]")
    protected WebElement btnBackUpFavs;

    @AndroidFindBy(xpath = "/" + TEXT_VIEW + "[contains(@text, 'Restore favorites\n')]")
    protected WebElement btnRestoreFavs;

    @AndroidFindBy(id = ANDROID_ID + "content_preview_text")
    protected WebElement txtShareAppContentPreviewText;

    @AndroidFindBy(xpath =  "/" + LINEAR_LAYOUT + TEXT_VIEW + "[1]")
    protected WebElement txtAboutAppTitle;

    @AndroidFindBy(xpath =  "/" + LINEAR_LAYOUT + TEXT_VIEW + "[2]")
    protected WebElement txtAboutAppText;

    @AndroidFindBy(id = "com.movinapp.dict.english.american:id/text_link")
    protected WebElement txtAboutAppLink;

    @AndroidFindBy(id = "com.movinapp.dict.english.american:id/app_version")
    protected WebElement txtAboutAppVersion;

    protected WebElement btnAddToFavs(int id) {
        return findElement(MobileBy.xpath(
                format("/" + LIST_VIEW + LINEAR_LAYOUT + "[%d]" + CHECKBOX, id + 1)));
    }
}
