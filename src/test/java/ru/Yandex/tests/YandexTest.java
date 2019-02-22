package ru.Yandex.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Test;

public class YandexTest extends BaseTest {

    @Feature("Функционал1")
    @Story("Аннотация Story1")
    @Test()
    @Description("Описание теста1")
    public void testFindLaptop1() throws InterruptedException {
        yandexMarket()
                .headerMainWidget.waitAndClickPopup()
                .topMenuOfProductTypes.goTo("Компьютерная техника")
                .menuOfProducts.goTo("Ноутбуки")
                .productSearchByParameters.setPriceTo("30000").setColor("Цвет белый", "Цвет черный").setBrand("HP", "Lenovo");
        yandexMarket().getSortProductList();
        yandexMarket().getProductPriceDeference();
        yandexMarket().getProductSpecifications();
        //yandexMarket().checkProductSearchByIndex(5);
    }

    @Feature("Функционал2")
    @Story("Аннотация Story2")
    @Test()
    @Description("Описание теста2")
    public void testFindLaptop2() throws InterruptedException {
        yandexMarket().headerMainWidget.findProduct("Ноутбук HP 15-bw590ur (AMD E2 9000E 1500 MHz/15.6\"/1920x1080/4Gb/500Gb HDD/DVD нет/AMD Radeon R2/Wi-Fi/Bluetooth/DOS)");
        yandexMarket().checkQuestionMark();
    }
}



