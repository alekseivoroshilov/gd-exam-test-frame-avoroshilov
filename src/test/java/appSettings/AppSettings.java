package appSettings;

import data.BaseTest;
import data.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static data.constants.Constants.TestGroups.SETTINGS_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestListener.class)
public class AppSettings extends BaseTest {

    @Test(groups = {SETTINGS_PAGE}, description = "transition between Main Page and Settings Page")
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

    @Test(groups = {SETTINGS_PAGE}, description = "Settings Page elements test")
    public void testSettingsPageElementsPresence() {
        assertThat(mainPage.openSettingsPage()
                .isOpen())
                .describedAs("Settings page not opened")
                .isTrue();
        settingsPage.verifySettingsPageElements();
    }
}
