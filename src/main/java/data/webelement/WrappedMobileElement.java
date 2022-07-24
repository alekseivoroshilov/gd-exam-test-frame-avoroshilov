package data.webelement;

import exceptions.ActionFailedError;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class WrappedMobileElement extends MobileElement {
    private MobileElement mobileElement;
    private WebElement webElement;

    public WebElement getElement() {
        MobileElement element = getMobileElement();
        return element == null ? webElement : element;
    }

    public static boolean isWrapped(WebElement element) { return element instanceof WrappedMobileElement; }

    public MobileElement getMobileElement() { return mobileElement; }
    public WebElement getWebElement() { return webElement; }

    public boolean isNullRecursive(WebElement element) {
        if (element == null) return true;
        if (isWrapped(element)) return isNullRecursive(((WrappedMobileElement) element).getElement());
        return false;
    }

    public boolean isNullRecursive() {
        return isNullRecursive(getElement());
    }
    public MobileElement findElement(By by) {
        if (isNullRecursive())
            throw new ActionFailedError("WebElement not found by " + by.toString());
        return (MobileElement) getElement().findElement(by);
    }
}
