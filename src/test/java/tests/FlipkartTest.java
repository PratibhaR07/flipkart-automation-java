package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class FlipkartTest extends BaseTest {

    @Test
    public void testFlipkartFlow() {

        HomePage home = new HomePage(driver);
        home.closeLoginPopup();
        home.searchProduct("mobile");

        SearchPage search = new SearchPage(driver);

        Assert.assertTrue(search.getResultText().contains("mobile"));

        String price = search.get10thProductPrice();

        search.selectCompareItems();

        ProductPage product = new ProductPage(driver);
        product.switchWindow();
        product.addToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Total Amount')]")
        ));

        CartPage cart = new CartPage(driver);

        Assert.assertTrue(cart.getTotalAmount().contains(price));

        cart.increaseQty();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'QUANTITY')]")
        ));

        cart.removeItem();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Missing Cart items')]")
        ));

        Assert.assertTrue(cart.isCartEmpty());
    }
}
