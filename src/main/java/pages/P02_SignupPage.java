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
    private final By loginToYourAccountLocator = By.xpath("//div [@class='login-form'] //h2 ");
    private final By loginEmailLocator = By.xpath("// input [@data-qa='login-email']");
    private final By loginPasswordLocator = By.xpath("// input [@data-qa='login-password']");
    private final By loginButtonLocator = By.xpath("// button[@data-qa='login-button']");
    private final By incorrectDataText = By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p");

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

    // Methode to check that log in to your account text in displayed
    public boolean verifyLoginText() {
        return driver.findElement(loginToYourAccountLocator).isDisplayed();
    }

    // Methode to enter valid log in data
    public P02_SignupPage enterValidLoginData(String email, String password) {
        Utility.sendData(driver, loginEmailLocator, email);
        Utility.sendData(driver, loginPasswordLocator, password);
        return this;
    }

    // Methode to enter log in data
    public P02_SignupPage enterInValidLoginData(String email, String password) {
        Utility.sendData(driver, loginEmailLocator, email);
        Utility.sendData(driver, loginPasswordLocator, password);
        return this;
    }

    // Methode to click on login button
    public P01_HomePage clickOnLoginButton() {
        Utility.clickOnElement(driver, loginButtonLocator);
        return new P01_HomePage(driver);
    }

    // Methode to check Your email or password is incorrect text
    public boolean checkIncorrectText() {
        return Utility.findWebElement(driver, incorrectDataText).isDisplayed();
    }
}
