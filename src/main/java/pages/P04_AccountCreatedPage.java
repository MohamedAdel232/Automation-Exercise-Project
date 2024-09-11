package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_AccountCreatedPage {

    // Page locators
    private final By continueButton = By.xpath("//a [@data-qa='continue-button']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P04_AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to click on continue button
    public P01_HomePage clickOnContinueButton() {
        Utility.clickOnElement(driver, continueButton);
        return new P01_HomePage(driver);
    }
}
