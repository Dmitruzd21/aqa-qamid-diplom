package UI.test;

import static com.codeborne.selenide.Selenide.$;

import SQL.SQLMethods;
import UI.pages.ChoiceOfPaymentPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.allFieldsAreValid();
        successMessage.shouldBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldBe(Condition.visible);
        String statusAfterSendingDataToServer = sqlMethods.getStatusFromPaymentEntity();
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        long expectedNumberOfRawsFromPaymentEntity = initialNumberOfRawsFromPaymentEntity + 1;
        assertEquals(expectedNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
        assertEquals("APPROVED", statusAfterSendingDataToServer);
    }

    @Test
    @DisplayName("Latin Letters In Card Number Field")
    public void shouldErrorIfLatinLettersInCardNumberField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
        String statusAfterSendingDataToServer = sqlMethods.getStatusFromPaymentEntity();
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Cyrillic Letters In Card Number Field")
    public void shouldErrorIfCyrillicLettersInCardNumberField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
        String statusAfterSendingDataToServer = sqlMethods.getStatusFromPaymentEntity();
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Symbols In Card Number Field")
    public void shouldErrorIfSymbolsInCardNumberField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInCardNumberField();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Empty Card Number Field")
    public void shouldErrorIfCardNumberFieldIsEmpty() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cardNumberFieldIsEmpty();
        notValidFormat.shouldBe(Condition.visible);
        bankError.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Bank Rejection If Number Of Declined Card")
    public void shouldBeBankRejectionIfNumberOfDeclinedCard() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.numberOfDeclinedCard();
        String statusAfterSendingDataToServer = sqlMethods.getStatusFromPaymentEntity();
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        long expectedNumberOfRawsFromPaymentEntity = initialNumberOfRawsFromPaymentEntity + 1;
        bankError.shouldBe(Condition.visible, Duration.ofSeconds(15));
        bankRejected.shouldBe(Condition.visible);
        assertEquals(expectedNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
        assertEquals("DECLINED", statusAfterSendingDataToServer);
    }

    @Test
    @DisplayName("Invalid Month Format")
    public void shouldErrorIfInvalidMonthFormat() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.invalidMonthFormat();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Not Existed Month 13")
    public void shouldErrorIfNotExistedMonth13() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.notExistedMonth13();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Not Existed Month 0")
    public void shouldErrorIfNotExistedMonth0() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.notExistedMonth0();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Latin Letters In Month Field")
    public void shouldErrorIfLatinLettersInMonthField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Cyrillic Letters In Month Field")
    public void shouldErrorIfCyrillicLettersInMonthField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Symbols In Month Field")
    public void shouldErrorIfSymbolsInMonthField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInMonthField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Year More Than 5")
    public void shouldErrorIfYearMoreThan5() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.yearMoreThan5();
        errorOfCardValidity.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Past Year")
    public void shouldErrorIfPastYear() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.pastYear();
        ValidityError.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Empty Year Field")
    public void shouldErrorIfEmptyYearField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Latin Letters In Year Field")
    public void shouldErrorIfLatinLettersInYearField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Cyrillic Letters In Year Field")
    public void shouldErrorIfCyrillicLettersInYearField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Symbols In Year Field")
    public void shouldErrorIfSymbolsInYearField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInYearField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Cyrillic Letters In Owner Field")
    public void shouldErrorIfCyrillicLettersInOwnerField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInOwnerField();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Symbols In Owner Field")
    public void shouldErrorIfSymbolsInOwnerField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInOwnerField();
        onlyLatinLetters.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Empty Owner Field")
    public void shouldErrorIfEmptyOwnerField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyOwnerField();
        obligatoryOwnerField.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Figures In Owner Field")
    public void shouldErrorIfFiguresInOwnerField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.figuresInOwnerField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("2 Figures In CVC Field")
    public void shouldErrorIf2FiguresInCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.twoFiguresInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("1 Figure In CVC Field")
    public void shouldErrorIf1FiguresInCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.oneFiguresInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Cyrillic Letters In CVC Field")
    public void shouldErrorIfСyrillicLettersInCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.cyrillicLettersInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Latin Letters In CVC Field")
    public void shouldErrorIfLatinLettersInCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.latinLettersInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Symbols In CVC Field")
    public void shouldErrorIfSymbolsInCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.symbolsInCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

    @Test
    @DisplayName("Empty CVC Field")
    public void shouldErrorIfEmptyCVCField() {
        SQLMethods sqlMethods = new SQLMethods();
        long initialNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        ChoiceOfPaymentPage paymentPage = new ChoiceOfPaymentPage();
        var withCard = paymentPage.selectPaymentByCard();
        withCard.emptyCVCField();
        notValidFormat.shouldBe(Condition.visible);
        successMessage.shouldNotBe(Condition.visible, Duration.ofSeconds(15));
        approveMessage.shouldNotBe(Condition.visible);
        long finalNumberOfRawsFromPaymentEntity = sqlMethods.getNumberOfRawsFromPaymentEntity();
        assertEquals(initialNumberOfRawsFromPaymentEntity, finalNumberOfRawsFromPaymentEntity);
    }

}
