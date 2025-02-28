package pages;

import api.URL;
import api.UserHttp;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pojo.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Random;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class RegistrationTest extends BaseTest {
    private static final Random RANDOM = new Random();
    private User user;
    private final UserHttp userHttp = new UserHttp();
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        String randomUsername = "User_" + UUID.randomUUID().toString().substring(0, 8); // Unique username
        String randomEmail = "test_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com"; // Unique email
        String randomPassword = generateRandomPassword();
        user = new User(randomUsername, randomEmail, randomPassword);

        userHttp.createUser(user);
    }

    private String generateRandomPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int randomIndex = RANDOM.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }
        return password.toString();
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
        regPage.clickRegistrationButton();//и нажимаем "Зарегистрироваться"


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
