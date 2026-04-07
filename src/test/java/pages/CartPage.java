package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalAmount() {
        return driver.findElement(By.xpath("//span[contains(text(),'Total Amount')]")).getText();
    }

    public void increaseQty() {
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.elementToBeClickable(
    By.xpath("//button[contains(text(),'+')]")
)).click();
    }

    public void removeItem() {
        driver.findElement(By.xpath("//div[text()='Remove']")).click();
        driver.findElement(By.xpath("//div[text()='Remove']")).click();
    }

    public boolean isCartEmpty() {
        return driver.getPageSource().contains("Missing Cart items");
    }
}
