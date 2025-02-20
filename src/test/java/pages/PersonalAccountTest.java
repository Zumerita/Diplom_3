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

public class PersonalAccountTest extends BaseTest {
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
    @DisplayName("Нажатии кнопки Личный кабинет не залогиненным пользователем")
    public void clickPersonalAccountButtonUnAuthorizationUser() {
        MainPage mainPage = open(URL.BASE_URL, MainPage.class); //переходим на главную страницу
        mainPage.clickPersonalAccountButton(); // нажимаем кнопку Личный кабинет

        LoginPage loginPage = page(LoginPage.class);
        loginPage.checkHeader(); //проверяем, что открылась страница авторизации
    }

    @Test
    @DisplayName("Нажатии кнопки Личный кабинет залогиненным пользователем")
    public void clickPersonalAccountButtonAuthorizationUser() {
        LoginPage loginPage = open(URL.LOGIN_URL, LoginPage.class); //Открываем страницу авторизации
        loginPage.loginUser(user); //авторизуемся под созданным пользователем

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton(); //Нажимаем кнопку Личный кабинет

        PersonalAccountPage lk = page(PersonalAccountPage.class);
        lk.checkInformationText(); // Проверяем, что на странице отображается текст с информацией
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета по кнопке Конструктор в шапке сайте")
    public void clickConstructorButtonFromPersonalAccountPage() {
        LoginPage loginPage = open(URL.LOGIN_URL, LoginPage.class); //Открываем страницу авторизации
        loginPage.loginUser(user); //авторизуемся под созданным пользователем

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton(); //Нажимаем кнопку Личный кабинет

        PersonalAccountPage lk = page(PersonalAccountPage.class);
        lk.clickConstructorButton(); // Нажимаем кнопку Конструктор

        mainPage.checkTextOnCreateOrderButton(); // Проверяем, что открылась главная страница и текст на кнопке Оформить заказ
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета по клику на Логотип в шапке сайте")
    public void clickLogoButtonFromPersonalAccountPage() {
        LoginPage loginPage = open(URL.LOGIN_URL, LoginPage.class); //Открываем страницу авторизации
        loginPage.loginUser(user); //авторизуемся под созданным пользователем

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton(); //Нажимаем кнопку Личный кабинет

        PersonalAccountPage lk = page(PersonalAccountPage.class);
        lk.clickLogoButton(); // Нажимаем на логотип в шапке сайте

        mainPage.checkTextOnCreateOrderButton(); // Проверяем, что открылась главная страница и текст на кнопке Оформить заказ
    }

    @Test
    @DisplayName("Выход из профиля через кнопку Выход в личном кабинете")
    public void clickExitButtonFromPersonalAccountPage() {
        LoginPage loginPage = open(URL.LOGIN_URL, LoginPage.class); //Открываем страницу авторизации
        loginPage.loginUser(user); //авторизуемся под созданным пользователем

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton(); //Нажимаем кнопку Личный кабинет

        PersonalAccountPage lk = page(PersonalAccountPage.class);
        lk.clickExitButton(); // Нажимаем на кнопку Выход

        loginPage.checkHeader(); // Проверяем, что открылась страница авторизации пользователя
    }


}