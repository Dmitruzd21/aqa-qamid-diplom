package UI;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;

import java.time.Duration;

public class UITests {
    //Кнопки
    private SelenideElement buttonBuy = $(Selectors.withText("Купить"));
    private SelenideElement buttonContinue = $(Selectors.withText("Продолжить"));
    //Номер карты
    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    // Месяц
    private SelenideElement month = $("[placeholder=\"08\"]");
    private SelenideElement errorOfCardValidity = $(Selectors.withText("Неверно указан срок действия карты"));
    //Год
    private SelenideElement year = $("[placeholder=\"22\"]");
    private SelenideElement ValidityError = $(Selectors.withText("Истёк срок действия карты"));
    //Владелец
    private SelenideElement owner = $("div:nth-child(3) span:nth-child(1) span.input__box input");
    private SelenideElement obligatoryOwnerField = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement onlyLatinLetters = $(Selectors.withText("Допустимо использовать только латинские буквы"));
    //CVC
    private SelenideElement cvcCvv = $("[placeholder=\"999\"]");
    //Неверный формат
    private SelenideElement notValidFormat = $(Selectors.withText("Неверный формат"));
    //Сообщения банка
    private SelenideElement successMessage = $(Selectors.withText("Успешно"));
    private SelenideElement approveMessage = $(Selectors.withText("Операция одобрена Банком."));
    private SelenideElement bankError = $(Selectors.withText("Ошибка"));
    private SelenideElement bankRejected = $(Selectors.withText("Ошибка! Банк отказал в проведении операции."));


    @BeforeAll

    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
    }


    @Test
    @DisplayName("Successful Payment")
    public void shouldBeSuccessfulPayment() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        successMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Card Number Field")
    public void shouldErrorIfLatinLettersInCardNumberField() {
        buttonBuy.click();
        cardNumber.setValue("ddddvvvvnnnnxxxx");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Card Number Field")
    public void shouldErrorIfCyrillicLettersInCardNumberField() {
        buttonBuy.click();
        cardNumber.setValue("ккккрррррнннноооо");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols Letters In Card Number Field")
    public void shouldErrorIfSymbolsInCardNumberField() {
        buttonBuy.click();
        cardNumber.setValue("%%%%????;;;;####");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Card Number Field")
    public void shouldErrorIfCardNumberFieldIsEmpty2() {
        buttonBuy.click();
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Bank Rejection If Number Of Declined Card")
    public void shouldBeBankRejectionIfNumberOfDeclinedCard() {
        buttonBuy.click();
        cardNumber.setValue("5555 6666 7777 8888");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        bankError.shouldBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Invalid Month Format")
    public void shouldErrorIfInvalidMonthFormat() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("2");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Not Existed Month 13")
    public void shouldErrorIfNotExistedMonth13() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("13");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Not Existed Month 0")
    public void shouldErrorIfNotExistedMonth0() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("0");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Month Field")
    public void shouldErrorIfLatinLettersInMonthField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("sd");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Month Field")
    public void shouldErrorIfCyrillicLettersInMonthField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("ва");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Month Field")
    public void shouldErrorIfSymbolsInMonthField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("%%");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Year More Than 5")
    public void shouldErrorIfYearMoreThan5() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("27");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Past Year")
    public void shouldErrorIfPastYear() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("20");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        ValidityError.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Year Field")
    public void shouldErrorIfEmptyYearField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Year Field")
    public void shouldErrorIfLatinLettersInYearField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("ff");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Year Field")
    public void shouldErrorIfCyrillicLettersInYearField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("нн");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Year Field")
    public void shouldErrorIfSymbolsInYearField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("%$");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Owner Field")
    public void shouldErrorIfCyrillicLettersInOwnerField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Иванов Иван");
        cvcCvv.setValue("563");
        buttonContinue.click();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Owner Field")
    public void shouldErrorIfSymbolsInOwnerField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("%^$#");
        cvcCvv.setValue("563");
        buttonContinue.click();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Owner Field")
    public void shouldErrorIfEmptyOwnerField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        cvcCvv.setValue("563");
        buttonContinue.click();
        obligatoryOwnerField.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Figures In Owner Field")
    public void shouldErrorIfFiguresInOwnerField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("5347457");
        cvcCvv.setValue("563");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("2 Figures In CVC Field")
    public void shouldErrorIf2FiguresInCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("22");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("1 Figure In CVC Field")
    public void shouldErrorIf1FiguresInCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("2");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In CVC Field")
    public void shouldErrorIfСyrillicLettersInCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("ррр");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In CVC Field")
    public void shouldErrorIfLatinLettersInCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("ddd");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In CVC Field")
    public void shouldErrorIfSymbolsInCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("#@%");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty CVC Field")
    public void shouldErrorIfEmptyCVCField() {
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        owner.setValue("Ivanov Ivan");
        buttonContinue.click();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }









}
