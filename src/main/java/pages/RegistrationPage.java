package pages;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pojo.User;

public class RegistrationPage extends BasePage {


    //Локатор для вода Имени
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement inputName;

    //Локатор для поля ввода Email
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement inputEmail;

    //Локатор для поля ввода Пароль
    @FindBy(how = How.XPATH, using = ".//fieldset[3]//input")
    private SelenideElement inputPassword;

    //Локатор для кнопки Зарегистрироваться
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    //Локатор для ссылки Войти
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement linkEnter;

    //Локатор для сообщения, что пароль не корректный
    @FindBy(how = How.XPATH, using = ".//p[@class='input__error text_type_main-default']")
    private SelenideElement passwordErrorText;


    @Step("Регистрация пользователя")
    public void registrationUser(User user) {
        inputName.setValue(user.getName());
        inputEmail.setValue(user.getEmail());
        inputPassword.setValue(user.getPassword());
    }

    @Step("Нажатие ссылки Вход на странице регистрации пользователя")
    public void clickEnterLink() {
        linkEnter.click();
    }

    @Step("Проверка отображения ошибки при невалидном пароле")
    public void checkErrorTextWithNotValidPassword() {
        inputEmail.click();
        passwordErrorText.shouldHave(exactText("Некорректный пароль"));
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegistrationButton() {
        buttonRegistration.click();
    }

}