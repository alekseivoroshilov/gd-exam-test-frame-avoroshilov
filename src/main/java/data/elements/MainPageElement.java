package data.elements;

import data.BaseClass;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainPageElement extends BaseClass {
    @AndroidFindBy(id = "search")
    protected WebElement txtSearch;
}
