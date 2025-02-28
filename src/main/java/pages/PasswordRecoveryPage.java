package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;



public class PasswordRecoveryPage extends BasePage {


    //Локатор для ссылки Войти
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement linkEnter;

    @Step("Нажатие ссылки Войти на странице восстановления пароля")
    public void clickEnterLink() {
        linkEnter.shouldBe(visible);
        linkEnter.click();
    }


}