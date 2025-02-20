package pages;

import org.junit.Before;

public class BaseTest {

    @Before
    public void browserSetup() {
        /*
        Теперь запустить тесты в яндекс браузере можно командой mvn clean test -Dbrowser=yandex
        Без команды -Dbrowser=yandex тесты будут прогоняться в хроме
         */

        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/yandexdriver.exe");
            // При необходимости скорректировать путь до Яндекс браузера
            System.setProperty("webdriver.chrome.binary", "C:/Users/User/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        } else {
            System.setProperty("webdriver.chrome.binary", "C:/Program Files/Google/Chrome/Application/chrome.exe");
        }


    }
}