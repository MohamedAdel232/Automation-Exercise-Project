package pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P09_CartPage {
    // Page locators
    public final By productOneQuantityLocator = By.xpath("// button [@class='disabled'][1]");
    public final By proceedToCheckoutButtonLocator = By.xpath("// a [contains(@class,'check_out')]");
    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P09_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode for verify number of products
    public boolean verifyNumberOfProducts(String numberOfProducts) {
        int temp = Integer.parseInt(numberOfProducts);
        boolean check = false;
        for (int index = 1; index <= temp; index++) {
            By addToCartButtonLocator = By.xpath("//a [@href = '/product_details/" + index + "']");
            check = Utility.findWebElement(driver, addToCartButtonLocator).isDisplayed();
        }
        return check;
    }

    // Methode to very price, quantity and total price
    public boolean verifyPriceInfo(String numberOfProducts) {
        int temp = Integer.parseInt(numberOfProducts);
        boolean check = false;
        for (int index = 1; index <= temp; index++) {
            By priceLocator = By.xpath("(//td [@class='cart_price'] //p) [" + index + "]");
            By quantityLocator = By.xpath("(// button [@class='disabled'])[" + index + "]");
            By totalPriceLocator = By.xpath("(// p[@class='cart_total_price'])[" + index + "]");

            String priceFullText = Utility.findWebElement(driver, priceLocator).getText();
            String quantityFullText = Utility.findWebElement(driver, quantityLocator).getText();
            String totalPriceFullText = Utility.findWebElement(driver, totalPriceLocator).getText();

            float price = Float.parseFloat(priceFullText.replace("Rs. ", ""));
            LogsUtils.info("Price: " + price);
            float quantity = Float.parseFloat(quantityFullText);
            LogsUtils.info("Quantity: " + quantity);
            float realTotalPrice = Float.parseFloat(totalPriceFullText.replace("Rs. ", ""));
            LogsUtils.info("Total price: " + realTotalPrice);
            float totalPrice = price * quantity;
            LogsUtils.info("Calculated total price: " + totalPrice);

            check = realTotalPrice == totalPrice;
        }
        return check;
    }

    // Methode to verify quantity number
    public boolean verifyQuantityNumber(String realQuantity) {
        String quantityFullText = Utility.findWebElement(driver, productOneQuantityLocator).getText();
        String quantity = quantityFullText.replace("Rs. ", "");
        LogsUtils.info("Real quantity: " + realQuantity);
        LogsUtils.info("Quantity: " + quantity);
        return realQuantity.equals(quantity);
    }

    // Methode to proceed to checkout
    public P10_AddressDetailsPage proceedToCheckout() {
        Utility.clickOnElement(driver, proceedToCheckoutButtonLocator);
        return new P10_AddressDetailsPage(driver);
    }
}

