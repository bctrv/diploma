package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getApprovedStatus() {
        return "APPROVED";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getDeclinedStatus() {
        return "DECLINED";
    }

    public static String getShortCardNumber() {
        return faker.number().digits(15);
    }

    public static String getLettersInCardField() {
        return "qwerty";
    }

    public static String getSymbolsInField() {
        return "!”№";
    }

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    public static String getInvalidMonth() {
        return "25";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getLettersInMonth() {
        return "qw";
    }

    public static String getValidYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(2));
    }

    public static String getYearOneSymbol() {
        return faker.number().digits(1);
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(2));
    }

    public static String getFutureYear() {
        LocalDate localdate = LocalDate.now();
        return String.format("%ty", localdate.plusYears(5));
    }

    public static String getLettersInYear() {
        return "QW";
    }

    public static String getValidName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidName() {
        return faker.name().username();
    }

    public static String getCyrillicName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getLongName() {
        return "johnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohn johnjohnjohnjohnjohnjohnjohnjohnjohnjohnjohn";
    }

    public static String getNumbersInName() {
        return faker.number().digits(3);
    }

    public static String getValidCVC() {
        return faker.number().digits(3);
    }

    public static String getCVCOneDigit() {
        return faker.number().digits(1);
    }

    public static String getCVCTwoDigits() {
        return faker.number().digits(2);
    }

    public static String getEmptyField() {
        return "";
    }

    public static String getLettersInCVC() {
        return "qwe";
    }
}
