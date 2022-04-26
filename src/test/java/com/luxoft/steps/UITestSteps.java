package com.luxoft.steps;

import com.luxoft.Hooks;
import com.luxoft.pages.CataloguePage;
import com.luxoft.pages.HomePage;
import com.luxoft.pages.PageWithRadioLikeButtons;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UITestSteps {

    // Driver & wait objects
    private final WebDriver driver = Hooks.driver.get();
    private final WebDriverWait wait = Hooks.wait.get();

    // Pages
    private HomePage homePage;
    private PageWithRadioLikeButtons schedulePage;
    private PageWithRadioLikeButtons contactsPage;
    private CataloguePage cataloguePage;

    @Given("открыта домашняя веб-страница Luxoft-Training")
    public void openWebPage() {
        assertTrue(
            driver != null && wait != null,
            "WebDriver или WebDriverWait-объекты не инициализированы"
        );
        driver.navigate().to("https://luxoft-training.ru");
        homePage = new HomePage(driver, wait);
    }

    @When("^выполнен переход на страницу (Расписание|Контакты|Каталог)$")
    public void pressButton(String pageName) {
        assertNotNull(homePage, "Главная страница не открыта");
        switch (pageName) {
            case "Расписание":
                schedulePage = homePage.openSchedulePage();
                break;
            case "Контакты":
                contactsPage = homePage.openContactsPage();
                break;
            case "Каталог":
                cataloguePage = homePage.openCataloguePage();
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

    @Then("доступна строка поиска курсов")
    public void catalogueSearchInputAccessible() {
        assertNotNull(cataloguePage, "Не открыта страница каталогов");
        assertTrue(cataloguePage.isSearchInputAccessible());
    }

    @Then("^(доступна ссылка на курс|доступно описание курса) по идентификатору (.+)$")
    public void courseIsFindable(String action, String courseId) {

        assertNotNull(cataloguePage, "Не открыта страница каталогов");
        final CataloguePage.CourseLink courseLink =
            assertDoesNotThrow(() -> cataloguePage.findCourseLink(courseId));
        switch (action) {
            case "доступна ссылка на курс":
                assertTrue(courseLink.getLink().isDisplayed());
                break;
            case "доступно описание курса":
                assertTrue(courseLink.followLink().isCourseDescriptionVisible());
                break;
            default:
                throw new IllegalArgumentException("Unsupported action: " + action);
        }

    }

}
