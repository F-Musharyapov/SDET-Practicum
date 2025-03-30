package pages;

import config.AddCustomerConfig;
import org.aeonbits.owner.ConfigFactory;
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
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    /**
     * Конструктор создания страницы BasePage
     *
     * @param driver драйвер для управления браузером
     */
    public BasePage(final WebDriver driver) {
        try {
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }
        catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
