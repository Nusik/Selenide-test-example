package citrus.steps;

import citrus.pages.ProductListPage;
import io.qameta.allure.Step;

public class ProductListPageStep {

    ProductListPage productListPage = new ProductListPage();

    @Step("Click on Product")
    public void clickOnProduct(String productName) {
        productListPage.waitForPageToLoad()
                .clickOnProductByName(productName);
    }

    @Step("Get product price")
    public String addProductToBasketByPrice(String productName) {
        productListPage.addProductToBasket(productName);
        return productListPage.waitForPageToLoad().getProductPriceByName(productName);
    }

    @Step("Add product to basket from search result")
    public String addProductToBasketByName(String productName, int productIndex) {
        productListPage.addProductToBasket(productName);
        return productListPage.getProductNameFromSearchResult(productIndex);
    }

    @Step("Get product price from search result")
    public String getProductPriceFromSearchResult(int productIndex) {
        productListPage.waitForPageToLoad();
        return productListPage.getProductPriceFromSearchResult(productIndex);
    }

    @Step("Add product to compare from List")
    public String addProductToCompareFromList(int productIndex) {
        productListPage.clickProductCompareBtn(productIndex);
        return productListPage.getProductNameFromSearchResult(productIndex);
    }

    @Step("Open Compare widget")
    public void openCompareWidget() {
        productListPage.clickCompareBtnHeader().waitForPageToLoad();
    }


}
