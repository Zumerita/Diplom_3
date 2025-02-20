package pages;

import api.URL;
import api.UserHttp;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RegistrationTest extends BaseTest {
    private User user;
    private final UserHttp userHttp = new UserHttp();

    @Before
    public void setUp() throws Exception {
        user = new User("Iva324n", "fsasdfffff@mail.ru", "123456");
    }

    @After
    public void tearDown() throws Exception {
        getWebDriver().quit();
        if (user.getPassword().length() > 5) {
            userHttp.loginUser(user);
            userHttp.deleteUser(user);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationUser() {
        //Открываем главную страницу

        MainPage mainPage = open(URL.BASE_URL, MainPage.class);
        mainPage.clickEnterInAccountButton(); // нажимаем кнопки "Войти в аккаунт"

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationLink();  // нажимаем ссылку "Зарегистрироваться"

        RegistrationPage regPage = page(RegistrationPage.class);
        regPage.registrationUser(user); // заполняем поля
        regPage.clickRegistrationButton(); //и нажимаем "Зарегистрироваться"

        loginPage.loginUser(user);

        mainPage.checkTextOnCreateOrderButton(); //проверяем, что на главной странице отображается кнопки с текстом "Оформить заказ"
    }

    @Test
    @DisplayName("Невалидный пароль для регистрации пользователя. Минимальный пароль — шесть символов")
    public void registrationUserWithLessThanSixCharactersErrorText() {

        user.setPassword("12345"); //устанавливаем пользователю невалидный пароль

        MainPage mainPage = open(URL.BASE_URL, MainPage.class);  //Открываем главную страницу
        mainPage.clickEnterInAccountButton(); // нажимаем кнопки "Войти в аккаунт"

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationLink();  // нажимаем ссылку "Зарегистрироваться"

        RegistrationPage regPage = page(RegistrationPage.class);
        regPage.registrationUser(user); // заполняем поля и нажимаем "Зарегистрироваться"
        regPage.checkErrorTextWithNotValidPassword(); //проверяем, что под паролем отображается сообщение "Некорректный пароль"
    }
}
