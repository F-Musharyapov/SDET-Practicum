package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
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

        CustomerPage customerPage = new CustomerPage(driver);

        customerPage.clickToTabCustomer().clickToFirstNameHeader();
        boolean isSortedFalse = customerPage.checkSortingOrder(false);
        Assert.assertTrue(isSortedFalse, "Сортировка по именам возрастающая выполнена некорректно.");
        customerPage.clickToFirstNameHeader();
        boolean isSortedTrue = customerPage.checkSortingOrder(true);
        Assert.assertTrue(isSortedTrue, "Сортировка по именам возрастающая выполнена некорректно.");
        System.out.print("Сработал CustomerPageTest");
    }
}