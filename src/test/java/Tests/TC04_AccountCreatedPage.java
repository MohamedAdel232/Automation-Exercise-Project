package Tests;

import Listeners.iInvokedMethodListener;
import Listeners.iTestResultListener;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.P01_HomePage;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC04_AccountCreatedPage {

    private static final String password = readDataFromJsonFile("signupInformation", "password");
    private static final String day = readDataFromJsonFile("signupInformation", "day");
    private static final String month = readDataFromJsonFile("signupInformation", "month");
    private static final String year = readDataFromJsonFile("signupInformation", "year");
    private static final String firstName = readDataFromJsonFile("signupInformation", "firstName");
    private static final String lastName = readDataFromJsonFile("signupInformation", "lastName");
    private static final String company = readDataFromJsonFile("signupInformation", "company");
    private static final String address1 = readDataFromJsonFile("signupInformation", "address1");
    private static final String address2 = readDataFromJsonFile("signupInformation", "address2");
    private static final String country = readDataFromJsonFile("signupInformation", "country");
    private static final String state = readDataFromJsonFile("signupInformation", "state");
    private static final String city = readDataFromJsonFile("signupInformation", "city");
    private static final String zipcode = readDataFromJsonFile("signupInformation", "zipcode");
    private static final String mobileNumber = readDataFromJsonFile("signupInformation", "mobileNumber");
    private WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        // Select browser to open
        setupDriver(readDataFromProperties("environments", "BROWSER"));
        LogsUtils.info(readDataFromProperties("environments", "BROWSER") + " is opened");

        // Open home url
        getDriver().get(readDataFromProperties("environments", "HOME_URL"));
        LogsUtils.info(readDataFromProperties("environments", "HOME_URL") + " website is opened");

        // Wait 5 sec at the start
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void clickOnContinueButtonTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnSignupButton()
                .enterName(readDataFromJsonFile("signupInformation", "name"))
                .enterEmail(readDataFromJsonFile("signupInformation", "email"))
                .clickSignupButton()
                .selectTitle()
                .enterPassword(password)
                .selectBirthday(day, month, year)
                .selectNewsLetter()
                .selectReceiveSpecialOffers()
                .fillAddressInfo(firstName, lastName, company, address1, address2, country, state, city, zipcode, mobileNumber)
                .clickOnCreateAccountButton()
                .clickOnContinueButton();

        LogsUtils.info("User name: " + readDataFromJsonFile("signupInformation", "name"));
        LogsUtils.info("User email: " + readDataFromJsonFile("signupInformation", "email"));
        LogsUtils.info("User firstName: " + firstName);
        LogsUtils.info("User lastName: " + lastName);
        LogsUtils.info("User company: " + company);
        LogsUtils.info("User address1: " + address1);
        LogsUtils.info("User address2: " + address2);
        LogsUtils.info("User country: " + country);
        LogsUtils.info("User state: " + state);
        LogsUtils.info("User city: " + city);
        LogsUtils.info("User zipcode: " + zipcode);
        LogsUtils.info("User mobileNumber: " + mobileNumber);

        Assert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "HOME_URL")));
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
