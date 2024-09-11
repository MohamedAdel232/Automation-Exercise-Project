package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_AccountInfoPage {
    // Page locators
    private final By enterAccountInformationLocator = By.xpath("(// div //b)[1]");
    private final By titleLocator = By.id("id_gender1");
    private final By passwordLocator = By.id("password");
    private final By dayLocator = By.id("days");
    private final By monthLocator = By.id("months");
    private final By yearLocator = By.id("years");
    private final By newsLetterLocator = By.id("newsletter");
    private final By receiveSpecialOffersLocator = By.id("optin");
    private final By firstNameLocator = By.id("first_name");
    private final By lastNameLocator = By.id("last_name");
    private final By companyLocator = By.id("company");
    private final By address1Locator = By.id("address1");
    private final By address2Locator = By.id("address2");
    private final By countryLocator = By.id("country");
    private final By stateLocator = By.id("state");
    private final By cityLocator = By.id("city");
    private final By zipcodeLocator = By.id("zipcode");
    private final By mobileNumberLocator = By.id("mobile_number");
    private final By createAccountButtonLocator = By.xpath("(//button [@type='submit'])[1]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P03_AccountInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to check that the text is displayed
    public boolean verifyTextVisibility() {
        return Utility.findWebElement(driver, enterAccountInformationLocator).isDisplayed();
    }

    // Methode to select a title
    public P03_AccountInfoPage selectTitle() {
        Utility.clickOnElement(driver, titleLocator);
        return this;
    }

    // Methode to enter a password
    public P03_AccountInfoPage enterPassword(String password) {
        Utility.sendData(driver, passwordLocator, password);
        return this;
    }

    // Methode to select a birthday
    public P03_AccountInfoPage selectBirthday(String day, String month, String year) {
        Utility.selectFromDropDown(driver, dayLocator, day);
        Utility.selectFromDropDown(driver, monthLocator, month);
        Utility.selectFromDropDown(driver, yearLocator, year);
        return this;
    }

    // Methode to select news letter
    public P03_AccountInfoPage selectNewsLetter() {
        Utility.clickOnElement(driver, newsLetterLocator);
        return this;
    }

    // Methode to select receive special offers
    public P03_AccountInfoPage selectReceiveSpecialOffers() {
        Utility.clickOnElement(driver, receiveSpecialOffersLocator);
        return this;
    }

    // Methode to enter the rest of the account info
    public P03_AccountInfoPage fillAddressInfo(String firstName, String lastName, String company,
                                               String address1, String address2, String country, String state,
                                               String city, String zipcode, String mobileNumber) {
        Utility.sendData(driver, firstNameLocator, firstName);
        Utility.sendData(driver, lastNameLocator, lastName);
        Utility.sendData(driver, companyLocator, company);
        Utility.sendData(driver, address1Locator, address1);
        Utility.sendData(driver, address2Locator, address2);
        Utility.selectFromDropDown(driver, countryLocator, country);
        Utility.sendData(driver, stateLocator, state);
        Utility.sendData(driver, cityLocator, city);
        Utility.sendData(driver, zipcodeLocator, zipcode);
        Utility.sendData(driver, mobileNumberLocator, mobileNumber);
        return this;
    }

    // Methode to click on create an account button
    public P04_AccountCreatedPage clickOnCreateAccountButton() {
        Utility.clickOnElement(driver, createAccountButtonLocator);
        return new P04_AccountCreatedPage(driver);
    }

}
