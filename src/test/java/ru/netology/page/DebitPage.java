package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    private SelenideElement debitTitle = $(byText("Оплата по карте"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement nameField = $$("fieldset > div:nth-of-type(3) .input__control").first();
    private SelenideElement cvcField = $("[placeholder='999']");
    ;
    private SelenideElement purchaseButton = $(byText("Продолжить"));
    private SelenideElement formatError = $(byText("Неверный формат"));
    private SelenideElement emptyFieldError = $(byText("Поле обязательно для заполнения"));
    private SelenideElement cardExpiryError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpired = $(byText("Истёк срок действия карты"));
    private SelenideElement approvedPurchaseNotification = $$(".notification__content").find(Condition.exactText("Операция одобрена Банком."));
    private SelenideElement declinedPurchaseNotification = $$(".notification__content").find(Condition.exactText("Ошибка! Банк отказал в проведении операции."));

    public DebitPage() {
        debitTitle.shouldBe(Condition.visible);
        cardNumberField.shouldBe(Condition.visible);
        monthField.shouldBe(Condition.visible);
        yearField.shouldBe(Condition.visible);
        nameField.shouldBe(Condition.visible);
        cvcField.shouldBe(Condition.visible);
        purchaseButton.shouldBe(Condition.visible);
    }

    public void payWithDebitCard(String number, String month, String year, String name, String CVC) {
        cardNumberField.setValue(number);
        monthField.setValue(month);
        yearField.setValue(year);
        nameField.setValue(name);
        cvcField.setValue(CVC);
        purchaseButton.click();
    }

    public void successfulPurchase() {
        approvedPurchaseNotification.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void declinedPurchase() {
        declinedPurchaseNotification.shouldBe(Condition.visible, Duration.ofSeconds(25));
    }

    public void invalidFormat() {
        formatError.shouldBe(Condition.visible);
    }

    public void emptyField() {
        emptyFieldError.shouldBe(Condition.visible);
    }

    public void invalidCardExpiry() {
        cardExpiryError.shouldBe(Condition.visible);
    }

    public void oldCard() {
        cardExpired.shouldBe(Condition.visible);
    }
}