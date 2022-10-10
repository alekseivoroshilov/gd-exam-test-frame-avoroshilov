package mainPage;

import core.page.BaseTest;
import org.testng.annotations.Test;

import static constants.Constants.TestGroups.MAIN_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageElementTest extends BaseTest {

    @Test(groups = {MAIN_PAGE}, description = "Some elements check")
    public void testMainPageElements() {
        mainPage.verifyMainPageElementsPresence()
                .clickRateApp()
                .verifyRateAppPopUpElements();
    }

    @Test(groups = {}, description = "Share app chooser")
    public void testMainPageShareApp() {
        mainPage.openKebabMenu()
                .clickShareApp()
                .verifyShareAppChooserElements();
    }

    @Test(groups = {}, description = "Share app chooser")
    public void testMainPageAbout() {
        mainPage.openAboutApp()
                .verifyAboutAppElements();

    }
}
