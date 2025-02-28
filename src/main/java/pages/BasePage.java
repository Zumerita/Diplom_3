package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage {

    // локатор поля кнопки Конструктор
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    protected SelenideElement buttonConstructor;

    // локатор для кнопки Личный кабинет
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    protected SelenideElement buttonPersonalAccount;

    // локатор для логотипа сайта
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    protected SelenideElement buttonLogo;


    @Step("Нажатие кнопки Конструктор в шапке сайта")
    public void clickConstructorButton() {
        buttonConstructor.click();
    }

    @Step("Нажатие кнопки Личный кабинет в шапке сайта")
    public void clickPersonalAccountButton() {
        buttonPersonalAccount.click();
    }

    @Step("Нажатие на логотип в шапке сайта")
    public void clickLogoButton() {
        buttonLogo.click();
    }
}
