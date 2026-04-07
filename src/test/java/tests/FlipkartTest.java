package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class FlipkartTest extends BaseTest {

    @Test
    public void completeFlipkartFlow() {

        // Home Page
        HomePage home = new HomePage(driver);
        home.closeLoginPopup();
        home.searchProduct("mobile");

        // Search Page
        SearchPage search = new SearchPage(driver);

        //  Validate search result message
        String resultText = search.getResultText();
        Assert.assertTrue(resultText.contains("results for \"mobile\""),
                "Search result text not matching");

        // Capture product details (10th item)
        int tenthProduct = 10;
        int eleventhProduct = 11;

        String productName = search.getProductTitles().get(tenthProduct - 1).getText();
        String price = search.getPrices().get(tenthProduct - 1).getText();

        //  Compare 10th & 11th product (dynamic indexing)
        search.selectNthCompare(tenthProduct);
        search.selectNthCompare(eleventhProduct);

        Assert.assertTrue(driver.findElement(
                By.xpath("//span[contains(text(),'Compare')]")).isDisplayed(),
                "Compare tray not visible");

        // Click 10th product
        search.getProductTitles().get(tenthProduct - 1).click();

        // Product Page
        ProductPage product = new ProductPage(driver);
        product.switchToChildWindow();

        //  Validate product page opened
        Assert.assertTrue(driver.getTitle().contains(productName),
                "Product page not opened correctly");

        // Add to cart
        product.clickAddToCart();

        //  Validate button changed to Go to Cart
        Assert.assertTrue(product.isGoToCartVisible(),
                "Add to cart button did not change");

        // Cart Page
        CartPage cart = new CartPage(driver);

        //  Validate product added to cart
        Assert.assertTrue(cart.isProductPresent(productName),
                "Product not added to cart");

        //  Validate total amount matches
        String totalAmount = cart.getTotalAmount();
        Assert.assertTrue(totalAmount.replaceAll("[^0-9]", "")
                .contains(price.replaceAll("[^0-9]", "")),
                "Price mismatch between list and cart");

        // Increase quantity
        cart.increaseQty();

        //  Validate quantity update message
        Assert.assertTrue(driver.getPageSource().contains("QUANTITY to '2'"),
                "Quantity update message not displayed");

        //  Validate remove popup
        Assert.assertTrue(driver.getPageSource().contains("Remove"),
                "Remove button not visible");
        Assert.assertTrue(driver.getPageSource().contains("Cancel"),
                "Cancel button not visible");

        // Remove item
        cart.removeItem();

        //  Validate remove success message
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("removed from your cart"),
                "Remove confirmation message missing");

        //  Validate empty cart message
        Assert.assertTrue(cart.isCartEmpty(),
                "Empty cart message not displayed");

        Assert.assertTrue(driver.getPageSource()
                .contains("Login to see the items you added previously"),
                "Login message missing on empty cart");
    }
}
