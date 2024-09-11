package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02_SignupPage {
    // Page locators
    private final By NewUserSignupLocator = By.xpath("(//div //h2)[3]");
    private final By nameLocator = By.xpath("//input [@type='text']");
    private final By emailLocator = By.xpath("//input [@data-qa='signup-email']");
    private final By signupButtonLocator = By.xpath("//button [@data-qa='signup-button']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P02_SignupPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to check that signed up text is displayed
    public boolean verifySignupText() {
        return driver.findElement(NewUserSignupLocator).isDisplayed();
    }

    // Methode to enter name
    public P02_SignupPage enterName(String name) {
        Utility.sendData(driver, nameLocator, name);
        return this;
    }

    // Methode to enter email
    public P02_SignupPage enterEmail(String email) {
        Utility.sendData(driver, emailLocator, email);
        return this;
    }

    // Methode to click on signup button
    public P03_AccountInfoPage clickSignupButton() {
        Utility.clickOnElement(driver, signupButtonLocator);
        return new P03_AccountInfoPage(driver);
    }
}
