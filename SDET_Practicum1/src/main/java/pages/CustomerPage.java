package pages;

import config.AddCustomerConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerPage {
    /**
     * Экземпляр драйвера для управления браузером
     */
    private final WebDriver driver;

    /**
     * Экземпляр конфигурации с параметрами для тестов формы на странице
     */
    private final AddCustomerConfig config = ConfigFactory.create(AddCustomerConfig.class, System.getenv());

    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement tabCustomer;

    @FindBy(css = "table[class=\"table table-bordered table-striped\"]")
    private WebElement tableElements;
    @FindBy(css = "a[ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement firstNameHeader;

    @FindBy(css = "span[ng-show=\"sortType == 'fName' && !sortReverse\"].fa-caret-down:not(.ng-hide)")
    private WebElement firstNameTabeSortDown;
    @FindBy(css = "span[ng-show=\"sortType == 'fName' && sortReverse\"].fa-caret-up:not(.ng-hide)")
    private WebElement firstNameTabeSortUp;

    /**
     * Конструктор создания CustomerPage
     *
     * @param driver драйвер для управления браузером
     */
    public CustomerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод клика на таб tabAddCustomer
     *
     * @return текущая страница
     */
    @Step("")
    public CustomerPage clickToTabCustomer() {
        tabCustomer.click();
        return this;
    }
    @Step("")
    public CustomerPage clickToFirstNameHeader() {
        firstNameHeader.click();
        return this;
    }

    public boolean checkSortByFirstName() {

        List<WebElement> rows = driver.findElements(By.cssSelector(".table.table-bordered.table-striped tbody tr"));

        List<String> names = new ArrayList<>();
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            names.add(name);
        }

        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);

        boolean isSortedAscending = names.equals(sortedNames);

        firstNameHeader.click();

        rows = driver.findElements(By.cssSelector(".table.table-bordered.table-striped tbody tr"));
        names.clear();
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            names.add(name);
        }

        Collections.reverse(sortedNames);

        boolean isSortedDescending = names.equals(sortedNames);

        return isSortedAscending && isSortedDescending;
    }

}

