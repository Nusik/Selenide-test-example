package citrus.steps;

import citrus.pages.ComparePage;
import io.qameta.allure.Step;

public class ComparePageStep {

    ComparePage comparePage = new ComparePage();

    @Step("Add to basket from compare")
    public void addToBasketFromCompare(int productIndex) {
        comparePage.addToBasket(productIndex).waitForPageToLoad();
        comparePage.getBasketFragment().closeBasketBtnClick();
    }

@Step("Click basket in Header")
    public void clickBasketInHeader(){
    comparePage.getBasketFragment().clickBasketInHeader();
}

}
