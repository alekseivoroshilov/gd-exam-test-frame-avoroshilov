package data.driver;

import data.models.DriverConfigs;
import exceptions.PlatformNotSupportedError;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static data.Constants.Devices.ANDROID;
import static data.Constants.Devices.IOS;
import static data.providers.TestDataReader.readConfig;
import static java.util.Objects.isNull;

public class DriverManager {
    private static AppiumDriver driver;
    public static DriverConfigs CONFIG;
    public static DesiredCapabilities capabilities;

    public AppiumDriver getInstance(String target) throws IOException, PlatformNotSupportedError {
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

    private AppiumDriver getAndroidDriver() throws IOException {
        CONFIG = readConfig(ANDROID);
        setCapabilities();
        return getDriver();
    }

    private AppiumDriver getIOSDriver() throws IOException {
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

    public static AppiumDriver getDriver() {
        if (isNull(driver)) {
            try {
                URL url = new URL(CONFIG.getUrl());
                driver = new AppiumDriver(
                        url, capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }
}
