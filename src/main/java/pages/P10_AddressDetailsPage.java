package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P10_AddressDetailsPage {
    // Page locators
    private final By addressDetailsLocator = By.xpath("//div [@class='checkout-information']");
    private final By orderDetailsLocator = By.tagName("tbody");
    private final By commentLocator = By.className("form-control");
    private final By placeOrderButtonLocator = By.xpath("//a [contains(@class, 'check_out')]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P10_AddressDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to check address & order details
    public boolean reviewAddressAndOrderDetails() {
        return (((Utility.findWebElement(driver, addressDetailsLocator)).isDisplayed())
                && ((Utility.findWebElement(driver, orderDetailsLocator)).isDisplayed()));
    }

    // Methode to enter a comment
    public P11_PaymentPage enterCommentAndPlaceOrder(String comment) {
        Utility.sendData(driver, commentLocator, comment);
        Utility.clickOnElement(driver, placeOrderButtonLocator);
        return new P11_PaymentPage(driver);
    }

}
