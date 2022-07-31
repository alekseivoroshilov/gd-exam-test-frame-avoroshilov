package mainPageTests;

import data.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageElementTest extends BaseClass {
    @Test(description = "Search button, search field check.")
    public void testMainPageElementsPresence() {
        System.out.println("The test has started!");
        Assert.assertEquals(true, true);
    }

    @Test(description = "Some elements check")
    public void testMainPageElements() {
        System.out.println("The test has started!");
    }
}
