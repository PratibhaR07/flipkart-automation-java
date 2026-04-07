package pages;

import org.openqa.selenium.*;

import java.util.Set;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchWindow() {
        String parent = driver.getWindowHandle();

        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
            }
        }
    }

    public void addToCart() {
        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
    }
}
