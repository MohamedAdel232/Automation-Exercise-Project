package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_DeletedAccountPage {

    // Page locators
    private final By continueButtonLocator = By.xpath("// a [@data-qa='continue-button']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P05_DeletedAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to click on continue button on the delete page
    public P01_HomePage clickOnContinueDeleteButton() {
        Utility.clickOnElement(driver, continueButtonLocator);
        return new P01_HomePage(driver);
    }
}
