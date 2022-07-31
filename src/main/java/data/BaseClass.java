package data;

import data.models.DriverConfigs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Muted;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import static data.Constants.Configuration.TEST_PLATFORM;
import static data.Constants.Devices.ANDROID;
import static data.Constants.Devices.IOS;
import static data.providers.TestDataReader.readConfig;

public class BaseClass {
    public static AppiumDriver<MobileElement> driver;
    DriverConfigs CONFIG;

    @Muted
    @BeforeTest
    public void setup(){
        CONFIG = readConfig(TEST_PLATFORM);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, CONFIG.getPlatformName());
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, CONFIG.getDeviceName());
        //capabilities.setCapability(MobileCapabilityType.UDID, CONFIG.getUdid()); // I use simulator
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, CONFIG.getTimeout()); // 15 min
        capabilities.setCapability(MobileCapabilityType.APP, CONFIG.getApp());
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("appWaitActivity", "com.movinapp.dict.english.american.Dictionary");

        try {
            URL url = new URL(CONFIG.getUrl());
            switch (TEST_PLATFORM) {
                case IOS : {
                    driver = new IOSDriver<>(url, capabilities);
                    break;
                }
                case ANDROID : {
                    driver = new AndroidDriver<>(url, capabilities);
                    break;
                }
                default : {
                    driver = new AppiumDriver<>(url, capabilities);
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("Caused by: " + e.getCause());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Muted
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
