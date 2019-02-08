package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class Element {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 10);

    protected void wait(int milliseconds) throws InterruptedException {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement find(String xPath) {
        if (!isPresent(xPath))
            return driver.findElement(By.xpath(xPath));
        else
            return null;
    }

    public boolean isPresent(String xPath) {
        boolean present;
        try {
            driver.findElement(By.xpath(xPath));
            present = true;
        } catch (NoSuchElementException ex) {
            present = false;
        }
        return present;
    }

    //1 неявные ожидания
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            //добавить сообщение об ошибке
            throw ex;
        } catch (NoSuchElementException ex) {
            //добавить сообщение об ошибке
            return false;
        }
    }

/*
    //2 явные ожидания
    public boolean isElementPresent(By locator) {
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (TimeoutException ex) {
            //добавить сообщение об ошибке
            return false;
        }
    }
*/

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
