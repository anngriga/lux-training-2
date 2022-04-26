package com.luxoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CoursePage extends BasePage {

    public CoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
