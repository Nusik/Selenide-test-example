package citrus.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SearchFragment {

    SelenideElement searchInput = $x("//input[@id='search-input']");
    SelenideElement searchButtonSubmit = $x("//button[@type='submit']");

    public SearchFragment searchProduct(String productName) {
        searchInput.val(productName);
        searchButtonSubmit.click();
        return this;
    }

//    public SearchFragment searchProduct(String productName) {
//    searchInput.val(productName).pressEnter();
//        return this;
//    }


}
