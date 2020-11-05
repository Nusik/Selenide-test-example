package citrus.steps;

import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ProductPageStep {

    ProductPage productPage = new ProductPage();

    @Step("Add product to basket")
    public String addProductToBasket() {
        productPage.clickBuyButton();
        return productPage.getProductPrice();
    }

    @Step("Verify basket content")
    public void verifyBasketContent(String productName, String productPrice) {
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));

    }

    @Step("Verify two products Name Price in basket")
    public void verifyTwoProductsNamePriceInBasket(String productName, String productPrice) {
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(0).
                shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(productPrice));
    }

    @Step("Verify two products Name Price in SearchList")
    public void verifyTwoProductsNamePriceFromSearch(String productNameOne, String productNameTwo, String productPriceOne,
                                                     String productPriceTwo) {

        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().shouldHaveSize(2);
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(0).
                shouldHave(Condition.text(productNameOne));
        productPage.getBasketFragment().getProductNamesFromBasketWhenSearchByName().get(1).
                shouldHave(Condition.text(productNameTwo));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPriceOne));
        productPage.getBasketFragment().getBasketProductPrice().get(1).shouldHave(Condition.text(productPriceTwo));
    }

    @Step("Get Total value in Basket")
    public void getTotalValueInBasket(String totalValueString) {
        StringBuffer totalValueConverted = new StringBuffer(totalValueString);
        String totalValueWithSpace = totalValueConverted.insert(2, " ").toString();
        productPage.getBasketFragment().getBasketTotalPrice().shouldHave(Condition.text(totalValueWithSpace));
    }


}