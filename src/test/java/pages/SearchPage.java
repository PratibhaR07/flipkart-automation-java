package pages;

import org.openqa.selenium.*;
import java.util.List;

public class SearchPage {

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getResultText() {
        return driver.findElement(By.xpath("//span[contains(text(),'Showing')]")).getText();
    }

    public void selectCompareItems() {
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='_4rR01T']"));

        items.get(9).click(); // open product 10
    }

    public String get10thProductName() {
        return driver.findElements(By.xpath("//div[@class='_4rR01T']")).get(9).getText();
    }

    public String get10thProductPrice() {
        return driver.findElements(By.xpath("//div[@class='_30jeq3']")).get(9).getText();
    }
}
