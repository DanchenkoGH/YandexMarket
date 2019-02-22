package ru.Yandex.pages.widgets;

import org.openqa.selenium.WebDriver;
import ru.Yandex.components.*;

public class ProductDetailWidget {
    private WebDriver driver;
    public HeaderMainWidget headerMainWidget;
    public ProductSearchByParameters productSearchByParameters;
    public ListOfFoundProducts listOfFoundProducts;
    public FilterPanel filterPanel;
    public ProductDesctiptionWidget productDesctiptionWidget;
    public ProductSpecificationWidget productSpecificationWidget;

    public ProductDetailWidget(WebDriver driver) {
        this.driver = driver;
        productSearchByParameters = new ProductSearchByParameters(driver);
        productSearchByParameters.init(driver);

        headerMainWidget = new HeaderMainWidget(driver);
        headerMainWidget.init(driver);

        listOfFoundProducts = new ListOfFoundProducts(driver);
        listOfFoundProducts.init(driver);

        filterPanel = new FilterPanel(driver);
        filterPanel.init(driver);

        productDesctiptionWidget = new ProductDesctiptionWidget(driver);
        productDesctiptionWidget.init(driver);

        productSpecificationWidget = new ProductSpecificationWidget(driver);
        productSpecificationWidget.init(driver);
    }
}
