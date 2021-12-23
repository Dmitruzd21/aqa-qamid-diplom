package UI.test;

import static com.codeborne.selenide.Selenide.$;

import UI.pages.ChoiceOfPaymentPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;

import java.time.Duration;

public class UIPaymentWithCardTests {
    // Месяц
    private SelenideElement errorOfCardValidity = $(Selectors.withText("Неверно указан срок действия карты"));
    //Год
    private SelenideElement ValidityError = $(Selectors.withText("Истёк срок действия карты"));
    //Владелец
    private SelenideElement obligatoryOwnerField = $(Selectors.withText("Поле обязательно для заполнения"));
    private SelenideElement onlyLatinLetters = $(Selectors.withText("Допустимо использовать только латинские буквы"));
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
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.allFieldsAreValid();
        successMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Card Number Field")
    public void shouldErrorIfLatinLettersInCardNumberField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Card Number Field")
    public void shouldErrorIfCyrillicLettersInCardNumberField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Card Number Field")
    public void shouldErrorIfSymbolsInCardNumberField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Card Number Field")
    public void shouldErrorIfCardNumberFieldIsEmpty() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cardNumberFieldIsEmpty();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Bank Rejection If Number Of Declined Card")
    public void shouldBeBankRejectionIfNumberOfDeclinedCard() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.numberOfDeclinedCard();
        bankError.shouldBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Invalid Month Format")
    public void shouldErrorIfInvalidMonthFormat() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.invalidMonthFormat();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Not Existed Month 13")
    public void shouldErrorIfNotExistedMonth13() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.notExistedMonth13();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Not Existed Month 0")
    public void shouldErrorIfNotExistedMonth0() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.notExistedMonth0();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Month Field")
    public void shouldErrorIfLatinLettersInMonthField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Month Field")
    public void shouldErrorIfCyrillicLettersInMonthField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Month Field")
    public void shouldErrorIfSymbolsInMonthField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Year More Than 5")
    public void shouldErrorIfYearMoreThan5() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.yearMoreThan5();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Past Year")
    public void shouldErrorIfPastYear() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.pastYear();
        ValidityError.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Year Field")
    public void shouldErrorIfEmptyYearField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In Year Field")
    public void shouldErrorIfLatinLettersInYearField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Year Field")
    public void shouldErrorIfCyrillicLettersInYearField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Year Field")
    public void shouldErrorIfSymbolsInYearField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In Owner Field")
    public void shouldErrorIfCyrillicLettersInOwnerField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInOwnerField();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In Owner Field")
    public void shouldErrorIfSymbolsInOwnerField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInOwnerField();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty Owner Field")
    public void shouldErrorIfEmptyOwnerField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyOwnerField();
        obligatoryOwnerField.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Figures In Owner Field")
    public void shouldErrorIfFiguresInOwnerField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.figuresInOwnerField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("2 Figures In CVC Field")
    public void shouldErrorIf2FiguresInCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.twoFiguresInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("1 Figure In CVC Field")
    public void shouldErrorIf1FiguresInCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.oneFiguresInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Cyrillic Letters In CVC Field")
    public void shouldErrorIfСyrillicLettersInCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Latin Letters In CVC Field")
    public void shouldErrorIfLatinLettersInCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Symbols In CVC Field")
    public void shouldErrorIfSymbolsInCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("Empty CVC Field")
    public void shouldErrorIfEmptyCVCField() {
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
    }

}
