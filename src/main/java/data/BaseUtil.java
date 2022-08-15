package data;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;


import java.time.Duration;

import static data.driver.DriverManager.getDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

public class BaseUtil {

    public BaseUtil() {
        initElements(new AppiumFieldDecorator(getDriver(), Duration.ofSeconds(15)), this);
    }

}
