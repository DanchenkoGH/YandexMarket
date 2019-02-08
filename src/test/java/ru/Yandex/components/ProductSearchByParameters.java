package ru.Yandex.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class ProductSearchByParameters extends Element {
    private WebDriver driver;
    private final static Logger LOG = LogManager.getLogger(ProductSearchByParameters.class);

    @FindBy(xpath = "//div/h1")
    private WebElement productHeader;

    @FindBy(id = "glpriceto")
    private WebElement priceTo;

    @FindBy(id = "glpricefrom")
    private WebElement priceFrom;

    @FindBy(xpath = "//div/fieldset/legend[.='Производитель']/..")
    private WebElement manufacturer;

    @FindBy(xpath = "//div/fieldset/legend[.='Производитель']/..//a[.='Показать всё']")
    private WebElement manufacturerShowAll;

    public ProductSearchByParameters(WebDriver driver) {
        this.driver = driver;
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductDetailWidget showAll() {
        //ToDo --сделать, чтобы при повторной попытке не выдавал ошибки
        if (manufacturerShowAll.isEnabled()) {
            manufacturerShowAll.click();
        }
        return new ProductDetailWidget(driver);
    }

    public ProductDetailWidget setBrand(String... brand) throws InterruptedException {
        //showAll();//todo --включить Показать все

        for (String one : brand) {
            if (driver.findElement(By.xpath("//div/fieldset/legend[.='Производитель']/.././/label//div[.='" + one + "']")).isEnabled()) {
                driver.findElement(By.xpath("//div/fieldset/legend[.='Производитель']/.././/label//div[.='" + one + "']")).click();
            } else {
                LOG.debug("Продукция производителя, который вы пытаетесь выбрать недоступна для выбора");
                System.out.println("Продукция производителя, который вы пытаетесь выбрать недоступна для выбора");
            }
            wait(2000);
        }
        return new ProductDetailWidget(driver);
    }

    public ProductSearchByParameters setPriceRange(String from, String to) {
        setPriceFrom(from);
        setPriceTo(to);
        return new ProductSearchByParameters(driver);
    }

    public ProductSearchByParameters setPriceFrom(String from) {
        priceFrom.click();
        priceFrom.clear();
        priceFrom.sendKeys(from);
        return new ProductSearchByParameters(driver);
    }

    public ProductSearchByParameters setPriceTo(String to) {
        priceTo.click();
        priceTo.clear();
        priceTo.sendKeys(to);
        return new ProductSearchByParameters(driver);
    }

    public ProductSearchByParameters setColor(String... color) throws InterruptedException {
        for (String productColor : color) {
            if (driver.findElement(By.xpath("//div/fieldset/legend[.='Цвет']/..//input[@name = '" + productColor + "']")).isEnabled()) {
                driver.findElement(By.xpath("//div/fieldset/legend[.='Цвет']/..//input[@name = '" + productColor + "']")).sendKeys(Keys.SPACE);
            } else {
                System.out.println("Цвет товара, который вы пытаетесь выбрать недоступен для выбора");
            }
            wait(2000);
        }
        return new ProductSearchByParameters(driver);
    }
}
