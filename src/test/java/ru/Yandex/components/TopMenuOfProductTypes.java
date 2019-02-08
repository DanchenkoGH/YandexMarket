package ru.Yandex.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.Yandex.pages.widgets.ProductTypeWidget;

public class TopMenuOfProductTypes {
    WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'n-w-tabs__horizontal-tabs-background')]")
    private WebElement topMenuList;

    private final static Logger LOG = LogManager.getLogger(TopMenuOfProductTypes.class);

    public TopMenuOfProductTypes(WebDriver driver) {
        this.driver = driver;
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductTypeWidget goTo(String item) {
        (topMenuList.findElement(By.xpath(".//div/a[.='" + item + "']"))).click();
        return new ProductTypeWidget(driver);
    }
}
