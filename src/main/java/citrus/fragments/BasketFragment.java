package citrus.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BasketFragment {

    SelenideElement widgetBasket = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection productNamesBasket = $$x("//a[@class='ex-active active ctrs-basket-product__name']");
    ElementsCollection productNameBasketFromSearch = $$x("//a[@class='ctrs-basket-product__name']");
    SelenideElement basketTotalPrice = $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']");
    ElementsCollection basketProductPrice = $$x("//div[@class='ctrs-mixed-price ctrs-mixed-price--showed-old ctrs-basket-product__mixed-price-value']");

    SelenideElement closeBasketBtn = $x("//i[@class='el-dialog__close el-icon el-icon-close']");
    SelenideElement basketBtnHeader = $("//div[@class='user-actions__cart h-icons__icon ctrs-basket-mini-icon']");

    public SelenideElement getBasket() {
        return widgetBasket;
    }

    public ElementsCollection getProductNamesFromBasket() {
        return productNamesBasket;
    }

    public ElementsCollection getProductNamesFromBasketWhenSearchByName() {
        return productNameBasketFromSearch;
    }

    public SelenideElement getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public ElementsCollection getBasketProductPrice() {
        return basketProductPrice;
    }

    public void closeBasketBtnClick() {
        closeBasketBtn.shouldBe(Condition.visible).click();
    }

    public void clickBasketInHeader() {
        basketBtnHeader.click();
    }
}
