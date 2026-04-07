package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By closePopup = By.xpath("//button[contains(text(),'✕')]");
    By searchBox = By.name("q");

    public void closeLoginPopup() {
        driver.findElement(closePopup).click();
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBox).submit();
    }
}
