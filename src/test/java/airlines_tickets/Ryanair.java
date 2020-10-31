package airlines_tickets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Ryanair {

    @Test
    public void searchTicketsRyanair() {
        Configuration.baseUrl = "https://www.ryanair.com";
        Configuration.timeout = 10000;

        open("/");

        //Select language = "English"
        $x("//icon[@class='language-selector__button-chevron ng-star-inserted']").click();
        $x("//a[contains(text(),' Great Britain ')]").click();

        //Add departure station ("Vienna")
        $(By.id("input-button__departure")).clear();
        $(By.id("input-button__departure")).val("Vienna");
        $x("//span[contains(text(),'Vienna')]").click();

        //Add arrival station ("Kyiv")
        $(By.id("input-button__destination")).val("Kyiv");
        $x("//span[contains(text(),' Kyiv ')]").click();

        //Select departure and return dates (19-22 Nov)
        $x("//*[@data-id='2020-11-19'][@data-value='19']").click();
        $x("//*[@data-id='2020-11-21'][@data-value='21']").click();

        //Add number of passengers = 2 adults. Click search
        $x("//div[@class='counter__button-wrapper--enabled']").click();
        $(".flight-search-widget__start-search.ry-button--gradient-yellow").click();

        //Verify that there are 2 blocks: Vienna-Kyiv, Kyiv-Vienna
        //Verify dates are 19 Nov and 21 Nov accordingly
        $$x("//flight-list").shouldHaveSize(2);

        $$x("//span[@class='date-item__day-of-month h4 date-item__day-of-month--selected']").get(0)
                .shouldHave(Condition.text("19"));
        $$x("//span[@class='date-item__day-of-month h4 date-item__day-of-month--selected']").get(1)
                .shouldHave(Condition.text("21"));
    }
}