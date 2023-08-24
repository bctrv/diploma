package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private SelenideElement mainPageTitle = $(byText("Путешествие дня"));
    private SelenideElement debitButton = $$("[type= 'button']").first();
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    public MainPage() {
        mainPageTitle.shouldBe(Condition.visible);
        debitButton.shouldBe(Condition.visible);
        creditButton.shouldBe(Condition.visible);
    }

    public DebitPage debitPayment() {
        debitButton.click();
        return new DebitPage();
    }

    public CreditPage creditPayment() {
        creditButton.click();
        return new CreditPage();
    }
}
