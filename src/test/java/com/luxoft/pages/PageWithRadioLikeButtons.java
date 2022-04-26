package com.luxoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Catalogue, Contacts page
public class PageWithRadioLikeButtons extends BasePage {

    private static final String BUTTON_XPATH_TEMPLATE = "//div[@class='frame']/ul/li/a[text()='%s']";

    public PageWithRadioLikeButtons(WebDriver driver) {
        super(driver);
    }

    public boolean isButtonVisible(String buttonCaption) {
        final List<WebElement> elements =
            driver.findElements(By.xpath(String.format(BUTTON_XPATH_TEMPLATE, buttonCaption)));
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

}
