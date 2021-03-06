package com.luxoft;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/luxoft/features",
    tags = "not @ex2",
    glue = {"com.luxoft.steps", "com.luxoft.hooks"},
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    plugin = {
        "json:target/cucumber3.json",
        "pretty",
        "html:target/cucumber-reports/cucumber-pretty.html",
    }
)
public class AppTest {

}
