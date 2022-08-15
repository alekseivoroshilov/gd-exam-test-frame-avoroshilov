package data;

import data.driver.DriverManager;
import data.helpers.PropertiesReader;
import data.pages.AbstractMainPage;
import exceptions.PlatformNotSupportedError;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

import static data.driver.DriverManager.tearDown;
import static data.helpers.ReflectionUtils.invokeFields;

public abstract class BaseTest {
    protected AbstractMainPage mainPage;

    protected AppiumDriver driver;
    protected PropertiesReader reader = new PropertiesReader();

    public BaseTest () {}

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        context.setAttribute("target", reader.getTarget());

        try {
            String target = (String) context.getAttribute("target");
            this.driver = new DriverManager().getInstance(target);
        } catch (IOException | PlatformNotSupportedError e) {
            e.printStackTrace();
        }
        invokePagesIfNeeded(mainPage, driver);
    }

    @AfterSuite(alwaysRun = true)
    public void afterMethod() {
        tearDown();
    }

    protected <T extends BasePage> T invokePagesIfNeeded(T page, AppiumDriver driver) {
        if (page == null) {
            reInvokeFields(driver);
        }
        return page;
    }

    private void reInvokeFields(AppiumDriver driver) {
        invokeFields("getInstance",
                this,
                BaseTest.class,
                s -> s.contains("Page"),
                driver);
    }
}
