package pages;

import config.AddCustomerConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
    /**
     * Экземпляр драйвера для управления браузером
     */
    private final WebDriver driver;

    /**
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = ".btnClass2")
    private WebElement tabAddCustomer;

    /**
     * Конструктор создания FormPage
     *
     * @param driver драйвер для управления браузером
     */
    public AddCustomerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод клика на таб tabAddCustomer
     *
     * @return текущая страница
     */
    @Step("Ввод данных в поле ввода First Name")
    public AddCustomerPage clickToTabAddCustomer() {
        tabAddCustomer.click();
        return this;
    }
}