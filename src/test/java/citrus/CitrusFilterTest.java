package citrus;

import citrus.pages.BasePage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

public class CitrusFilterTest extends BasePage {

    HomePage homePage;
    ProductListPage productListPage;

    String menuSmartphones = "Смартфоны";
    String linkInMenuXiaomi = "Xiaomi";
    String brandSamsung = "Samsung";
    String minPrice = "5000";
    String maxPrice = "15000";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        open("");
        homePage = new HomePage();
        productListPage = new ProductListPage();
    }

    @Test
    public void materialFilterTest() {
        homePage.waitForPageToLoad()
                .hoverMenuLine(menuSmartphones)
                .clickLinkInMenuSmartphones(linkInMenuXiaomi);
        productListPage.waitForPageToLoad();
        productListPage.getFilterFragment().setMaterial("Металл");

        productListPage.waitForPageToLoad();
        productListPage.getProductsMaterial().getText().contains("Металл");
        assertTrue(productListPage.isProductNameMatchFilter(linkInMenuXiaomi));
    }

    @Test
    public void priceFilterTest() {
        homePage.waitForPageToLoad()
                //.closePopUp()
                .hoverMenuLine(menuSmartphones)
                .clickLinkInMenuSmartphones(brandSamsung);
        productListPage.waitForPageToLoad()
                .getFilterFragment().setMinPriceFilter(minPrice);
        productListPage.waitForPageToLoad();
        productListPage.getFilterFragment().setMaxPriceFilter(maxPrice);
        productListPage.waitForPageToLoad();
        assertTrue(productListPage.isProductNameMatchFilter(brandSamsung));
        assertTrue(productListPage.isProductPriceInFilterRange(minPrice, maxPrice));
    }

    @Test
    public void memoryFilterTest() {
        homePage.waitForPageToLoad()
                .hoverMenuLine(menuSmartphones)
                .clickLinkInMenuSmartphones(linkInMenuXiaomi);
        productListPage.waitForPageToLoad();
        productListPage.getFilterFragment().setMemorySize(2);
        productListPage.getFilterFragment().setMemorySize(3);
        productListPage.waitForPageToLoad();

        productListPage.getNamesListResultViaMenu().filter(Condition.text("/32Gb"));
        productListPage.getNamesListResultViaMenu().filter(Condition.text("/64Gb"));
        assertTrue(productListPage.isProductNameMatchFilter(linkInMenuXiaomi));
    }
}
