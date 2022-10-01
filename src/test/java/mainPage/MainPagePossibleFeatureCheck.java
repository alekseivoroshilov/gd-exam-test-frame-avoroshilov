package mainPage;

import core.page.BaseTest;
import org.testng.annotations.Test;

import static constants.Constants.Words.NOUN;
import static constants.Constants.Words.WORD1;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPagePossibleFeatureCheck extends BaseTest {
    @Test(description = "Add some results to favorites")
    public void testSearchAndAddToFavorites() {
        assertThat(mainPage.sendKeysToField(WORD1)
                .clickSearch()
                .getAmountOfSearchResults())
                .describedAs("None word found by search")
                .isNotZero();
        mainPage.addWordsWithPartsOfSpeechToFavs(NOUN)
                .clearSearch()
                .verifyWordsWithSinglePartOfSpeechInFavs(NOUN);
    }
}