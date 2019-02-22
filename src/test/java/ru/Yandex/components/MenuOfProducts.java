package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class MenuOfProducts extends Element {

    @FindBy(xpath = "//div[@class = 'theme_light']//a[.='Компьютеры']/../..")
    private WebElement productMenu;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public MenuOfProducts(WebDriver driver) {
        super(driver);
    }

    public ProductDetailWidget goTo(String item) {
        productMenu.findElement(By.xpath(".//a[.='" + item + "']")).click();
        return new ProductDetailWidget(driver);
    }

    private ProductDetailWidget checkPageHeader(String productHeader) {
        driver.findElement(By.xpath("//div/h1[.='" + productHeader + "']"));
        return new ProductDetailWidget(driver);
    }
}
