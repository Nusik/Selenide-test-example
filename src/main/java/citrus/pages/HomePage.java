package citrus.pages;

import citrus.fragments.SearchFragment;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends BasePage {

    SearchFragment searchFragment = new SearchFragment();

    public HomePage hoverMenuLine(String menuText) {
        $x("//div[@class='menu--desktop__drop-list show']/ul[@class='menu-aim']/li/a/span[2][contains(text(),'" + menuText + "')]")
                .hover();
        return this;
    }

    public HomePage clickLinkInMenuSmartphones(String linktext) {
        $x("//a[contains(@href,'/smartfony/brand-')]/span[contains(text(),'" + linktext + "')]").click();
        return this;
    }

    public HomePage clickLinkInMenuLaptops(String linkText) {
        $x("//a[contains(@href,'/noutbuki-i-ultrabuki/brand-acer/')]/span[contains(text(),'" + linkText + "')]")
                .click();
        return this;
    }

    public HomePage waitForPageToLoad() {
        super.waitForPageToLoad();
        return this;
    }

    public HomePage closePopUp() {
        super.closePopUp();
        return this;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }
}
