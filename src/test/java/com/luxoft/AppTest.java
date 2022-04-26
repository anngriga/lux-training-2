package com.luxoft;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/luxoft/features",
    glue = "com.luxoft.steps",
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    plugin = {
        "json:target/cucumber3.json",
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
    }
)
public class AppTest {


    @BeforeClass
    public static void setUp(){
        Hooks.init();
    }

    @AfterClass
    public static void tearDown() {
        final WebDriver driver = Hooks.driver.get();
        if (driver != null) {
            Hooks.driver.get().quit();
        }
    }

}
