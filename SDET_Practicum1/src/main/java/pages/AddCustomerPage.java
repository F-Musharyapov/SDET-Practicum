package pages;

import config.AddCustomerConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

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

    @FindBy(css = "button[class='btn btn-default']")
    private WebElement successLocator;



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
    @Step("")
    public AddCustomerPage clickToTabAddCustomer() {
        tabAddCustomer.click();
        return this;
    }

    // Генерация 10-значного номера для поля Post Code
    String getPostCode = generatePostCode();

    // Генерация имени на основе Post Code
    //String getFirstName = generateFirstName(getPostCode);

    //метод генерации 10 цифр
    private static String generatePostCode() {
        Random random = new Random();
        String postCode = "";

        for (int i = 0; i < 10; i++) {
            postCode += random.nextInt(10); // Генерация цифр от 0 до 9
        }

        return postCode;
    }

    public AddCustomerPage inputPostCode() {
        postCode.sendKeys(generatePostCode());
        //makeScreenShot(driver);
        return this;
    }

    // Метод для генерации имени на основе Post Code
    private static String generateFirstName(String postCode) {
        String firstName = "";

        for (int i = 0; i < postCode.length(); i += 2) {
            // Берем две цифры
            String pair = postCode.substring(i, Math.min(i + 2, postCode.length()));
            int number = Integer.parseInt(pair);

            // Преобразуем в букву
            char letter = (char) ('a' + (number % 26)); // 0-25 -> a-z
            firstName += letter; // Добавляем букву к имени
        }

        return firstName;
    }

    public AddCustomerPage inputFirstName() {
        firstName.sendKeys(generateFirstName(getPostCode));
        //makeScreenShot(driver);
        return this;
    }

    public AddCustomerPage inputLastName(String input) {
        lastName.sendKeys(input);
        //makeScreenShot(driver);
        return this;
    }


    public AddCustomerPage clickToButtonAddCustomer() {
        buttonAddCustomer.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert(); //переключаемся на окно
        String alertText = alert.getText(); //извлечение сообщения

        Assert.assertTrue("Текст предупреждения не содержит ожидаемого сообщения.",
                alertText.contains("Customer added successfully with customer id"));
        alert.accept(); //подвердили окно
        return this;
    }
}