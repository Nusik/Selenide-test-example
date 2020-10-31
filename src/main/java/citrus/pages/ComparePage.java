package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.ComparisonFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class ComparePage extends BasePage {

    ComparisonFragment comparisonFragment = new ComparisonFragment();
    BasketFragment basketFragment = new BasketFragment();

    SelenideElement addNewProductToCompare = $(".add-icon");
    ElementsCollection addToBasketProductBtn = $$x("//i[@class = 'icon-new-citrus-cart el-tooltip item']");


    public ComparePage addToBasket(int index) {
        addToBasketProductBtn.get(index).click();
        return this;
    }

    public ComparePage addNewProductToCompare() {
        addNewProductToCompare.click();
        return this;
    }

    public ComparePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public ComparisonFragment getComparisonFragment() {
        return comparisonFragment;
    }

}
