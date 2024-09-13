package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.verifyURL;

public class P01_HomePage {
    // Page locators
    private final By signupButtonLocator = By.xpath("//*[@href='/login']");
    private final By loggedInLocator = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a");
    private final By deleteAccountButtonLocator = By.cssSelector("[href='/delete_account']");
    private final By logOutButtonLocator = By.xpath("//div [@class='shop-menu pull-right'] //ul[1]//li[4]//a[1]");
    private final By productButtonLocator = By.xpath("//div[@class='col-sm-8']//div//li[2] //a[1] ");
    private final By categorySectionLocator = By.xpath("//div [@id='accordian']");
    private final By womenCategory = By.xpath("(//a[@data-toggle='collapse'])[1]");
    private final By dressSection = By.xpath("(//div [@id='Women'] //div //ul //li //a)[1]");

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
        Utility.clickOnElement(driver, deleteAccountButtonLocator);
        return new P05_DeletedAccountPage(driver);
    }

    // Methode to click on logout button
    public P02_SignupPage clickOnLogoutButton() {
        Utility.clickOnElement(driver, logOutButtonLocator);
        return new P02_SignupPage(driver);
    }

    // Methode to click on product button
    public P06_ProductPage clickOnProductButton() {
        Utility.clickOnElement(driver, productButtonLocator);
        return new P06_ProductPage(driver);
    }

    // Methode to verify category section visibility
    public boolean verifyCategorySectionVisibility() {
        return Utility.findWebElement(driver, categorySectionLocator).isDisplayed();
    }

    // Methode to select dress section from women category
    public P13_CategoryProduct1Page selectDressSection() {
        Utility.clickOnElement(driver, womenCategory);
        Utility.clickOnElement(driver, dressSection);
        return new P13_CategoryProduct1Page(driver);
    }
}
