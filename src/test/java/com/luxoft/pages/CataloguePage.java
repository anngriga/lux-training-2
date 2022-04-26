package com.luxoft.pages;

import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CataloguePage extends BasePage {

    @Data
    public static class CourseLink {

        private final WebElement link;
        private final WebDriver driver;
        private final WebDriverWait wait;

        public CoursePage followLink() {
            link.click();
            return new CoursePage(driver, wait);
        }

    }

    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='title-search-result']")
    private WebElement searchResults;

    public CataloguePage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public boolean isSearchInputAccessible() {
        try {
            return wait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .until(ignored -> searchInput.isDisplayed() && searchInput.isEnabled());
        } catch (TimeoutException exc) {
            return false;
        }
    }

    /**
     * Найти ссылку на курс по заданному имени курса
     * @param name Имя курса для поиска
     * @return URL найденного курса
     */
    public CourseLink findCourseLink(String name) {
        searchInput.sendKeys(name);
        final WebElement link = wait.until(ExpectedConditions.visibilityOf(searchResults))
            .findElement(By.xpath("//div[@class='title-search-result']//a[@href]"));
        return new CourseLink(link, driver, wait);
    }

}
