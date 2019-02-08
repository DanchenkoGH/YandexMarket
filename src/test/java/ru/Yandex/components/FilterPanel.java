package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.Yandex.pages.widgets.ProductDetailWidget;

public class FilterPanel {
    private WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='n-filter-panel-dropdown__main']")
    private WebElement filterPanel;

    public FilterPanel(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductDetailWidget sortByPrice() throws InterruptedException {
        filterPanel.findElement(By.xpath(".//div[contains(@class, 'n-filter-sorter')]/a[.='по цене']")).click();
        Thread.sleep(10000);
        return new ProductDetailWidget(driver);
    }

    public ProductDetailWidget sortByDescendingPrice() throws InterruptedException {

        sortByPrice();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//div[contains(@class, 'n-filter-sorter_sort_desc')]")));
        } catch (TimeoutException ex) {
            sortByPrice();
        }

        return new ProductDetailWidget(driver);
    }

    public ProductDetailWidget sortByAscendingPrice() throws InterruptedException {

        sortByPrice();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                    "//div[contains(@class, 'n-filter-sorter_sort_asc')]")));
        } catch (TimeoutException ex) {
            sortByPrice();
        }

        return new ProductDetailWidget(driver);
    }
}
