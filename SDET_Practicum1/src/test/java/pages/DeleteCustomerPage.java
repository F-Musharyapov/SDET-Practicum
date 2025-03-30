package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
     */
    @Step("Удаление строк с именами, со средней длиной")
    public void deleteCustomerWithNameClosestToAverageLength() {
        List<String> names = getCustomerNames();
        double averageLength = calculateAverageLength(names);
        List<String> closestNames = findClosestNames(names, averageLength);

        if (!closestNames.isEmpty()) {
            deleteCustomersByNames(closestNames);
            assertCustomersDeleted(closestNames);
        }
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
     * Метод вычисления средней длины имен
     *
     * @param names список имен
     * @return средняя длина имен
     */
    private double calculateAverageLength(List<String> names) {
        if (names.isEmpty()) {
            return 0;
        }
        double totalLength = 0;
        for (String name : names) {
            totalLength += name.length();
        }
        return totalLength / names.size();
    }

    /**
     * Метод поиска имен, ближайших к средней длине
     *
     * @param names         список имен
     * @param averageLength средняя длина имен
     * @return список имен, ближайших к средней длине
     */
    private List<String> findClosestNames(List<String> names, double averageLength) {
        List<String> closestNames = new ArrayList<>();
        double closestDifference = Double.MAX_VALUE;

        for (String name : names) {
            double difference = Math.abs(name.length() - averageLength);
            if (difference < closestDifference) {
                closestDifference = difference;
                closestNames.clear();
                closestNames.add(name);
            } else if (difference == closestDifference) {
                closestNames.add(name);
            }
        }
        return closestNames;
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
    private void assertCustomersDeleted(List<String> names) {
        List<String> remainingNames = getCustomerNames();

        for (String name : names) {
            Assert.assertFalse(remainingNames.contains(name), "Поле с именем " + name + " не был удален.");
        }
    }
}
