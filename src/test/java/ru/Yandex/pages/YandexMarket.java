package ru.Yandex.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ru.Yandex.components.HeaderMainWidget;
import ru.Yandex.components.TopMenuOfProductTypes;
import ru.Yandex.pages.widgets.ProductDetailWidget;
import ru.Yandex.pages.widgets.ProductTypeWidget;

import static org.junit.Assert.assertEquals;

public class YandexMarket {
    WebDriver driver;

    private final static Logger LOG = LogManager.getLogger(YandexMarket.class);

    public HeaderMainWidget headerMainWidget;
    public TopMenuOfProductTypes topMenuOfProductTypes;
    public ProductTypeWidget productTypeWidget;
    public ProductDetailWidget productDetailWidget;

    public YandexMarket(WebDriver driver) {
        this.driver = driver;
        headerMainWidget = new HeaderMainWidget(driver);
        headerMainWidget.init(driver);

        topMenuOfProductTypes = new TopMenuOfProductTypes(driver);
        topMenuOfProductTypes.init(driver);

        productTypeWidget = new ProductTypeWidget(driver);
        productDetailWidget = new ProductDetailWidget(driver);
    }

    public YandexMarket checkProductSearchByIndex(int productIndex) throws InterruptedException {
        String productName;
        int productValue = productDetailWidget.listOfFoundProducts.getProductValue();

        if (productValue > productIndex) {
            productName = productDetailWidget.listOfFoundProducts.getProductName(productIndex);
            headerMainWidget.findProduct(productName);
            assertEquals(productName, productDetailWidget.listOfFoundProducts.getProductName(1));
        }
        return new YandexMarket(driver);
    }

    public YandexMarket getSortProductList() throws InterruptedException {
        Thread.sleep(10000);
        productDetailWidget.listOfFoundProducts.sortProductList();
        return new YandexMarket(driver);
    }

    public YandexMarket getMapPriceDifference() {
        productDetailWidget.listOfFoundProducts.MapPriceDifference();
        return new YandexMarket(driver);
    }

    public YandexMarket getProductSpecifications() {
        //Параметры пункта "Питание"
        productDetailWidget.listOfFoundProducts.
                getProductByIndex(2).
                goToSpecificationPage().
                getProductPowerSpecification();

        return new YandexMarket(driver);
    }

    public YandexMarket checkQuestionMark() throws InterruptedException {
        productDetailWidget.listOfFoundProducts.getProductByIndex(1).goToSpecificationPage();
        productDetailWidget.productSpecificationWidget.getFirstQuestionMarkItemText();
        return new YandexMarket(driver);
    }

    public YandexMarket getProductPriceDeference() throws InterruptedException {

        int minimum = productDetailWidget.filterPanel.sortByAscendingPrice().
                listOfFoundProducts.getFirstProductPrice(1);
        System.out.println("\nМинимальная стоимость продукта: " + minimum);

        int maximum = productDetailWidget.filterPanel.sortByDescendingPrice().
                listOfFoundProducts.getFirstProductPrice(1);
        System.out.println("Максимальная стоимость продукта: " + maximum);

        System.out.println("\nРазница в цене продуктов: " + (maximum - minimum));

        return new YandexMarket(driver);
    }
}