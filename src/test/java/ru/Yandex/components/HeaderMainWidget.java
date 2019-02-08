package ru.Yandex.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.YandexMarket;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class HeaderMainWidget {
    WebDriver driver;
    private final static Logger LOG = LogManager.getLogger(HeaderMainWidget.class);
    private By searchInput = By.xpath("//input[@id = 'header-search']");

    @FindBy(id = "header-search")
    private WebElement searchPanel;

    @FindBy(xpath = "//span[@class='search2__button']//button[.='Найти']")
    @CacheLookup
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'n-region-notification_js_inited')]")
    private WebElement popupWindow;

    @FindBy(xpath = "//span[@class='button2__text'][.='Да, спасибо']/..")
    private WebElement popupLocalisation;

    private By regionSubmitButton = By.xpath("//span[@class = 'button2__text'][.='Да, спасибо']");

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public HeaderMainWidget(WebDriver driver) {
        this.driver = driver;
    }

    public ProductDetailWidget findProduct(String product) throws InterruptedException {
        searchPanel.click();
        searchPanel.clear();
        searchPanel.sendKeys(product);
        searchButton.click();
        Thread.sleep(5000);
        return new ProductDetailWidget(driver);
    }

    public YandexMarket waitAndClickPopup() throws InterruptedException {
        WebElement element;
        do {
            Thread.sleep(1000);
            element = popupWindow;
        } while (!element.isEnabled());
        popupLocalisation.click();
        return new YandexMarket(driver);
    }
}
