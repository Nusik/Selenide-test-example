package citrus;

import citrus.pages.ComparePage;
import citrus.pages.HomePage;
import citrus.pages.ProductListPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class CitrusComparisonTest {
    HomePage homePage;
    ProductListPage productListPage;
    ComparePage comparePage;

    String productName = "Acer";
    String menuLaptops = "Ноутбуки, планшеты, МФУ";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        open("");
        homePage = new HomePage();
        productListPage = new ProductListPage();
        comparePage = new ComparePage();
    }

    @Test
    public void compareProductsTest() {
        homePage.waitForPageToLoad()
                .hoverMenuLine(menuLaptops)
                .clickLinkInMenuLaptops(productName);

        String firstProductPrice = productListPage.waitForPageToLoad()
                // .closePopUp()
                .getNamesListResultViaMenu().get(0).getText();
        String firstProductName = productListPage.getNamesListResultViaMenu().get(0).getText();
        String secondProductPrice = productListPage.getNamesListResultViaMenu().get(1).getText();
        String secondProductName = productListPage.getNamesListResultViaMenu().get(1).getText();

        productListPage.clickProductCompareBtn(0);
        productListPage.clickProductCompareBtn(1);
        productListPage.clickCompareBtnHeader().waitForPageToLoad();

        comparePage.getComparisonFragment().getProductNamesCompare().shouldHaveSize(4);
        comparePage.getComparisonFragment().getProductNamesCompare().get(1).shouldHave(Condition.text(firstProductName));
        comparePage.getComparisonFragment().getProductNamesCompare().get(2).shouldHave(Condition.text(secondProductName));
        comparePage.getComparisonFragment().getProductPriceCompare().get(1).shouldHave(Condition.text(firstProductPrice));
        comparePage.getComparisonFragment().getProductPriceCompare().get(2).shouldHave(Condition.text(secondProductPrice));
        comparePage.addNewProductToCompare();

        comparePage.getComparisonFragment().markCheckboxInList().get(1).click();
        String thirdProductName = comparePage.getComparisonFragment().getFirsProductName();
        String thirdProductPrice = comparePage.getComparisonFragment().getFirsProductPrice();
        comparePage.getComparisonFragment().clickAdd();

        comparePage.getComparisonFragment().getProductNamesCompare().shouldHaveSize(6);
        comparePage.getComparisonFragment().getProductNamesCompare().get(0).shouldHave(Condition.text(firstProductName));
        comparePage.getComparisonFragment().getProductNamesCompare().get(2).shouldHave(Condition.text(secondProductName));
        comparePage.getComparisonFragment().getProductNamesCompare().get(4).shouldHave(Condition.text(thirdProductName));

        comparePage.getComparisonFragment().getProductPriceCompare().get(0).shouldHave(Condition.text(firstProductPrice));
        comparePage.getComparisonFragment().getProductPriceCompare().get(2).shouldHave(Condition.text(secondProductPrice));
        comparePage.getComparisonFragment().getProductPriceCompare().get(4).shouldHave(Condition.text(thirdProductPrice));
    }
}
