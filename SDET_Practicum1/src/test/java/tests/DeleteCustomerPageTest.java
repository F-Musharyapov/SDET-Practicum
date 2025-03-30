package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.DeleteCustomerPage;

/**
 * Класс удаления имени по среднеарифметическому количеству букв
 */
@Epic("DeleteCustomer Test")
public class DeleteCustomerPageTest extends BaseTest {

    @Test(description = "Удаление имени по среднеарифметическому количеству букв")
    @Feature("Вычисление среднеарифметического количеству букв имени и удаление")
    public void deleteTest() {
        new DeleteCustomerPage(driver)
                .clickToTabCustomer()
                .deleteCustomerWithNameClosestToAverageLength();
    }
}
