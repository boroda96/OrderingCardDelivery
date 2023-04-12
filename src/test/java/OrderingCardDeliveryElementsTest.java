import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String generateDate(long addDays, String pattern) {
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
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestPositiveData7() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
        $(".icon_name_calendar").click();
        //$x("/html/body/div[2]/div/div/div/div/div/div/div[4]").click();
        //$(byText("Май 2023")).shouldBe(Condition.visible);
        $x("/html/body/div[2]/div/div/div/div/div/table/tbody/tr[5]/td[3]").click();
        //$(byText("19")).click();
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + "19.04.2023"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldTestPositiveDataNextMonth() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
        $(".icon_name_calendar").click();
        $x("/html/body/div[2]/div/div/div/div/div/div/div[4]").click();
        $(byText("Май 2023")).shouldBe(Condition.visible);
        //$x("/html/body/div[2]/div/div/div/div/div/table/tbody/tr[5]/td[3]").click();
        $(byText("19")).click();
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + "19.05.2023"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldTestPositiveDataNextYears() {
        $("[data-test-id=city] input").setValue("Ек");
        $(byText("Екатеринбург")).click();
        $(".icon_name_calendar").click();
        $x("/html/body/div[2]/div/div/div/div/div/div/div[3]").click();
        $x("/html/body/div[2]/div/div/div/div/div/div/div[4]").click();
        $(byText("Май 2024")).shouldBe(Condition.visible);
        $(byText("20")).click();
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + "20.05.2024"), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}