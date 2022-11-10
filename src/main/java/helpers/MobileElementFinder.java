package helpers;

import core.page.BaseUtil;
import core.elements.WrappedMobileElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static core.driver.DriverManager.getDriver;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MobileElementFinder extends BaseUtil {

    private static final int DEFAULT_WAIT_UNTIL_VISIBILITY_TIME_IN_SECONDS = 15;
    private static final int DEFAULT_ATTEMPTS_NUMBER = 2;
    private static final int DEFAULT_PIXELS_SCROLL_NUMBER = 60;

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
    }

    public static boolean isNotDisplayed(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
    }

    public static WebElement waitUntilVisibilityOf(WebElement element, long seconds) {
        return new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilVisibilityOf(WebElement element) {
        return waitUntilVisibilityOf(element, DEFAULT_WAIT_UNTIL_VISIBILITY_TIME_IN_SECONDS);
    }

    public void typeText(MobileElement element, String text) {
        element.sendKeys(text);
    }

    public static void click(WebElement element) {
        try {
            waitUntilVisibilityOf(element).click();
        } catch (Exception e) {
            waitUntilVisibilityOf(element, 10);
            element.click();
        }
    }

    public static String getElementText(WebElement element) {
        return element.getText();
    }

    @Step("usePhoneBackButton")
    public static void usePhoneBackButton() {
        getDriver().navigate().back();
    }

    public static WebElement findElement(By by) {
        try {
            return getDriver().findElement(by);
        } catch (WebDriverException e) {
            return null;
        }
    }

    public static List<WebElement> findElements(By by) {
        return getDriver().findElements(by);
    }

    /**
     * -------------------------------------------------------------------------------------------
     * The next methods are created for experiment purpose TODO
     *
     */
    public static WrappedMobileElement findElementUsingScroll(WebElement element, boolean isScrollToTopRequired) {
        return findElementUsingScroll(element, DEFAULT_ATTEMPTS_NUMBER, isScrollToTopRequired);
    }

    // experimental, not for analyzing
    public static WrappedMobileElement findElementUsingScroll(WebElement element, int attempts, boolean isScrollToTopRequired) {
        return findElementUsingScroll(element, attempts, isScrollToTopRequired, DEFAULT_PIXELS_SCROLL_NUMBER);
    }

    // experimental, not for analyzing
    private static WrappedMobileElement findElementUsingScroll(WebElement element, int attempts, boolean isScrollToTopRequired, int pixelsQuantity) {
        boolean isDisplayed = isDisplayed(element);

        if (element == null) {
            return null;
            // TODO failCurrentTest()
        }

        if (!isDisplayed) {
            if (isScrollToTopRequired) scrollToTheVeryTop();

            while (!(isDisplayed = isDisplayed(element)) && attempts-- >= 0) {
                scrollDownByPixels(pixelsQuantity);
            }
        }

        return isDisplayed ? new WrappedMobileElement(element) : new WrappedMobileElement();
    }

    // experimental, not for analyzing
    private static void scrollToTheVeryTop() {
        int x = 40;
        int endY = 400;

        swipe(x, 40, x, endY, 1000);
    }

    // experimental, not for analyzing
    private static void scrollDownByPixels(int pixels) {
        int x = 40;
        int endY = 40;

        swipe(x, 400 + pixels, x, endY, 1000);
    }

    // experimental, not for analyzing
    @Step("(x,y) swipe from ({0},{1}) to ({2}, {3}) with duration: {4}")
    public static void swipe(int x, int y, int endX, int endY, int duration) {
        try {
            TouchAction swipeAction = new TouchAction(getDriver());
            PointOption start = point(x, y);
            PointOption end = point(endX, endY);

            swipeAction.press(start)
                    .waitAction(waitOptions(ofMillis(duration)))
                    .moveTo(end)
                    .release();
        } catch (WebDriverException e) {
            throw e;
        }
    }
}
