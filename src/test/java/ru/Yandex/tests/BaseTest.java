package ru.Yandex.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.Yandex.pages.YandexMarket;
import ru.Yandex.pages.widgets.ProductDetailWidget;
import ru.Yandex.pages.widgets.ProductTypeWidget;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver wd;
    private YandexMarket yandexMarket;
    private ProductDetailWidget productDetailWidget;
    private ProductTypeWidget productTypeWidget;
    private final static Logger LOG = LogManager.getLogger(BaseTest.class);

    public YandexMarket yandexMarket() {
        return yandexMarket;
    }

    public void start(String browser) throws Exception {
        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            File file = new File("C:/drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            wd = new InternetExplorerDriver();
        } else System.out.println("Ахтунг: Инициализация драйвера не выполнена");

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.get("https://market.yandex.ru/");

        yandexMarket = new YandexMarket(wd);
    }

    public void stop() {
        wd.quit();
    }

    @cucumber.api.java.Before
    @Before
    public void beforeTest() throws Exception {
        start(BrowserType.CHROME);
    }

    @cucumber.api.java.After
    @After
    public void afterTest() {
        stop();
    }
}
