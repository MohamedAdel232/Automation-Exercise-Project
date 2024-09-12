package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P12_OrderPlaced {
    // Page locators
    private final By orderPlacedLocator = By.xpath("(//b)[2]");
    private final By continueButtonLocator = By.xpath("//a [@data-qa='continue-button']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P12_OrderPlaced(WebDriver driver) {
        this.driver = driver;
    }

    // Check confirmation message
    public boolean checkConfirmationMessage() {
        return Utility.findWebElement(driver, orderPlacedLocator).isDisplayed();
    }

    // Place order confirmed
    public P01_HomePage finishTheOrder() {
        Utility.clickOnElement(driver, continueButtonLocator);
        return new P01_HomePage(driver);
    }
}
