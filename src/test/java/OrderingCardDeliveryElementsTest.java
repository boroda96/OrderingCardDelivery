import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class OrderingCardDeliveryElementsTest {
    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
    }

    public String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
   @Test
    void shouldTestPositiveCityEk() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestPositiveData7() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
       String planningDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id=date] input").click();
        if(!generateDate(3,  "MM").equals(generateDate(7,"MM"))) {
            $("div[class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $$("[data-day]").find(Condition.text(generateDate(7, "dd"))).click();
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldTestPositiveData20Day() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
        String planningDate = generateDate(20, "dd.MM.yyyy");
        $("[data-test-id=date] input").click();
        if(!generateDate(3,  "MM").equals(generateDate(20,"MM"))) {
            $("div[class='calendar__arrow calendar__arrow_direction_right']").click();
        }
        $$("[data-day]").find(Condition.text(generateDate(20, "dd"))).click();
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}