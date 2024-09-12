package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11_PaymentPage {
    // Page locators
    private final By nameOnCardLocator = By.xpath("//input [@class='form-control']");
    private final By cardNumberLocator = By.xpath("//input [@name='card_number']");
    private final By cvcLocator = By.xpath("//input [@name='cvc']");
    private final By expiration1Locator = By.xpath("//input [@name='expiry_month']");
    private final By expiration2Locator = By.xpath("//input [@name='expiry_year']");
    private final By confirmButtonLocator = By.xpath("//button[@data-qa='pay-button']");


    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P11_PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to fill payment data and press confirm
    public P12_OrderPlaced fillPaymentDataAndConfirm(String name, String cardNumber, String cvc, String monthExpiration, String yearExpiration) {
        Utility.sendData(driver, nameOnCardLocator, name);
        Utility.sendData(driver, cardNumberLocator, cardNumber);
        Utility.sendData(driver, cvcLocator, cvc);
        Utility.sendData(driver, expiration1Locator, monthExpiration);
        Utility.sendData(driver, expiration2Locator, yearExpiration);
        Utility.clickOnElement(driver, confirmButtonLocator);

        return new P12_OrderPlaced(driver);
    }

}
