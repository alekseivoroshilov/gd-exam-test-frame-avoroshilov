package data.helpers;

import data.BaseClass;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

import static java.lang.Thread.sleep;


public class MobileElementFinder extends BaseClass {
    private static final boolean DEFAULT_SCROLL_TO_THE_TOP_REQUIRED = true;

    public boolean isElementDisplayed(MobileElement element){
        try {
            return element.isDisplayed();
        } catch(Exception e){
            System.out.println(e.getCause());
            return false;
        }
    }
    public WebElement waitUntilVisibilityOf(MobileElement element, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void sleepInSeconds(int seconds) {
        sleepInMilliseconds(seconds * 1000);
    }

    public static void sleepInMilliseconds (long milliseconds) {
        if (milliseconds < 0) return;
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            //TODO()
            System.out.println("there should be logger stuff");
        }
    }

    public void typeText(MobileElement element, String text) {
        element.sendKeys(text);
    }

    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e){
            sleepInSeconds(10);
            element.click();
        }

    }
}
