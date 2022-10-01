package core.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;

import java.time.Duration;

import static core.driver.DriverManager.getDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

public abstract class BasePage {

    private final AppiumDriver currentDriver;

    protected BasePage() {
        currentDriver = getDriver();
        initElements(new AppiumFieldDecorator(currentDriver, Duration.ofSeconds(15)), this);
    }

    @Step("isOpen")
    public abstract boolean isOpen();

    /*public BasePage(AppiumDriver driver) {
        this.currentDriver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }*/
}
