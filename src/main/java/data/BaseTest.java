package data;

import data.driver.DriverManager;
import data.helpers.PropertiesReader;
import data.pages.AbstractMainPage;
import data.pages.AbstractSettingsPage;
import exceptions.PlatformNotSupportedError;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Muted;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;

import static data.constants.Constants.TestGroups.MAIN_PAGE;
import static data.driver.DriverManager.*;
import static data.helpers.ReflectionUtils.invokeFields;

public abstract class BaseTest {
    protected AbstractMainPage mainPage;
    protected AbstractSettingsPage settingsPage;

    protected AppiumDriver driver;
    protected PropertiesReader reader = new PropertiesReader();

    public BaseTest() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        context.setAttribute("target", reader.getTarget());
        AUTO_START_APPIUM_SERVER = reader.getAutoStartServer();
        if (AUTO_START_APPIUM_SERVER)
            startAppiumServer();

        try {
            String target = (String) context.getAttribute("target");
            this.driver = new DriverManager().getInstance(target);
        } catch (IOException | PlatformNotSupportedError e) {
            e.printStackTrace();
        }
        invokePagesIfNeeded(mainPage, driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        launchApp(1);
    }

    @Muted
    @BeforeMethod(onlyForGroups = MAIN_PAGE)
    public void clearStuff() {
        invokePagesIfNeeded(mainPage, driver);
        mainPage.clearFavorites()
                .clearHistory();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        closeApp();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        tearDown();
        if (AUTO_START_APPIUM_SERVER)
            stopAppiumServer();
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
