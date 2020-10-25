import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.*;

public class Flyua {

    @Test
    public void searchTicketsFlyuia() throws AWTException {
        Configuration.baseUrl = "https://www.flyuia.com/";
        Configuration.timeout = 10000;
        open("/");

        //Select language = "English"
        $x("//span[@class='country-chooser__lang']").click();
        $x("//span[@class='input input_select country-chooser__input input_in-focus'][2]").click();
        $x("//li[2]//a[@class='country-chooser__language '][contains(text(),'English')]").click();
        $(".btn-v2.btn-blue.btn-small.country-chooser__btn").click();
        sleep(2000);

        //Add departure station ("Vienna"), arrival station ("Kyiv")
        Robot robotDeparture = new Robot();
        $x("//sw-form-control-suggester[@formcontrolname='from']").click();
        robotDeparture.keyPress(KeyEvent.VK_V);
        robotDeparture.keyPress(KeyEvent.VK_I);
        robotDeparture.keyPress(KeyEvent.VK_E);
        robotDeparture.keyPress(KeyEvent.VK_N);
        sleep(2000);
        robotDeparture.keyPress(KeyEvent.VK_ENTER);

        Robot robotArrival = new Robot();
        $x("//sw-form-control-suggester[@formcontrolname='to']").click();
        robotArrival.keyPress(KeyEvent.VK_K);
        robotArrival.keyPress(KeyEvent.VK_Y);
        robotArrival.keyPress(KeyEvent.VK_I);
        robotArrival.keyPress(KeyEvent.VK_V);
        sleep(1500);
        robotArrival.keyPress(KeyEvent.VK_ENTER);

        //Select departure and return dates (19-22 Nov)

        $x("//*[@formcontrolname='departureDate']").click();
        $$x("//span[@class='prev-next-btn']").last().click();
        $x("//button[contains(text(),'19')]").click();
        $x("//span[@class='obe-sw-icon-calendar-arrivals']").click();
        $x("//button[contains(text(), '22')]").click();

        //Add number of passengers = 2 adults
        $x("//span[@class='obe-sw-icon-passenger']").click();
        $x("//button[@class='set-val-btn fx-row__center__center fx-flex-15'][contains(text(),'+')]").click();
        $x("//span[@class='obe-sw-icon-promo-code']").click();

        //Click search
        $x("//form[@class='search-flights-button']").click();

        switchTo().window("Search results");

        //close iframe
        $x("//i[@class='icon-close']").click();

        //Verify that there are 2 blocks: Vienna-Kyiv, Kyiv-Vienna
        //Verify dates are 19 Nov and 22 Nov accordingly
        $$x("//div[@class='product__title']").shouldHaveSize(2);

        $x("//div[@class='product__title'][contains(text(),'Vienna - Kyiv')]").shouldBe(Condition.visible);
        $x("//div[@class='product__title'][contains(text(),'Kyiv - Vienna')]").shouldBe(Condition.visible);
        $x("//button[@class='date-container nonclickable ng-star-inserted']/div[contains(text(),'19')]")
                .shouldBe(Condition.visible);
        $x("//button[@class='date-container nonclickable ng-star-inserted']/div[contains(text(),'22')]")
                .shouldBe(Condition.visible);
    }
}
