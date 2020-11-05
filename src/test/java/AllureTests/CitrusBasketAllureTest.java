package AllureTests;

import citrus.steps.ComparePageStep;
import citrus.steps.HomePageStep;
import citrus.steps.ProductListPageStep;
import citrus.steps.ProductPageStep;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketAllureTest {
    HomePageStep homePage;
    ProductListPageStep productListPage;
    ProductPageStep productPage;
    ComparePageStep comparePage;

    String menuSmartphones = "Смартфоны";
    String linkInMenuApple = "Apple";
    String productNameFull = "Apple iPhone 11 128Gb Black";
    String productNameBrand = "Apple iPhone";
    String productNameWithModel = "Apple iPhone 11";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua";
        open("/");
        homePage = new HomePageStep();
        productListPage = new ProductListPageStep();
        productPage = new ProductPageStep();
        comparePage = new ComparePageStep();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    @Test
    public void addProductToBasketViaMenu() {
        homePage.clickOnSmartphonesLinkInMenu(menuSmartphones, linkInMenuApple);
        productListPage.clickOnProduct(productNameFull);
        String productPrice = productPage.addProductToBasket();
        productPage.verifyBasketContent(productNameFull, productPrice);
    }

    @Test
    public void addProductToBasketViaSearchResults() {
        homePage.searchProduct(productNameWithModel);
        String productPrice = productListPage.addProductToBasketByPrice(productNameFull);
        productPage.verifyTwoProductsNamePriceInBasket(productNameFull, productPrice);
    }

    @Test
    public void addTwoProductsToCompare() {
        homePage.searchProduct(productNameBrand);

        String productPriceOne = productListPage.getProductPriceFromSearchResult(0);
        int productPriceOneInt = Integer.valueOf(productPriceOne.replaceAll(" ", ""));
        String productNameOne = productListPage.addProductToBasketByName(productNameBrand, 0);

        String productPriceTwo = productListPage.getProductPriceFromSearchResult(1);
        int productPriceTwoInt = Integer.valueOf(productPriceTwo.replaceAll(" ", ""));
        String productNameTwo = productListPage.addProductToBasketByName(productNameBrand, 2);

        productPage.verifyTwoProductsNamePriceFromSearch(productNameOne, productNameTwo, productPriceOne, productPriceTwo);

        int totalValue = productPriceOneInt + productPriceTwoInt;
        String totalValueString = String.valueOf(totalValue);
        productPage.getTotalValueInBasket(totalValueString);
    }

    @Test
    public void addProductsToBasketFromComparison() {
        homePage.searchProduct(productNameBrand);

        String productPriceOne = productListPage.getProductPriceFromSearchResult(0);
        int productPriceOneInt = Integer.valueOf(productPriceOne.replaceAll(" ", ""));
        String productNameOne = productListPage.addProductToCompareFromList(0);

        String productPriceTwo = productListPage.getProductPriceFromSearchResult(1);
        int productPriceTwoInt = Integer.valueOf(productPriceTwo.replaceAll(" ", ""));
        String productNameTwo = productListPage.addProductToCompareFromList(1);

        productListPage.openCompareWidget();
        comparePage.addToBasketFromCompare(0);
        comparePage.addToBasketFromCompare(2);
        comparePage.clickBasketInHeader();

        productPage.verifyTwoProductsNamePriceFromSearch(productNameOne, productNameTwo, productPriceOne, productPriceTwo);

        int totalValue = productPriceOneInt + productPriceTwoInt;
        String totalValueString = String.valueOf(totalValue);
        productPage.getTotalValueInBasket(totalValueString);
    }
}
