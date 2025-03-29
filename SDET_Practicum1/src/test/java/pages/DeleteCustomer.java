package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс в котором происходит взаимодействие со странницей с формой
 */
public class DeleteCustomer {
    /**
     * Экземпляр драйвера для управления браузером
     */
    private WebDriver driver;

    /**
     * Конструктор создания DeleteCustomer
     *
     * @param driver драйвер для управления браузером
     */
    public DeleteCustomer(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод удаления по имени
     *
     * @return текущая страница
     */
    @Step("Удаление по имени")
    public void deleteCustomerWithNameClosestToAverageLength() {

        List<WebElement> rows = driver.findElements(By.cssSelector(".table.table-bordered.table-striped tbody tr"));

        List<String> names = new ArrayList<>();
        for (WebElement row : rows) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
            names.add(name);
        }

        double totalLength = 0;
        for (String name : names) {
            totalLength += name.length();
        }
        double averageLength = totalLength / names.size();

        String closestName = null;
        double closestDifference = Double.MAX_VALUE;

        for (String name : names) {
            double difference = Math.abs(name.length() - averageLength);
            if (difference < closestDifference) {
                closestDifference = difference;
                closestName = name;
            }
        }

        if (closestName != null) {
            for (WebElement row : rows) {
                String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
                if (name.equals(closestName)) {
                    WebElement deleteButton = row.findElement(By.cssSelector("td button[ng-click=\"deleteCust(cust)\"]"));
                    deleteButton.click();
                    break;
                }
            }
        }
    }
}
