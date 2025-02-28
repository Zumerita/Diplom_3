package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class MainPage extends BasePage {

    //Локатор для кнопки войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement buttonEnterInAccount;

    //Локатор для раздела Булки
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement sectionBuns;

    //Локатор для раздела Соусы
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sectionSauces;

    //Локатор для раздела Начинки
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement sectionToppings;

    //Локатор для определения выделенного раздела конструктора
    @FindBy(how = How.XPATH, using = ".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']")
    private SelenideElement selectedSection;

    //Локатор для заголовка Булки под блоками с разделами
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement headerBuns;

    //Локатор для заголовка Соусы под блоками с разделами
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement headerSauces;

    //Локатор для заголовка Начинки под блоками с разделами
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement headerToppings;


    //Локатор для кнопки Оформить заказ
    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement buttonCreateOrder;


    @Step("Нажатие кнопки \"Войти в аккаунт\" на главной странице")
    public void clickEnterInAccountButton() {
        buttonEnterInAccount.click();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkHeader();
    }

    @Step("Проверка, что после авторизации на кнопке отображается текст \"Оформить заказ\"")
    public void checkTextOnCreateOrderButton() {
        buttonCreateOrder.shouldBe(exactText("Оформить заказ"));
    }

    @Step("Выбор раздела Булки")
    public void clickSectionBuns() {
        sectionBuns.shouldBe(visible);
        sectionBuns.click();
        headerBuns.shouldBe(visible);
    }

    @Step("Выбор раздела Соусы")
    public void clickSectionSauces() {
        sectionSauces.shouldBe(visible);
        sectionSauces.click();
        headerSauces.shouldBe(visible);
    }

    @Step("Выбор раздела Начинки")
    public void clickSectionToppings() {
        sectionToppings.shouldBe(visible);
        sectionToppings.click();
        headerToppings.shouldBe(visible);
    }

    @Step("Получаем название выбранного раздела в конструкторе")
    public String getNameSelectedSection(String text) throws InterruptedException {
        // ждем две секунды, пока пройдет анимация смены раздела и текст в веб элементе будет соответствовать выбранному разделу
        selectedSection.shouldBe(Condition.exactText(text), Duration.ofSeconds(2));
        return selectedSection.getText();
    }


}
