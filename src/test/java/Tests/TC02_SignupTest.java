package Tests;

import Listeners.iInvokedMethodListener;
import Listeners.iTestResultListener;
import Utilities.LogsUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P02_SignupPage;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC02_SignupTest {

    // Fake data
    String fakeEmail = new Faker().internet().emailAddress();
    String fakePassword = new Faker().number().digits(3);

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
    public void verifySignupTextTC() {
        new P01_HomePage(getDriver()).clickOnSignupButton();
        Assert.assertTrue(new P02_SignupPage(getDriver()).verifySignupText());
    }

    @Test
    public void clickOnSignupButtonTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnSignupButton()
                .enterName(readDataFromJsonFile("signupInformation", "name"))
                .enterEmail(readDataFromJsonFile("signupInformation", "email"))
                .clickSignupButton();
        LogsUtils.info("User name: " + readDataFromJsonFile("signupInformation", "name"));
        LogsUtils.info("User email: " + readDataFromJsonFile("signupInformation", "email"));

        Assert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "REGISTER_URL")));
    }

    @Test
    public void verifyLoginTextTC() {
        new P01_HomePage(getDriver()).clickOnSignupButton();
        Assert.assertTrue(new P02_SignupPage(getDriver()).verifyLoginText());
    }

    @Test
    public void clickOnLoginButtonWithValidDataTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnSignupButton()
                .enterValidLoginData(readDataFromJsonFile("signupInformation", "email"),
                        readDataFromJsonFile("signupInformation", "password"))
                .clickOnLoginButton();

        LogsUtils.info("User email: " + readDataFromJsonFile("signupInformation", "email"));
        LogsUtils.info("User password: " + readDataFromJsonFile("signupInformation", "password"));

        Assert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "HOME_URL")));
    }

    @Test
    public void clickOnLoginButtonWithInvalidDataTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnSignupButton()
                .enterValidLoginData(fakeEmail, fakePassword)
                .clickOnLoginButton();

        LogsUtils.info("User email: " + fakeEmail);
        LogsUtils.info("User password: " + fakePassword);

        Assert.assertTrue(new P02_SignupPage(getDriver()).checkIncorrectText());
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
