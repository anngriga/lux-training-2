package com.luxoft;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    public static final ThreadLocal<WebDriver> driver =
        ThreadLocal.withInitial(()->null);
    public static final ThreadLocal<WebDriverWait> wait =
        ThreadLocal.withInitial(()->null);

    public static void init() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        // driver уже не null
        if (wait.get() == null) {
            wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(15)));
        }
    }

    private static WebDriver createDriver(){

        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

        final ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setHeadless(false);

        final WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;

    }

}
