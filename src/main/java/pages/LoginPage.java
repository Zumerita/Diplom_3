package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pojo.User;

import static com.codeborne.selenide.Condition.*;

public class LoginPage extends BasePage {

    //Локатор для заголовка Вход
    @FindBy(how = How.XPATH, using = ".//h2[text()='Вход']")
    private SelenideElement headerEnter;

    //Локатор для поля ввода Email
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement inputEmail;

    //Локатор для поля ввода Пароль
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement inputPassword;

    //Локатор для кнопки Войти
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement buttonEnter;

    //Локатор для ссылки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement linkRegistration;


    @Step("Авторизация пользователя")
    public void loginUser(User user) {
        checkHeader();
        inputEmail.setValue(user.getEmail());
        inputPassword.setValue(user.getPassword());
        buttonEnter.click();
    }

    @Step("Нажатие ссылки \"Зарегистрироваться\"")
    public void clickRegistrationLink() {
        linkRegistration.click();
    }

    @Step("Проверка, что отображается заголовок Вход на странице авторизации")
    public void checkHeader() {
        headerEnter.shouldBe(visible);
    }
}