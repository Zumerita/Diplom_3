package pages;

import api.URL;
import api.UserHttp;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginTest extends BaseTest {
    private User user;
    private final UserHttp userHttp = new UserHttp();


    @Before
    public void setUp() throws Exception {
        user = new User("Iva324n", "fsasdfffff@mail.ru", "123456");
        userHttp.createUser(user);
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
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    public void loginUsingTheEnterAccountButton() {

        MainPage mainPage = open(URL.BASE_URL, MainPage.class);//Открываем главную страницу
        mainPage.clickEnterInAccountButton(); // Нажимаем кнопку Войти в акканут

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user); //Вводим данные пользователя и нажимаем кнопку Войти

        mainPage.checkTextOnCreateOrderButton(); //Проверяем, что отображается кнопка с текстом Оформить заказ
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    public void loginUsingThePersonalAccountButton() {

        MainPage mainPage = open(URL.BASE_URL, MainPage.class);//Открываем главную страницу
        mainPage.clickPersonalAccountButton(); // Нажимаем кнопку Личный кабинет

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user); //Вводим данные пользователя и нажимаем кнопку Войти

        mainPage.checkTextOnCreateOrderButton(); //Проверяем, что отображается кнопка с текстом Оформить заказ
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginViaTheButtonInTheRegistrationPage() {

        RegistrationPage regPage = open(URL.REGISTER_URL, RegistrationPage.class);//Открываем страницу регистрации
        regPage.clickEnterLink(); // Нажимаем ссылку Войти

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user); //Вводим данные пользователя и нажимаем кнопку Войти

        MainPage mainPage = page(MainPage.class);
        mainPage.checkTextOnCreateOrderButton(); //Проверяем, что отображается кнопка с текстом Оформить заказ
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginViaTheButtonInTheRecoveryPasswordPage() {

        PasswordRecoveryPage recPage = open(URL.RECOVERY_URL, PasswordRecoveryPage.class);//Открываем страницу восстановления пароля
        recPage.clickEnterLink(); // Нажимаем ссылку Войти

        LoginPage loginPage = page(LoginPage.class);
        loginPage.loginUser(user); //Вводим данные пользователя и нажимаем кнопку Войти

        MainPage mainPage = page(MainPage.class);
        mainPage.checkTextOnCreateOrderButton(); //Проверяем, что отображается кнопка с текстом Оформить заказ
    }


}