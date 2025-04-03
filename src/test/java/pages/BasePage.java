package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс с основными повторяющимися методами
 */
public class BasePage {
    /**
     * Экземпляр драйвера для управления браузером
     */
    protected final WebDriver driver;

    /**
     * Конструктор создания страницы BasePage
     *
     * @param driver драйвер для управления браузером
     */
    public BasePage(final WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Значение WebDriver не может быть равно null");
        }
        try {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать элементы страницы. Проверить, что WebDriver корректно инициализирован.", e);
        }
    }
}
