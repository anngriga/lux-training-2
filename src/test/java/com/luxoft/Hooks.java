package com.luxoft;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public static final ThreadLocal<WebDriver> driver =
        ThreadLocal.withInitial(()->null);

    public static void init() {
        if (driver.get() == null) {
            driver.set(createDriver());
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
