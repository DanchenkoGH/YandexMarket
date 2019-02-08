package ru.Yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractPage {
    public WebDriver driver;

  public WebElement findElementByHeader(String header) {
        return  driver.findElement(By.xpath("//div/h1[.='" + header + "']"));
    }

    public WebElement findElementByLink(String link) {
        return  driver.findElement(By.xpath("(//div/h1/a[.='" + link + "']"));
    }
}
