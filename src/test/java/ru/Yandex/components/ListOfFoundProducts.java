package ru.Yandex.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class ListOfFoundProducts {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'n-snippet-list')]" +
            "/div[contains(@class,'n-snippet-card2 i-bem b-zone b-spy-visible')]")
    private List<WebElement> productList;

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public ListOfFoundProducts(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductName(int productIndex) {

        String productName = (productList.get(productIndex - 1).findElement(By.xpath(".//div[contains(@class," +
                "'n-snippet-card2__title')]/a"))).getText();
        System.out.println(productName);
        return productName;
    }

    public int getFirstProductPrice(int productIndex) {

        String buf = productList.get(productIndex - 1).findElement(By.xpath(
                ".//div[contains(@class,'n-snippet-card2__part_type_right')]//div[@class = 'price']")).getText();
        return Integer.parseInt(buf.replaceAll("[^0-9]", ""));
    }

    public int getProductValue() {
        return productList.size();
    }

    public void sortProductList() {
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (WebElement product : productList) {
            String str = (product.findElement(By.xpath(".//div[@class = 'price']")).getText());
            map.put((product.findElement(By.xpath(".//div[contains(@class,'n-snippet-card2__title')]/a"))).getText(),
                    Integer.parseInt(str.replaceAll("[^0-9]", "")));
        }

        System.out.println("Список продуктов отсортирован");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("    Продукт - " + entry.getKey() + "   Цена: "
                    + entry.getValue());
        }
    }

    public ProductDesctiptionWidget getProductByIndex(int index) {
        productList.get(index - 1).findElement(By.xpath(".//div[contains(@class,'n-snippet-card2__title')]/a")).click();
        return new ProductDesctiptionWidget(driver);
    }

    private int getMapKeyAndMaximumValue(TreeMap<String, Integer> map) {
        String keyWithMaxVal = "";

        int maxValueInMap = (Collections.max(map.values()));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValueInMap)
                keyWithMaxVal = entry.getKey();
        }
        System.out.println("    Найден продукт - " + keyWithMaxVal + " с максимальной ценой " + maxValueInMap);
        return maxValueInMap;
    }

    private int getMapKeyAndMinimumValue(TreeMap<String, Integer> map) {
        String keyWithMinVal = "";

        int minValueInMap = (Collections.min(map.values()));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == minValueInMap)
                keyWithMinVal = entry.getKey();
        }
        System.out.println("    Найден продукт - " + keyWithMinVal + " с мимимальной ценой " + minValueInMap);
        return minValueInMap;
    }

    public void MapPriceDifference() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (WebElement product : productList) {
            String str = (product.findElement(By.xpath(".//div[@class = 'price']")).getText());
            map.put((product.findElement(By.xpath(".//div[contains(@class,'n-snippet-card2__title')]/a"))).getText(),
                    Integer.parseInt(str.replaceAll("[^0-9]", "")));
        }

        System.out.println("    Разница между самым дорогим и самым дешевым продуктом - " +
                (getMapKeyAndMaximumValue(new TreeMap(map)) - getMapKeyAndMinimumValue(new TreeMap(map))));
    }

}
