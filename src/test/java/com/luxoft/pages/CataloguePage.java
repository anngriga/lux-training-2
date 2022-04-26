package com.luxoft.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CataloguePage extends BasePage {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='title-search-result']")
    private WebElement searchResults;

    public CataloguePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Открыть PDF-файл каталога курсов. Откроется в новой вкладке,
     * которая будет неактивна в драйвере
     */
    public void openCataloguePDF() {
        findLinkWithText("Скачать каталог").click();
        switchTab();
    }

}
