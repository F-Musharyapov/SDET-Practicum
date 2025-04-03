package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.DeleteCustomerPage;

import java.util.List;

/**
 * Класс удаления имени по среднеарифметическому количеству букв
 */
@Epic("DeleteCustomer Test")
public class DeleteCustomerPageTest extends BaseTest {

    @Test(description = "Удаление имени по среднеарифметическому количеству букв")
    @Feature("Вычисление среднеарифметического количеству букв имени и удаление")
    public void deleteTest() {
        DeleteCustomerPage deleteCustomerPage = new DeleteCustomerPage(driver);
        deleteCustomerPage.clickToTabCustomer();
        List<String> closestNames = deleteCustomerPage.deleteCustomerWithNameClosestToAverageLength();
        deleteCustomerPage.assertCustomersDeleted(closestNames);
    }
}
