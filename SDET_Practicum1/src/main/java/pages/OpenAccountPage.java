package pages;

import config.AddCustomerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OpenAccountPage {
    /**
     * Экземпляр драйвера для управления браузером
     */
    private final WebDriver driver;

    /**
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());


    /**
     * Конструктор создания FormPage
     *
     * @param driver драйвер для управления браузером
     */
    public OpenAccountPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}