package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDesctiptionWidget {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(@class, 'link n-smart-link')][.='Характеристики']")
    private WebElement specificationsButton;

    public ProductDesctiptionWidget(WebDriver driver) {
        this.driver = driver;
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductSpecificationWidget goToSpecificationPage() {
        driver.findElement(By.xpath("//a[contains(@class, 'link n-smart-link')][.='Характеристики']")).click();
        return new ProductSpecificationWidget(driver);
    }
}
