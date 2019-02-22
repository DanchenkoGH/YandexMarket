package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPropertiesWindow extends Element {
    @FindBy(xpath = "//div[contains(@class, 'n-product-summary')]")
    private WebElement productPropertiesInfo;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductPropertiesWindow(WebDriver driver) {
        super(driver);
        System.out.println("ProductPropertiesWindow: Выполняется инициализация элементов страницы.");

    }

    public String getProductName() {
        return (productPropertiesInfo.findElement(By.xpath(".//div/h1"))).getText();
    }
}
