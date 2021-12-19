package UI;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Selectors;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;

public class UITests {
    private SelenideElement buttonBuy = $ (Selectors.withText("Купить"));
    private SelenideElement cardNumber = $ ("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement month = $ ("[placeholder=\"08\"]");
    private SelenideElement year = $ ("[placeholder=\"22\"]");
    private SelenideElement owner = $ (Selectors.withText("Владелец"));
    private SelenideElement cvcCvv = $ ("[placeholder=\"999\"]");
    private SelenideElement buttonContinue = $ (Selectors.withText("Продолжить"));


    @BeforeAll
    static void setUpAll () {
        SelenideLogger.addListener("allure",new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll (){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
    }



    @Test
    @DisplayName("1")
    public void should (){
        buttonBuy.click();
        cardNumber.setValue("1111222233334444");
        month.setValue("02");
        year.setValue("23");
        //owner.setValue("Ivanov Ivan");
        cvcCvv.setValue("563");
        buttonContinue.click();
    }




}
