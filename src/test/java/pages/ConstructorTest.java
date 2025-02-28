package pages;

import api.URL;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ConstructorTest extends BaseTest {


    @After
    public void tearDown() throws Exception {
        getWebDriver().quit();
    }


    @Test
    @DisplayName("Проверка перехода к разделу Булки")
    public void selectSectionBuns() throws InterruptedException {
        MainPage mainPage = open(URL.BASE_URL, MainPage.class); //открываем главную страницу
        mainPage.clickSectionSauces(); // Сначала выбираем раздел Соусы, что бы булки были скрыты
        mainPage.clickSectionBuns(); // Выбираем раздел Булки и проверяем, что в таблице появляется заголовок Булки
        String expectedText = "Булки";
        Assert.assertEquals(expectedText, mainPage.getNameSelectedSection(expectedText));//Проверяем название выбранного раздела
    }

    @Test
    @DisplayName("Проверка перехода к разделу Соусы")
    public void selectSectionSauces() throws InterruptedException {
        MainPage mainPage = open(URL.BASE_URL, MainPage.class); //открываем главную страницу
        mainPage.clickSectionSauces(); // Выбираем раздел Соусы
        String expectedText = "Соусы";
        Assert.assertEquals(expectedText, mainPage.getNameSelectedSection(expectedText));  //Проверяем название выбранного раздела
    }

    @Test
    @DisplayName("Проверка перехода к разделу Начинки")
    public void selectSectionToppings() throws InterruptedException {
        MainPage mainPage = open(URL.BASE_URL, MainPage.class); //открываем главную страницу
        mainPage.clickSectionToppings(); // Выбираем раздел Начинки
        String expectedText = "Начинки";
        Assert.assertEquals(expectedText, mainPage.getNameSelectedSection(expectedText)); //Проверяем название выбранного раздела
    }
}

