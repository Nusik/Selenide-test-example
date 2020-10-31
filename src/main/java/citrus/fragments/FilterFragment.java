package citrus.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class FilterFragment {

    ElementsCollection priceFilters = $$(".el-input__inner");
    ElementsCollection memoryCheckboxes = $$x("//div[contains(text(),'Объем встроенной памяти')]/../ul/li");
    SelenideElement memoryFilterTitle = $x("//div[contains(text(),'Объем встроенной памяти')]");
    SelenideElement materialFilterTitle = $x("//div[contains(text(),'Материалы корпуса')]");

    public FilterFragment setMinPriceFilter(String minPrice) {
        priceFilters.get(0).clear();
        priceFilters.get(0).sendKeys(minPrice);
        priceFilters.get(0).shouldHave(Condition.value(minPrice));
        return this;
    }

    public FilterFragment setMaxPriceFilter(String maxPrice) {
        priceFilters.get(1).clear();
        priceFilters.get(1).sendKeys(maxPrice);
        priceFilters.get(1).shouldBe(Condition.matchText(maxPrice));
        return this;
    }

    public void setMemorySize(int index) {
        memoryFilterTitle.scrollTo();
        memoryCheckboxes.get(index).click();
    }

    public void setMaterial(String material) {
        materialFilterTitle.scrollTo();
        $x("//div[contains(text(),'Материалы корпуса')]/../ul/li//a[contains(text(),'" + material + "')]")
                .click();
    }
}
