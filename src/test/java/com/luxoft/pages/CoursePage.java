package com.luxoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CoursePage extends BasePage {

    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@id='description' and @class='course-content__block']")
    private WebElement courseDescription;

    public CoursePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isCourseDescriptionVisible() {
        try {
            return wait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .until(ignored -> courseDescription.isDisplayed());
        } catch (TimeoutException exc) {
            return false;
        }
    }

    /**
     * Найти количество видимых кнопок с текстом "Записаться на курс"
     * @return Количество отображаемых кнопок
     */
    public int findCountOfVisibleEnrollButtons() {

        List<WebElement> buttons = driver.findElements(
                By.xpath("//div[not (@style)]/span[text()='Записаться на курс']")
        );

        int visibleCount = 0;
        for (WebElement btn : buttons) {
            if (btn.isDisplayed()) {
                visibleCount++;
            }
        }
        return visibleCount;

    }

}
