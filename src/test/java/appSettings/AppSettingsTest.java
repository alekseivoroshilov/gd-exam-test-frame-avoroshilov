package appSettings;

import core.page.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static constants.Constants.SettingsTexts.ENABLE_SPEECH;
import static constants.Constants.TestGroups.SETTINGS_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestListener.class)
public class AppSettingsTest extends BaseTest {

    @Test(groups = {}, description = "transition between Main Page and Settings Page")
    public void testVisitSettingsPageAndBackToMainMenu() {
        assertThat(mainPage.openSettingsPage()
                .isOpen())
                .describedAs("Settings page not opened")
                .isTrue();
        assertThat(settingsPage.goBackToMainPage()
                .isOpen())
                .describedAs("Main page not opened")
                .isTrue();
    }

    @Test(groups = {}, description = "Settings Page elements test")
    public void testSettingsPageElementsPresence() {
        assertThat(mainPage.openSettingsPage()
                .isOpen())
                .describedAs("Settings page not opened")
                .isTrue();
        settingsPage.verifySettingsPageElements();
    }

    @Test(groups = {}, description = "Settings page disabled section check")
    public void testSettingsPageSectionBlocking() {
        mainPage.openSettingsPage()
                .verifySpeechSectionIsBlocked()
                .setCheckBoxState(ENABLE_SPEECH, true)
                .verifySpeechSectionIsNotBlocked()
                .goBackToMainPage()
                .openSettingsPage()
                .verifySpeechSectionIsNotBlocked();
    }
}
