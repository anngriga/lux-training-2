package com.luxoft.hooks;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
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

    public static void initialize() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        // driver уже не null
        if (wait.get() == null) {
            wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(15)));
        }

    }

    public Hooks() {
        System.out.println("In Hooks constructor!");
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

    @BeforeAll
    public static void beforeAll() {
        Hooks.initialize();
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Terminating...");
        final WebDriver driver = Hooks.driver.get();
        if (driver != null) {
            driver.quit();
        }
    }

    // Scenario hooks
    @Before("@ex1")
    public void hookEx1() {
        System.out.println("Before EX1 Hook");
    }

    @Before("@ex2")
    public void hookEx2() {
        System.out.println("Before EX2 Hook");
    }

    @Before("@ex3")
    public void hookEx3() {
        System.out.println("Before EX3 Hook");
    }

    @After(value = "@ex3", order = 1)
    public void afterEx3FirstHook() {
        System.out.println("After EX3 First Hook");
    }

    @After(value = "@ex3", order = 2)
    public void afterEx3SecondHook() {
        System.out.println("After EX3 Second Hook");
    }

}
