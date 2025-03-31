package tests;

import config.AddCustomerConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;
import pages.AddCustomerPage;

import static utils.GeneratorHelper.generateFirstName;
import static utils.GeneratorHelper.generatePostCode;

/**
 * Класс тестирования отправки данных на вкладке AddCustomer
 */
@Epic("AddCustomer test")
public class AddCustomerPageTest extends BaseTest {

    /**
     * Экземпляр конфигурации
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    /**
     * Переменные со сгенерированнымы данными полей PostCode и FirstName
     */
    String postCodeValue = generatePostCode();
    String firstNameValue = generateFirstName(postCodeValue);


    @Test(description = "Проверки отправки данных с заполненными полями формы")
    @Feature("Ввод данных в форму и отправка")
    public void testForm() {
        new AddCustomerPage(driver)
                .clickToTabAddCustomer()
                .inputPostCode(postCodeValue)
                .inputFirstName(firstNameValue)
                .inputLastName(config.getLastName())
                .clickToButtonAddCustomer()
                .waitAlert()
                .acceptAlert();
    }
}