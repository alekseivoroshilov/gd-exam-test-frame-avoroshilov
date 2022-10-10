package mainPage;

import core.page.BaseTest;
import org.testng.annotations.Test;

import static constants.Constants.TestGroups.MAIN_PAGE;
import static constants.Constants.Words.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageFavoritesFeatureTest extends BaseTest {

    @Test(groups = {MAIN_PAGE}, description = "Add some results to favorites")
    public void testSearchAndAddToFavorites() {
        assertThat(mainPage.sendKeysToField(WORD_PERPETUAL)
                .clickSearch()
                .getAmountOfSearchResults())
                .describedAs("None word found by search")
                .isNotZero();
        mainPage.addWordsWithPartsOfSpeechToFavs(NOUN)
                .clearSearch()
                .verifyWordsWithSinglePartOfSpeechInFavs(NOUN);
    }

    @Test(groups = {MAIN_PAGE}, description = "Add words to favs and try another search")
    public void testSearchAfterAddingToFavorites() {
        assertThat(mainPage.sendKeysToField(WORD_MARVELOUS)
                .clickSearch()
                .addWordsWithPartsOfSpeechToFavs(ADVERB)
                .clearSearch()
                .verifyWordsWithSinglePartOfSpeechInFavs(ADVERB)
                .sendKeysToField(WORD_GRID)
                .getAmountOfSearchResults())
                .describedAs("None word found by search")
                .isNotZero();
        mainPage.clearSearch()
                .verifyWordsWithSinglePartOfSpeechInFavs(ADVERB);
    }
}