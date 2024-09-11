package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.verifyURL;

public class P01_HomePage {
    // Page locators
    private final By signupButtonLocator = By.xpath("//*[@href='/login']");
    private final By loggedInLocator = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a");
    private final By deleteAccountButton = By.cssSelector("[href='/delete_account']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P01_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to check the url
    public boolean checkHomePageUrl(String expected) {
        return verifyURL(driver, expected);
    }

    // Methode to click on the signup button
    public P02_SignupPage clickOnSignupButton() {
        Utility.clickOnElement(driver, signupButtonLocator);
        return new P02_SignupPage(driver);
    }

    // Methode to check if the "Logged in as a user" text is visible
    public boolean checkLoggedInText() {
        return Utility.findWebElement(driver, loggedInLocator).isDisplayed();
    }

    // Methode to click on delete account button
    public P05_DeletedAccountPage clickOnDeleteAccountButton() {
        Utility.clickOnElement(driver, deleteAccountButton);
        return new P05_DeletedAccountPage(driver);
    }
}
