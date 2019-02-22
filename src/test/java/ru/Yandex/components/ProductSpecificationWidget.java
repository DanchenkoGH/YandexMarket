package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LaptopPower {
    private int workingHours;
    private int batteryCapacity;
    private int numberOfBatteryCells;
    private String batteryType;

    public LaptopPower(int workingHours, String batteryType) {
        System.out.println("\nДанные продукта: Питание");
        setWorkingHours(workingHours);
        setBatteryType(batteryType);
    }

    public LaptopPower(int workingHours, int numberOfBatteryCells, String batteryType) {
        System.out.println("\nДанные продукта: Питание");
        setWorkingHours(workingHours);
        setNumberOfBatteryCells(numberOfBatteryCells);
        setBatteryType(batteryType);
    }

    public LaptopPower(int workingHours, int batteryCapacity, int numberOfBatteryCells, String batteryType) {
        System.out.println("\nДанные продукта: Питание");
        setWorkingHours(workingHours);
        setBatteryCapacity(batteryCapacity);
        setNumberOfBatteryCells(numberOfBatteryCells);
        setBatteryType(batteryType);
    }

    public void setBatteryCapacity(int batteryCapacity) {
        System.out.println("    Емкость аккумулятора " + batteryCapacity + " Вт*ч");
        this.batteryCapacity = batteryCapacity;
    }

    public void setWorkingHours(int workingHours) {
        System.out.println("    Время работы " + workingHours);
        this.workingHours = workingHours;
    }

    public void setNumberOfBatteryCells(int numberOfBatteryCells) {
        System.out.println("    Количество ячеек батареи " + numberOfBatteryCells);
        this.numberOfBatteryCells = numberOfBatteryCells;
    }

    public void setBatteryType(String batteryType) {
        System.out.println("    Тип аккумулятора " + batteryType);
        this.batteryType = batteryType;
    }
}

public class ProductSpecificationWidget {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class = 'n-product-spec-wrap__body']")
    private WebElement laptopPowerArea;

    public ProductSpecificationWidget(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ProductSpecificationWidget getProductPowerSpecification() {

        int batteryCapacity, workingHours, numberOfBatteryCells;
        String batteryType;
        LaptopPower laptopPower;

        try {
            workingHours = Integer.parseInt((driver.findElement(By.xpath("//div[@class = 'n-product-spec-wrap__body']/h2[.='Питание']/.." +
                    "/dl[@class = 'n-product-spec']/dt/span[text()='Время работы от аккумулятора']" +
                    "/../../dd/span")).getText()).replaceAll("[^0-9]", ""));
        } catch (TimeoutException ex) {
            workingHours = -1;
        }

        try {
            batteryCapacity = Integer.parseInt((driver.findElement(By.xpath("//div[@class = 'n-product-spec-wrap__body']/" +
                    "./dl[@class = 'n-product-spec']/dt/span[.='Емкость аккумулятора']" +
                    "/../../dd/span")).getText()).replaceAll("[^0-9]", ""));
        } catch (TimeoutException ex) {
            batteryCapacity = -1;
        }

        try {
            numberOfBatteryCells = Integer.parseInt(driver.findElement(By.xpath("//div[@class = 'n-product-spec-wrap__body']/h2[.='Питание']/.." +
                    "/dl[@class = 'n-product-spec']/dt/span[text()='Количество ячеек аккумулятора']" +
                    "/../../dd/span")).getText());
        } catch (TimeoutException ex) {
            numberOfBatteryCells = -1;
        }

        try {
            batteryType = driver.findElement(By.xpath("//div[@class = 'n-product-spec-wrap__body']/./dl[@class = 'n-product-spec']/dt/span[.='Тип аккумулятора']/../../dd/span")).getText();
        } catch (TimeoutException ex) {
            batteryType = "";
        }

        if (workingHours > 0 && batteryCapacity > 0 && numberOfBatteryCells > 0 && batteryType != "")
            laptopPower = new LaptopPower(workingHours, batteryCapacity, numberOfBatteryCells, batteryType);
        else if (workingHours > 0 && batteryCapacity < 0 && numberOfBatteryCells > 0 && batteryType != "")
            laptopPower = new LaptopPower(workingHours, numberOfBatteryCells, batteryType);
        else if (workingHours > 0 && batteryCapacity < 0 && numberOfBatteryCells < 0 && batteryType != "")
            laptopPower = new LaptopPower(workingHours, batteryType);

        return new ProductSpecificationWidget(driver);
    }

    public ProductSpecificationWidget getFirstQuestionMarkItemText() throws InterruptedException {
        driver.findElement(By.xpath("(//span[contains(@class, 'n-hint-button__handle')])[3]")).click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'popup_visibility_visible')]//div[@class = 'n-hint-button__article']")));
        System.out.println("\nТекст подсказки: " + element.getAttribute("textContent"));

        return new ProductSpecificationWidget(driver);
    }
}
