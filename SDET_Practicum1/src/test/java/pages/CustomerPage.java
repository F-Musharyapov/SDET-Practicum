package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс в котором происходит взаимодействие с окном Customer
 */
public class CustomerPage extends BasePage {

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
     * Элемент столбца FirstName
     */
    @FindBy(css = "a[ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement firstNameHeader;

    /**
     * Конструктор создания CustomerPage
     *
     * @param driver драйвер для управления браузером
     */
    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод клика на таб Customer
     *
     * @return текущая страница
     */
    @Step("Выбор таба Customer")
    public CustomerPage clickToTabCustomer() {
        tabCustomer.click();
        return this;
    }

    /**
     * Метод клика по столбцу First Name
     *
     * @return текущая страница
     */
    @Step("Клик по столбцу First Name")
    public CustomerPage clickToFirstNameHeader() {
        firstNameHeader.click();
        return this;
    }

    /**
     * Основной метод проверки сортировки по имени, по возрастанию и убыванию
     *
     * @return возвращает результат true или false
     */
    @Step("Проверка сортировки имён в таблице")
    public boolean checkSortByFirstName() {
        boolean isSortedAscending = checkSortingOrder(false);
        firstNameHeader.click();
        boolean isSortedDescending = checkSortingOrder(true);
        return isSortedAscending && isSortedDescending;
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
     * Метод для извлечения имен из строк таблицы
     *
     * @return возвращает инициализированные строки
     */
    private List<String> getFirstNamesFromRows() {
        List<WebElement> rows = getRows();
        List<String> names = new ArrayList<>();

        for (WebElement row : rows) {
            String name = getFirstNameFromRow(row);
            names.add(name);
        }
        return names;
    }

    /**
     * Метод для получения имени из конкретной строки
     *
     * @param row строка извлечения имени
     * @return возвращает имя из строки
     */
    private String getFirstNameFromRow(WebElement row) {
        return row.findElement(By.cssSelector("td:nth-child(1)")).getText();
    }

    /**
     * Метод сравнения двух списков исходя из направления
     *
     * @param ascending значение для варианта выбора сортировки
     * @return возвращает результат сравнения
     */
    private boolean checkSortingOrder(boolean ascending) {
        List<String> names = getFirstNamesFromRows();
        List<String> sortedNames = sortNames(names, ascending);
        boolean isSorted = areListsEqual(names, sortedNames);
        Assert.assertTrue(isSorted, "Сортировка по именам " + (ascending ? "возрастающая" : "убывающая") + " выполнена некорректно.");

        return isSorted;
    }

    /**
     * Метод сортировки имен
     *
     * @param names     список имен для сортировки
     * @param ascending значение для варианта выбора сортировки
     * @return возвращает отсортированный список
     */
    private List<String> sortNames(List<String> names, boolean ascending) {
        List<String> sortedNames = new ArrayList<>(names);
        if (ascending) {
            Collections.sort(sortedNames);
        } else {
            Collections.sort(sortedNames, Collections.reverseOrder());
        }
        return sortedNames;
    }

    /**
     * Метод проверки равенства двух списков
     *
     * @param original оригинальный список имен
     * @param sorted   отсортированный список имен
     * @return возвращает результат проверки
     */
    private boolean areListsEqual(List<String> original, List<String> sorted) {
        return original.equals(sorted);
    }
}