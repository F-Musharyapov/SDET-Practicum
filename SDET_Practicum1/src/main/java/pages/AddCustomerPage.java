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
     * Элемент с полем ввода First Name (css = "div[class='main-select-JJyaZ main-location-XUs1_']")
     */
    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement tabAddCustomer;

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstName;

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastName;

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement postCode;

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = "button[class='btn btn-default']")
    private WebElement buttonAddCustomer;


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

    public AddCustomerPage inputFirstName(String input) {
        firstName.sendKeys(input);
        //makeScreenShot(driver);
        return this;
    }

    public AddCustomerPage inputLastName(String input) {
        lastName.sendKeys(input);
        //makeScreenShot(driver);
        return this;
    }

    public AddCustomerPage inputPostCode(String input) {
        postCode.sendKeys(input);
        //makeScreenShot(driver);
        return this;
    }

    public AddCustomerPage clickToButtonAddCustomer() {
        buttonAddCustomer.click();
        return this;
    }

}