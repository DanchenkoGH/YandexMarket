package ru.Yandex.pages.widgets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ru.Yandex.components.MenuOfProducts;
import ru.Yandex.pages.AbstractPage;

public class ProductTypeWidget extends AbstractPage {

    WebDriver driver;
    public MenuOfProducts menuOfProducts;

    private final static Logger LOG = LogManager.getLogger(ProductTypeWidget.class);

    public ProductTypeWidget(WebDriver driver) {
        this.driver = driver;
        menuOfProducts = new MenuOfProducts(driver);
        menuOfProducts.init(driver);
    }

    public ProductDetailWidget goTo(String item) throws InterruptedException {
        findElementByLink(item).click();
        Thread.sleep(3000);  //ToDo переделать на wait
        checkPageHeader(item);
        return new ProductDetailWidget(driver);
    }

    private ProductTypeWidget checkPageHeader(String productHeader) {
        findElementByHeader(productHeader);
        return new ProductTypeWidget(driver);
    }
}
