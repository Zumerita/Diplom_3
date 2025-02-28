package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class PersonalAccountPage extends BasePage {

    //Локатор для текста с информацией, что на этой странице можно изменить свои данные
    @FindBy(how = How.XPATH, using = ".//p[@class='Account_text__fZAIn text text_type_main-default']")
    private SelenideElement textWithInformation;

    //Локатор для кнопки Выход
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement buttonExit;

    @Step("Проверка, что отображается текст с информацией в личном кабинете")
    public void checkInformationText() {
        textWithInformation.shouldHave(exactText("В этом разделе вы можете изменить свои персональные данные"));
    }

    @Step("Нажатие на кнопку Выход в личном кабинете")
    public void clickExitButton() {
        buttonExit.shouldBe(visible);
        buttonExit.click();
    }
}