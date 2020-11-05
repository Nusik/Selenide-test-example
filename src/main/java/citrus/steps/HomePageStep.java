package citrus.steps;

import citrus.pages.HomePage;
import io.qameta.allure.Step;

public class HomePageStep {

    HomePage homePage = new HomePage();

    @Step("Click on Smartphones linn in menu")
    public void clickOnSmartphonesLinkInMenu(String menuSmartphones, String linkInMenuApple) {
        homePage.waitForPageToLoad()
                .closePopUp()
                .hoverMenuLine(menuSmartphones)
                .clickLinkInMenuSmartphones(linkInMenuApple);
    }

    @Step("Search product")
    public void searchProduct(String productName) {
        homePage.waitForPageToLoad()
                .getSearchFragment()
                .searchProduct(productName);
    }

}
