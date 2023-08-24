package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTourTest {

    CreditPage creditPage;
    MainPage mainPage;

    @BeforeEach
    void shouldOpenDebitPage() {
        mainPage = open("http://localhost:8080", MainPage.class);
        creditPage = mainPage.creditPayment();
    }

    @Test
    void ApprovedCardPurchaseWithValidData() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.successfulPurchase();
        var approvedStatus = DataHelper.getApprovedStatus();
        var creditStatus = SQLHelper.getCreditStatus();
        var transactionID = SQLHelper.getCreditInfo();
        var paymentID = SQLHelper.getCreditID();
        assertAll(
                () -> assertEquals(approvedStatus, creditStatus),
                () -> assertEquals(transactionID, paymentID)
        );
    }

    @Test
    void DeclinedCardPurchaseWithValidData() {
        var cardNumber = DataHelper.getDeclinedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.declinedPurchase();
        var declinedStatus = DataHelper.getDeclinedStatus();
        var creditStatus = SQLHelper.getCreditStatus();
        var transactionID = SQLHelper.getCreditInfo();
        var paymentID = SQLHelper.getCreditID();
        assertAll(
                () -> assertEquals(declinedStatus, creditStatus),
                () -> assertEquals(transactionID, paymentID)
        );
    }

    @Test
    void purchaseWithShortCardNumber() {
        var cardNumber = DataHelper.getShortCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithLettersInCardField() {
        var cardNumber = DataHelper.getLettersInCardField();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithSymbolsInCardField() {
        var cardNumber = DataHelper.getSymbolsInField();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithEmptyCardField() {
        var cardNumber = DataHelper.getEmptyField();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithMonthMoreThan12() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getInvalidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidCardExpiry();
    }

    @Test
    void purchaseWithZeroInMonthField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getZeroMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithLettersInMonthField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getLettersInMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithSymbolsInMonthField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getSymbolsInField();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithEmptyInMonthField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getEmptyField();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithOneNumberInYearField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getYearOneSymbol();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithPastYear() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getPastYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.oldCard();
    }

    @Test
    void purchaseWithFarFutureYear() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getFutureYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithLettersInYearField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getLettersInYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithSymbolsInYearField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getSymbolsInField();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithEmptyYearField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getEmptyField();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithOneWordName() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getInvalidName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithCyrrilicName() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getCyrillicName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithLongName() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getLongName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithNumbersInNameField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getNumbersInName();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithSymbolsInNameField() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getSymbolsInField();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithEmptyName() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getEmptyField();
        var cvc = DataHelper.getValidCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.emptyField();
    }

    @Test
    void purchaseWith1CharCVC() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getCVCOneDigit();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWith2CharCVC() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getCVCTwoDigits();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithLettersInCVC() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getLettersInCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithSymbolsInCVC() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getSymbolsInField();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
    }

    @Test
    void purchaseWithEmptyCVC() {
        var cardNumber = DataHelper.getApprovedCardNumber();
        var month = DataHelper.getValidMonth();
        var year = DataHelper.getValidYear();
        var owner = DataHelper.getValidName();
        var cvc = DataHelper.getEmptyField();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.emptyField();
    }

    @Test
    void purchaseWithEmptyFields() {
        var cardNumber = DataHelper.getEmptyField();
        var month = DataHelper.getEmptyField();
        var year = DataHelper.getEmptyField();
        var owner = DataHelper.getEmptyField();
        var cvc = DataHelper.getLettersInCVC();
        creditPage.payWithCreditCard(cardNumber, month, year, owner, cvc);
        creditPage.invalidFormat();
        creditPage.emptyField();
    }
}
