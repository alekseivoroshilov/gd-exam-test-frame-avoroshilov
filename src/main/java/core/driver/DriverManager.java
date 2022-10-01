package core.driver;

import exceptions.PlatformNotSupportedError;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static constants.Constants.Devices.ANDROID;
import static constants.Constants.Devices.IOS;
import static constants.Constants.Paths.APPIUM_PATH;
import static constants.Constants.Paths.NODE_PATH;
import static helpers.TestDataReader.readConfig;
import static java.util.Objects.isNull;

public class DriverManager {
    private static AppiumDriver<WebElement> driver;
    private static AppiumDriverLocalService server;
    public static DriverConfigs CONFIG;
    public static DesiredCapabilities capabilities;
    public static boolean AUTO_START_APPIUM_SERVER = true;

    public AppiumDriver<WebElement> getInstance(String target) throws IOException, PlatformNotSupportedError {
        System.out.println("Getting instance of: " + target);
        switch (target) {
            case ANDROID:
                return getAndroidDriver();
            case IOS:
                return getIOSDriver();
            default:
                throw new PlatformNotSupportedError("Please provide supported target");
        }
    }

    private AppiumDriver<WebElement> getAndroidDriver() throws IOException {
        CONFIG = readConfig(ANDROID);
        setCapabilities();
        return getDriver();
    }

    private AppiumDriver<WebElement> getIOSDriver() throws IOException {
        CONFIG = readConfig(IOS);
        setCapabilities();
        return getDriver();
    }

    private DesiredCapabilities setCapabilities() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, CONFIG.getPlatformName());
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, CONFIG.getDeviceName());
        //capabilities.setCapability(MobileCapabilityType.UDID, CONFIG.getUdid()); // I use simulator
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, CONFIG.getTimeout()); // 15 min
        capabilities.setCapability(MobileCapabilityType.APP, CONFIG.getApp());
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("appWaitActivity", "com.movinapp.dict.english.american.Dictionary");
        return capabilities;
    }

    public static AppiumDriver<WebElement> getDriver() {
        if (isNull(driver)) {
            try {
                if (AUTO_START_APPIUM_SERVER) {
                    Pattern p = Pattern.compile("(\\d){4,}");
                    String serverUrl = server.getUrl().toString();
                    Matcher matcher = p.matcher(serverUrl);
                    matcher.find();
                    String serverPort = matcher.group(0);
                    URL url = new URL(CONFIG.getUrl().replaceAll("(\\d){4,}", serverPort));
                    driver = new AppiumDriver<>(
                            url, capabilities);
                }
                else {
                    URL url = new URL(CONFIG.getUrl());
                    driver = new AppiumDriver<>(
                            url, capabilities);
                }
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                stopAppiumServer();
            }
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }

    @Step("closeApp")
    public static void closeApp() { //TODO Check if app closed
        int closeRetries = 10;
        while (closeRetries-- != 0) {
            try {
                terminateApp("");
                break;
            } catch (WebDriverException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Step("terminateApp")
    public static boolean terminateApp(String bungleId) {
        try {
            return getDriver().terminateApp(bungleId);
        } catch (Exception e) {
            return false;
        }
    }

    @Step("launchApp")
    public static void launchApp(int retries) {
        while (retries-- != 0) {
            try {
                getDriver().launchApp();
            } catch (WebDriverException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void startAppiumServer() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        // Use any port, in case the default 4723 is already taken (maybe by another Appium server)
        serviceBuilder.usingAnyFreePort();
        serviceBuilder.usingDriverExecutable(new File(NODE_PATH));
        serviceBuilder.withAppiumJS(new File(APPIUM_PATH));
        // The XCUITest driver requires that a path to the Carthage binary is in the PATH variable. I have this set for my shell, but the Java process does not see it. It can be inserted here.
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        serviceBuilder.withEnvironment(environment);

        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
    }

    public static void stopAppiumServer() {
        server.stop();
    }
}
