package mainPage;

import data.BaseTest;
import data.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static data.constants.Constants.TestGroups.MAIN_PAGE;
import static data.constants.Constants.Words.WORD2;
import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TestListener.class)
public class MainPageElementTest extends BaseTest {

    @Test(groups = {MAIN_PAGE}, description = "Search button, search field check.")
    public void testSearchFeature() {
        assertThat(mainPage.sendKeysToField(WORD2)
                .clickSearch()
                .getAmountOfSearchResults())
                .describedAs("None word found by search")
                .isNotZero();
    }

    @Test(groups = {MAIN_PAGE}, description = "Some elements check")
    public void testMainPageElements() {
        mainPage.verifyMainPageElementsPresence()
                .clickRateApp()
                .verifyRateAppPopUpElements();
    }
}
