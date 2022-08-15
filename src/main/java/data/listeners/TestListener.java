package data.listeners;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;

import static data.driver.DriverManager.getDriver;


public class TestListener implements ITestListener {

    protected AppiumDriver driver;
/*    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}
*/
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        driver = getDriver();
        /*try {
            wait(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Allure.addAttachment("screenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
/*
    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {}*/

}
