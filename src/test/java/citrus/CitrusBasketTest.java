package citrus;

import citrus.pages.ComparePage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductPage productPage;
    ComparePage comparePage;

    String menuSmartphones = "Смартфоны";
    String linkInMenuApple = "Apple";
    String productNameFull = "Apple iPhone 11 128Gb Black";
    String productNameBrand = "Apple iPhone";
    String productNameWithModel = "Apple iPhone 11";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        open("");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        productPage = new ProductPage();
        comparePage = new ComparePage();
    }

    @BeforeMethod
    public void cleanBasket() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    @Test
    public void addProductToBasketViaMenu() {
        homePage.waitForPageToLoad()
                .hoverMenuLine(menuSmartphones)
                .clickLinkInMenuSmartphones(linkInMenuApple);

        productListPage.waitForPageToLoad()
                .clickOnProductByName(productNameFull);

        String productPrice = productPage.getProductPrice();
        productPage.clickBuyButton();

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productNameFull));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addProductToBasketViaSearchResults() {
        homePage.waitForPageToLoad()
                .getSearchFragment()
                .searchProduct(productNameWithModel);

        String productPrice = productListPage.waitForPageToLoad()
                .getProductPriceByName(productNameFull);

        productListPage.addProductToBasket(productNameFull);

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(0).
                shouldHave(Condition.text(productNameFull));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Test
    public void addTwoProductsToCompare() {
        homePage.waitForPageToLoad()
                .getSearchFragment()
                .searchProduct(productNameBrand);

        String productPriceOne = productListPage.waitForPageToLoad()
                .getProductPriceFromSearchResult(0);
        int productPriceOneInt = Integer.valueOf(productPriceOne.replaceAll(" ", ""));
        String productNameOne = productListPage.getProductNameFromSearchResult(0);
        productListPage.addProductToBasket(productNameOne);

        String productPriceTwo = productListPage.getProductPriceFromSearchResult(1);
        int productPriceTwoInt = Integer.valueOf(productPriceTwo.replaceAll(" ", ""));
        String productNameTwo = productListPage.getProductNameFromSearchResult(1);
        productListPage.addProductToBasket(productNameTwo);

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().shouldHaveSize(2);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(0).
                shouldHave(Condition.text(productNameOne));
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(1).
                shouldHave(Condition.text(productNameTwo));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPriceOne));
        productPage.getBasketFragment().getBasketProductPrice().get(1).shouldHave(Condition.text(productPriceTwo));

        int totalValue = productPriceOneInt + productPriceTwoInt;
        String totalValueString = String.valueOf(totalValue);
        StringBuffer totalValueConverted = new StringBuffer(totalValueString);
        String totalValueWithSpace = totalValueConverted.insert(2, " ").toString();

        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalValueWithSpace));
    }

    @Test
    public void addProductsToBasketFromComparison() {
        homePage.waitForPageToLoad()
                .getSearchFragment()
                .searchProduct(productNameBrand);

        String productPriceOne = productListPage.waitForPageToLoad()
                .getProductPriceFromSearchResult(0);
        int productPriceOneInt = Integer.valueOf(productPriceOne.replaceAll(" ", ""));

        String productNameOne = productListPage.getProductNameFromSearchResult(0);
        productListPage.clickProductCompareBtn(0);

        String productPriceTwo = productListPage.getProductPriceFromSearchResult(1);
        int productPriceTwoInt = Integer.valueOf(productPriceTwo.replaceAll(" ", ""));

        String productNameTwo = productListPage.getProductNameFromSearchResult(1);
        productListPage.clickProductCompareBtn(1);

        productListPage.clickCompareBtnHeader().waitForPageToLoad();
        comparePage.addToBasket(0).waitForPageToLoad();
        comparePage.getBasketFragment().closeBasketBtnClick();
        comparePage.addToBasket(2).waitForPageToLoad();
        comparePage.getBasketFragment().closeBasketBtnClick();

        comparePage.getBasketFragment().clickBasketInHeader();

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().shouldHaveSize(2);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(0).
                shouldHave(Condition.text(productNameOne));
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(1).
                shouldHave(Condition.text(productNameTwo));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPriceOne));
        productPage.getBasketFragment().getBasketProductPrice().get(1).shouldHave(Condition.text(productPriceTwo));

        int totalValue = productPriceOneInt + productPriceTwoInt;
        String totalValueString = String.valueOf(totalValue);
        StringBuffer totalValueConverted = new StringBuffer(totalValueString);
        String totalValueWithSpace = totalValueConverted.insert(2, " ").toString();

        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalValueWithSpace));
    }
}
