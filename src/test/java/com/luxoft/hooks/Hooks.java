package com.luxoft.hooks;


import com.luxoft.DriverObject;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private static final ThreadLocal<DriverObject> driverObject =
        ThreadLocal.withInitial(()->null);

    public static DriverObject getDriverObject() {
        final DriverObject driverObj = driverObject.get();
        if (driverObj == null) {
            throw new IllegalStateException("Driver Object not initialized!");
        }
        return driverObj;
    }

    @BeforeAll
    public static void beforeAll() {
        driverObject.set(new DriverObject());
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Terminating...");
        final DriverObject driverObj = Hooks.driverObject.get();
        if (driverObj != null) {
            driverObj.getDriver().quit();
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

    @After
    public void makeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driverObject.get().getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName()); // ... and embed it in the report.
        }
    }

}
