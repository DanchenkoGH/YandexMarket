package ru.Yandex.pages.widgets;

import org.openqa.selenium.WebDriver;
import ru.Yandex.components.MenuOfProducts;

public class ProductTypeWidget {

    WebDriver driver;
    public MenuOfProducts menuOfProducts;

    public ProductTypeWidget(WebDriver driver) {
        this.driver = driver;
        menuOfProducts = new MenuOfProducts(driver);
        menuOfProducts.init(driver);
    }
}
