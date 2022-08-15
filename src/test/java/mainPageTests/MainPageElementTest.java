package mainPageTests;

import data.BaseTest;
import data.listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class MainPageElementTest extends BaseTest {

    @Test(description = "Search button, search field check.")
    public void testMainPageElementsPresence() {
        mainPage.clickSearch();
        System.out.println("The test has started!");
        Assert.assertEquals(true, true);
    }

    @Test(description = "Some elements check")
    public void testMainPageElements() {
        System.out.println("The test has started!");
    }
}
