package pages;

import config.AddCustomerConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static utils.Waiters.waitForAlert;

/**
 * Класс в котором происходит взаимодействие табом AddCustomer
 */
public class AddCustomerPage extends BasePage {

    /**
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());


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
    public AddCustomerPage(WebDriver driver) {
        super(driver);
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
    public AddCustomerPage inputPostCode(String postCodeValue) {
        postCode.sendKeys(postCodeValue);
        return this;
    }

    /**
     * Метод сверки текста postCodeValue, асерт
     */
    public AddCustomerPage verifyPostCodeValue(String verifyPostCodeValue) {
        Assert.assertEquals(postCode.getAttribute("value"), verifyPostCodeValue, "Post Code введен некорректно.");
        return this;
    }

    /**
     * Метод ввода в поле First Name
     *
     * @return текущая страница
     */
    @Step("Ввод в поле First Name")
    public AddCustomerPage inputFirstName(String firstNameValue) {
        firstName.sendKeys(firstNameValue);
        return this;
    }

    /**
     * Метод сверки текста First Name, асерт
     */
    public AddCustomerPage verifyFirstNameValue(String verifyFirstNameValue) {
        Assert.assertEquals(firstName.getAttribute("value"), verifyFirstNameValue, "Имя введено некорректно.");
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
        return this;
    }

    /**
     * Метод сверки текста postCodeValue, асерт
     */
    public AddCustomerPage verifyInputLastName(String verifyInputLastName) {
        Assert.assertEquals(lastName.getAttribute("value"), verifyInputLastName, "Фамилия введена некорректно.");
        return this;
    }

    /**
     * Метод клика по кнопке отправки
     *
     * @return текущая страница
     */
    @Step("Клик по кнопке отправки")
    public AddCustomerPage clickToButtonAddCustomer() {
        buttonAddCustomer.click();
        return this;
    }

    /**
     * Метод проверки текста всплывающего алерта
     *
     * @return текущая страница
     */
    @Step("Проверка текста всплывающего алерта")
    public AddCustomerPage waitAlert() {
        waitForAlert(driver);
        String alertText = getAlertText();
        verifyAlertText(alertText);
        return this;
    }

    /**
     * Метод извлечения текста алерта
     *
     * @return извлеченный текст
     */
    private String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Метод сверки текста, асерт
     */
    private void verifyAlertText(String alertText) {
        Assert.assertTrue(alertText.contains("Customer added successfully with customer id"),
                "Текст предупреждения не содержит ожидаемого сообщения.");
    }

    /**
     * Метод закрытия алерта
     */
    @Step("Закрытие окна алерта")
    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (IllegalStateException e) {
            throw new RuntimeException("Нет активного алерта.", e);
        }
    }
}