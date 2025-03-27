import config.AddCustomerConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.OpenAccountPage;


/**
 * Основной класс с тестами FormPage
 */
@Epic("AddCustomer Test")
public class AddCustomerTest extends BaseTest {

    /**
     * Экземпляр конфигурации с параметрами для Form тестов
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    @Test(description = "Проверки отправки данных с заполненными полями формы")
    @Feature("Ввод данных в форму и отправка")
    public void testFormTest() {
        new AddCustomerPage(driver)
                .clickToTabAddCustomer()
                .inputFirstName(config.getFirstName())
                .inputLastName(config.getLastName())
                .inputPostCode(config.getPostCode())
                .clickToButtonAddCustomer();

        //new OpenAccountPage(driver);
                //.inputFirstName(config.firstName())
                //.inputLastName(config.lastName())
                //.inputUserEmail(config.userEmail())
                //.clickToGender()
                //.inputUserNumber(config.userNumber())
                //.selectDateOfBirth()
                //.inputSubjectsInput(config.subjectsInput())
                //.clickToHobbiesWrapper()
                //.uploadPicture()
                //.inputCurrentAddress(config.currentAddress())
                //.clickToSelectState()
                //.clickToSelectCity()
        //.clickToSubmit();

/*
        new SuccessfulPage(driver)
                .checkHead()
                .checkTableElement();
                Кнопки



 */
    }
}