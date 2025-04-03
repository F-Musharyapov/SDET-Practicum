package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.GeneratorHelper.calculateAverageLength;
import static utils.GeneratorHelper.findClosestNames;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


/**
 * Класс в котором происходит взаимодействие со странницей с формой
 */
public class DeleteCustomerPage extends BasePage {

    /**
     * Элемент таба Customer
     */
    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement tabCustomer;

    /**
     * Элемент таблица
     */
    @FindBy(css = ".table.table-bordered.table-striped tbody tr")
    private List<WebElement> tableElements;

    /**
     * Конструктор создания страницы DeleteCustomerPage
     *
     * @param driver драйвер для управления браузером
     */
    public DeleteCustomerPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для получения всех строк таблицы в список
     *
     * @return возвращает инициализированные строки
     */
    private List<WebElement> getRows() {
        return tableElements;
    }

    /**
     * Метод клика на таб Customer
     *
     * @return текущая страница
     */
    @Step("Выбор таба Customer")
    public DeleteCustomerPage clickToTabCustomer() {
        tabCustomer.click();
        return this;
    }

    /**
     * Метод удаления строк с именами, со средней длиной
     *
     * @return
     */
    @Step("Удаление строк с именами, со средней длиной")
    public List<String> deleteCustomerWithNameClosestToAverageLength() {
        List<String> names = getCustomerNames();
        double averageLength = calculateAverageLength(names);
        List<String> closestNames = findClosestNames(names, averageLength);

        if (!closestNames.isEmpty()) {
            deleteCustomersByNames(closestNames);
        }
        return closestNames;
    }

    /**
     * Метод получения списка имен
     *
     * @return список имен
     */
    private List<String> getCustomerNames() {
        List<WebElement> rows = getRows();
        List<String> names = new ArrayList<>();

        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            names.add(name);
        }
        return names;
    }

    /**
     * Метод удаления по списку имен
     *
     * @param names список имен
     */
    private void deleteCustomersByNames(List<String> names) {
        List<WebElement> rows = getRows();

        for (WebElement row : rows) {
            String customerName = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            if (names.contains(customerName)) {
                WebElement deleteButton = row.findElement(By.cssSelector("td button[ng-click=\"deleteCust(cust)\"]"));
                deleteButton.click();
            }
        }
    }

    /**
     * Метод ассерт проверка, что имена удаленны
     *
     * @param names список имен
     */
    @Step("Проверка на наличие неудаленных имен")
    public void assertCustomersDeleted(List<String> names) {
        List<String> remainingNames = getCustomerNames();

        for (String name : names) {
            Assert.assertFalse(remainingNames.contains(name), "Поле с именем " + name + " не был удален.");
        }
    }
}
