import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class OrderingCardDeliveryTest {
    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
    }

    @Test
    void shouldTestPositive() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
        }
    @Test
    void shouldTestNegativeCity() {
        $("[data-test-id=city] input").setValue("Геленджик");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible, Duration.ofSeconds(15));
    }
    @Test
    void shouldTestEmptyCity() {
        $("[data-test-id=city] input").setValue("");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNegativeDate() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(text("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldTestEmptyDate() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=name] input").setValue("Иванов Петр");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(text("Неверно введена дата"));
    }
    @Test
    void shouldTestNegativeName() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Ivanov");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestEmptyName() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        //$("[data-test-id=name] input").setValue("Ivanov");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNegativePhone() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+799000000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestEmptyPhone() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(text("Поле обязательно для заполнения"));
    }
    @Test
    void shouldTestNegativeCheckBox() {
        $("[data-test-id=city] input").setValue("Екатеринбург");
        String dateDelivery = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dateDelivery);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79900000000");
        $(byText("Забронировать")).click();
        $("[data-test-id=agreement].input_invalid").shouldBe(visible);
    }

}