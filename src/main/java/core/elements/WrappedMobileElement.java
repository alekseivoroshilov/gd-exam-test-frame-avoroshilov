package core.elements;

import exceptions.ActionFailedError;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;


/**
 * -------------------------------------------------------------------------------------------
 * The next methods are created for experiment purpose TODO
 * Don't try using them :)
 */
public class WrappedMobileElement extends MobileElement {
    private MobileElement mobileElement;
    private WebElement webElement;

    public WrappedMobileElement() {}

    public WrappedMobileElement(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public Point getCenter() {
        return super.getCenter();
    }


    public Point getMyCenter() {
        return this.getCenter();
    }

    public MobileElement getElement() {
        MobileElement element = getMobileElement();
        return element == null ? mobileElement : element;
    }

    public static boolean isWrapped(WebElement element) { return element instanceof WrappedMobileElement; }

    public MobileElement getMobileElement() { return mobileElement; }
    //public WebElement getWebElement() { return webElement; }

    public boolean isNullRecursive(WebElement element) {
        if (element == null) return true;
        if (isWrapped(element)) return isNullRecursive(((WrappedMobileElement) element).getElement());
        return false;
    }

    public boolean isNullRecursive() {
        return isNullRecursive(getElement());
    }

    @Override
    public MobileElement findElement(By by) {
        if (isNullRecursive())
            throw new ActionFailedError("WebElement not found by " + by.toString());
        return getMobileElement().findElement(by);
    }

    @Override
    public MobileElement findElement(String by, String using) {
           return getMobileElement().findElement(by, using);
    }
}
