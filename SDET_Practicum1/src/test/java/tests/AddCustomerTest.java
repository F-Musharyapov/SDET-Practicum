package tests;

import config.AddCustomerConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomerPage;
import pages.DeleteCustomer;


/**
 * Основной класс с тестами FormPage
 */
@Epic("AddCustomer Test")
public class AddCustomerTest extends BaseTest {

    /**
     * Экземпляр конфигурации
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    @Test(description = "Проверки отправки данных с заполненными полями формы")
    @Feature("Ввод данных в форму и отправка")
    public void testFormTest() {
        new AddCustomerPage(driver)
                .clickToTabAddCustomer()
                .inputPostCode()
                .inputFirstName()
                .inputLastName(config.getLastName())
                .clickToButtonAddCustomer();

        new CustomerPage(driver)
                .clickToTabCustomer()
                .clickToFirstNameHeader()
                .checkSortByFirstName();

        new DeleteCustomer(driver)
                .deleteCustomerWithNameClosestToAverageLength();
    }
}