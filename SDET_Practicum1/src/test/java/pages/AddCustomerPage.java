package pages;

import config.AddCustomerConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.testng.AssertJUnit.assertTrue;
import static utils.GeneratorHelper.generateFirstName;
import static utils.GeneratorHelper.generatePostCode;
import static utils.Waiters.*;

/**
 * Класс в котором происходит взаимодействие табом AddCustomer
 */
public class AddCustomerPage {
    /**
     * Экземпляр драйвера для управления браузером
     */
    private final WebDriver driver;

    /**
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    String getPostCode = generatePostCode();

    /**
     * Элемент таба AddCustomer
     */
    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement tabAddCustomer;

    /**
     * Элемент с полем ввода First Name
     */
    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstName;

    /**
     * Элемент с полем ввода Last Name
     */
    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastName;

    /**
     * Элемент с полем ввода Post Name
     */
    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement postCode;

    /**
     * Элемент кнопка отправки данных
     */
    @FindBy(css = "button[class='btn btn-default']")
    private WebElement buttonAddCustomer;


    /**
     * Конструктор создания AddCustomerPage
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
    @Step("Выбор таба AddCustomer")
    public AddCustomerPage clickToTabAddCustomer() {
        tabAddCustomer.click();
        return this;
    }

    /**
     * Метод ввода в поле Post Code
     *
     * @return текущая страница
     */
    @Step("Ввод в поле Post Code")
    public AddCustomerPage inputPostCode() {
        postCode.sendKeys(generatePostCode());
        //makeScreenShot(driver);
        return this;
    }

    /**
     * Метод ввода в поле First Name
     *
     * @return текущая страница
     */
    @Step("Ввод в поле First Name")
    public AddCustomerPage inputFirstName() {
        firstName.sendKeys(generateFirstName(getPostCode));
        //makeScreenShot(driver);
        return this;
    }

    /**
     * Метод ввода в поле Last Name
     *
     * @return текущая страница
     */
    @Step("Ввод в поле Last Name")
    public AddCustomerPage inputLastName(String input) {
        lastName.sendKeys(input);
        //makeScreenShot(driver);
        return this;
    }

    /**
     * Метод клика по кнопке отправки и проверка
     *
     * @return текущая страница
     */
    @Step("Клик по кнопке отправки и проверка")
    public AddCustomerPage clickToButtonAddCustomer() {
        buttonAddCustomer.click();
        waitForAlert(driver);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertTrue("Текст предупреждения не содержит ожидаемого сообщения.",
                alertText.contains("Customer added successfully with customer id"));
        alert.accept();
        return this;
    }
}