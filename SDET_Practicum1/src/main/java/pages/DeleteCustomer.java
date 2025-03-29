package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DeleteCustomer {

    private WebDriver driver;

    public DeleteCustomer (WebDriver driver) {
        this.driver = driver;
    }

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
