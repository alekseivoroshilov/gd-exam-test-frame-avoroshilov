package mainPage;

import core.page.BaseTest;
import org.testng.annotations.Test;

import static constants.Constants.TestGroups.FULL_REINSTALL;
import static constants.Constants.TestGroups.MAIN_PAGE;
import static constants.Constants.Words.WORD_UBIQUITOUS;
import static constants.Constants.Words.WRONG_WORD;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageSearchTest extends BaseTest {

    @Test(groups = {MAIN_PAGE}, description = "Search button, search field check.")
    public void testSearchFeature() {
        assertThat(mainPage.sendKeysToField(WORD_UBIQUITOUS)
                .clickSearch()
                .getAmountOfSearchResults())
                .describedAs("None word found by search")
                .isNotZero();
    }

    @Test(groups = {FULL_REINSTALL}, description = "Wrong word zero results check")
    public void testSearchWrongWord() {
        assertThat(mainPage.sendKeysToField(WRONG_WORD)
                .clickSearch()
                .getAmountOfSearchResults())
                .describedAs("Words were found for the wrong input")
                .isZero();
    }
}
