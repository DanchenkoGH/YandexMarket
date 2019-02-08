package ru.Yandex.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.YandexMarket;

public class ProductPropertiesWindow {
    private WebDriver driver;

    private final static Logger LOG = LogManager.getLogger(YandexMarket.class);

    @FindBy(xpath = "//div[contains(@class, 'n-product-summary')]")
    private WebElement productPropertiesInfo;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductPropertiesWindow(WebDriver driver) {
        LOG.info("ProductPropertiesWindow: Выполняется инициализация элементов страницы.");
        System.out.println("ProductPropertiesWindow: Выполняется инициализация элементов страницы.");
        this.driver = driver;
    }

    public String getProductName() {
        return (productPropertiesInfo.findElement(By.xpath(".//div/h1"))).getText();
    }
}
