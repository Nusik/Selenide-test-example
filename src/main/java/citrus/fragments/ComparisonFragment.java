package citrus.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ComparisonFragment {

    ElementsCollection compareProducts = $$(".title-itm");
    ElementsCollection compareProductsPrices = $$("//div[@class='compare']//span[@class='price-number']");

    ElementsCollection compareCheckboxes = $$(".el-checkbox");
    ElementsCollection comparePopupNames = $$(".product-name");
    ElementsCollection comparePopupPrices = $$x("//span[@class='price-new']//span[@class='price-number']");
    SelenideElement addButton = $x("//button[@class='el-button el-button--primary']");

    public ElementsCollection markCheckboxInList() {
        return compareCheckboxes;
    }

    public String getFirsProductName() {
        return comparePopupNames.get(1).getText();
    }

    public String getFirsProductPrice() {
        return comparePopupPrices.get(1).getText();
    }

    public void clickAdd() {
        addButton.click();
    }

    public ElementsCollection getProductNamesCompare() {
        return compareProducts;
    }

    public ElementsCollection getProductPriceCompare() {
        return compareProductsPrices;
    }
}
