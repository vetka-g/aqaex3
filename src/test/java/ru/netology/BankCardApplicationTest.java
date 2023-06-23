package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class BankCardApplicationTest {
    @Test
    void ShouldFillTheForm() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ромашка Полевая-Лечебная");
        form.$("[data-test-id=phone] input").setValue("+79123456789");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

   @Test
    void ShouldFillTheFormWithError() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Romashka Polevaya");
        form.$("[data-test-id=phone] input").setValue("+79123456789");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void ShouldFillThePhoneWithError() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ромашка Полевая");
        form.$("[data-test-id=phone] input").setValue("+7912345678");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void ShouldNotCheckTheBox() {
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ромашка Полевая");
        form.$("[data-test-id=phone] input").setValue("+7912345678");
        form.$("[data-test-id=agreement]");
        form.$(".button").click();
        $("[data-test-id=agreement]").shouldNotBe(selected);

    }
}
