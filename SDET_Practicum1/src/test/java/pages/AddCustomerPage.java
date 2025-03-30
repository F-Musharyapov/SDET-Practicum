package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import static utils.GeneratorHelper.generateFirstName;
import static utils.GeneratorHelper.generatePostCode;
import static utils.Waiters.*;

/**
 * Класс в котором происходит взаимодействие табом AddCustomer
 */
public class AddCustomerPage extends BasePage {

    /**
     * Переменная для хранения полученных данных из generatePostCode()
     */
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
    public AddCustomerPage inputPostCode() {
        postCode.sendKeys(generatePostCode());
        Assert.assertEquals(postCode.getAttribute("value"), postCode, "Post Code введен некорректно.");
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
        Assert.assertEquals(firstName.getAttribute("value"), firstName, "Имя введено некорректно.");
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
        Assert.assertEquals(lastName.getAttribute("value"), input, "Фамилия введена некорректно.");
        //makeScreenShot(driver);
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
        //makeScreenShot(driver);
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
        acceptAlert();
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
    private void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}