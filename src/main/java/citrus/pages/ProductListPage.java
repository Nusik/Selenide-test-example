package citrus.pages;

import citrus.fragments.FilterFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ProductListPage extends BasePage {

    FilterFragment filterFragment = new FilterFragment();

    SelenideElement compareButtonHeader = $(".user-actions__compare.tips-parent");
    SelenideElement productLike = $x("//button[@class='product-card__like']");

    ElementsCollection pricesListSearchResult = $$x("//span[@class='price-number']");
    ElementsCollection namesListSearchResult = $$x("//div[@class='title-itm']/h5");
    ElementsCollection namesListResultViaMenu = $$(".product-card__name");
    ElementsCollection pricesListResultViaMenu = $$(".prices__price");
    ElementsCollection productsListViaMenu = $$x("//div[@class='product-card__overview']");
    ElementsCollection materialValueInCard = $$x("//ul[@class='properties__items']/li[1]/span[@class='item__value']");

    public ProductListPage clickOnProductByName(String productName) {
        $x("//span[contains(text(),'" + productName + "')]").click();
        return this;
    }

    public ProductListPage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public ProductListPage closePopUp() {
        super.closePopUp();
        return this;
    }

    public String getProductPriceByName(String productName) {
        return $x("//a[contains(@title,'" + productName + "')]/..//div[@class='base-price']/span").getText();
    }

    public ProductListPage addProductToBasket(String productName) {
        $x("//a[contains(@title,'" + productName + "')]/..//i[@class='icon-new-citrus-cart el-tooltip item']").click();
        return this;
    }

    public String getProductPriceFromSearchResult(int index) {
        return pricesListSearchResult.get(index).getText();
    }

    public String getProductNameFromSearchResult(int index) {
        return namesListSearchResult.get(index).getText();
    }

    public ProductListPage clickProductCompareBtn(int index) {
        productLike.scrollTo();
        $$x("//span[@class='icon icon-comparison2 el-tooltip item']").get(index).shouldBe(Condition.visible).click();
        return this;
    }

    public ProductListPage clickCompareBtnHeader() {
        compareButtonHeader.click();
        return this;
    }

    public ElementsCollection getNamesListResultViaMenu() {
        return namesListResultViaMenu;
    }

    public ElementsCollection getPricesListResultViaMenu() {
        return pricesListResultViaMenu;
    }

    public boolean isProductPriceInFilterRange(String minPrice, String maxPrice) {
        for (SelenideElement product : getPricesListResultViaMenu()) {
            Integer price = Integer.parseInt(product.getText().replaceAll(" ", ""));

            if ((price >= Integer.valueOf(minPrice)) && (price <= Integer.valueOf(maxPrice))) {
                continue;
            } else {
                break;
            }
        }
        return true;
    }

    public boolean isProductNameMatchFilter(String filterName) {
        getNamesListResultViaMenu().get(0).scrollTo();
        for (SelenideElement product : getNamesListResultViaMenu()) {
            String name = product.getAttribute("title");
            if (name.contains(filterName)) {
                continue;
            } else {
                break;
            }
        }
        return true;
    }

    public SelenideElement getProductsMaterial() {
        for (SelenideElement product : productsListViaMenu) {
            product.hover();
        }
        return materialValueInCard.get(0);
    }

    public FilterFragment getFilterFragment() {
        return filterFragment;
    }
}
