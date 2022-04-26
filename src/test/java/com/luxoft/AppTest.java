package com.luxoft;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/luxoft/features",
        glue = "com.luxoft.steps",
        tags = "@tag",
        dryRun = false,
        strict = true,
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "json:target/cucumber3.json",
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
        }
)
public class AppTest {

}
