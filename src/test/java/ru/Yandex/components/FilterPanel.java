package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class FilterPanel extends Element {

    @FindBy(xpath = "//div[@class='n-filter-panel-dropdown__main']")
    private WebElement filterPanel;

    public FilterPanel(WebDriver driver) {
        super(driver);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductDetailWidget sortByPrice() throws InterruptedException {
        filterPanel.findElement(By.xpath(".//div[contains(@class, 'n-filter-sorter')]/a[.='по цене']")).click();
        waitElementPresence(By.xpath("//div[@class = 'n-filter-applied-results__content preloadable i-bem preloadable_js_inited' and contains(@style, 'height: auto;')]"));
        return new ProductDetailWidget(driver);
    }

    public ProductDetailWidget sortByDescendingPrice() throws InterruptedException {
        sortByPrice();
        try {
            waitElementPresent(By.xpath("//div[contains(@class, 'n-filter-sorter_sort_desc')]"));
        } catch (TimeoutException ex) {
            sortByPrice();
        }
        return new ProductDetailWidget(driver);
    }

    public ProductDetailWidget sortByAscendingPrice() throws InterruptedException {
        sortByPrice();
        try {
            waitElementPresent(By.xpath("//div[contains(@class, 'n-filter-sorter_sort_asc')]"));
        } catch (TimeoutException ex) {
            sortByPrice();
        }
        return new ProductDetailWidget(driver);
    }
}
