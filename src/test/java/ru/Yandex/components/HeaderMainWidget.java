package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.YandexMarket;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class HeaderMainWidget extends Element {
    private By productList = By.xpath("//div[@class = 'n-filter-applied-results__content preloadable i-bem preloadable_js_inited']");
    private By popupWindowLocator = By.xpath("//span[@class='button2__text'][.='Да, спасибо']/..");

    @FindBy(id = "header-search")
    private WebElement searchPanel;

    @FindBy(xpath = "//span[@class='search2__button']//button[.='Найти']")
    //@CacheLookup
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class, 'n-region-notification_js_inited')]")
    private WebElement popupWindow;

    @FindBy(xpath = "//span[@class='button2__text'][.='Да, спасибо']/..")
    private WebElement popupSubmitButton;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public HeaderMainWidget(WebDriver driver) {
        super(driver);
        ;
    }

    public ProductDetailWidget findProduct(String product) {
        searchPanel.click();
        searchPanel.clear();
        searchPanel.sendKeys(product);
        searchButton.click();
        waitElementPresence(productList);
        return new ProductDetailWidget(driver);
    }

    public YandexMarket waitAndClickPopup() {
        waitElementEnable(popupWindowLocator);
        popupSubmitButton.click();
        return new YandexMarket(driver);
    }
}
