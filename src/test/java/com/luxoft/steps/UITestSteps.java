package com.luxoft.steps;

import com.luxoft.Hooks;
import com.luxoft.pages.HomePage;
import com.luxoft.pages.PageWithRadioLikeButtons;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UITestSteps {

    // Driver
    private final WebDriver driver = Hooks.driver.get();

    // Pages
    private HomePage homePage;
    private PageWithRadioLikeButtons schedulePage;
    private PageWithRadioLikeButtons contactsPage;

    @Given("открыта веб-страница {string}")
    public void openWebPage(String url) {
        assertNotNull(driver, "WebDriver не инициализирован");
        driver.navigate().to(url);
        homePage = new HomePage(driver);
    }

    @When("^выполнен переход на страницу (Расписание|Контакты)$")
    public void pressButton(String pageName) {
        assertNotNull(homePage, "Главная страница не открыта");
        switch (pageName) {
            case "Расписание":
                schedulePage = homePage.openSchedulePage();
                break;
            case "Контакты":
                contactsPage = homePage.openContactsPage();
                break;
            default:
                throw new IllegalArgumentException("Unsupported page: " + pageName);
        }
    }

    @Then("^(не )?отображаются кнопки$")
    public void checkButtonsAreDisplayed(String action, List<String> buttonNames) {

        assertTrue(
            schedulePage != null ^ contactsPage != null,
            "Не открыта страница с кнопками, нужными для данного шага, " +
                "либо открыты обе страницы с кнопками. Проверьте тестовый набор.");
        final PageWithRadioLikeButtons page =
            (schedulePage != null) ?
                schedulePage : contactsPage;

        final Stream<String> namesStream = buttonNames.stream();
        assertTrue(
            (action == null) ?
                namesStream.allMatch(page::isButtonVisible) :
                namesStream.noneMatch(page::isButtonVisible)
        );

    }

}
