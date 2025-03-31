package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.CustomerPage;

/**
 * Класс тестирования сортировки по полю First Name
 */
@Epic("First Name sorting Customer test")
public class CustomerPageTest extends BaseTest {

    @Test(description = "Проверка сортировки по полю First Name")
    @Feature("Проверка сортировки, сравнение ожидаемого результата с фактическим")
    public void sortingTest() {
        new CustomerPage(driver)
                .clickToTabCustomer()
                .clickToFirstNameHeader()
                .checkSortByFirstName();
    }
}