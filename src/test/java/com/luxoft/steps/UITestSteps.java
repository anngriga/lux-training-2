package com.luxoft.steps;

import com.luxoft.Hooks;
import com.luxoft.pages.HomePage;
import com.luxoft.pages.SchedulePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UITestSteps {

    private final WebDriver driver = Hooks.driver.get();
    private final HomePage homePage = new HomePage(driver);
    private final SchedulePage schedulePage = new SchedulePage(driver);

    @Given("открыта веб-страница {string}")
    public void openWebPage(String url) {
        driver.navigate().to(url);
    }

    @When("нажата ссылка {string}")
    public void pressButton(String linkText) {
        homePage.clickOnLinkWithText(linkText);
    }

    @Then("отображаются кнопки")
    public void checkButtonsAreDisplayed(List<String> buttonNames) {
        assertTrue(
            buttonNames.stream().allMatch(schedulePage::isButtonVisible)
        );
    }

}
