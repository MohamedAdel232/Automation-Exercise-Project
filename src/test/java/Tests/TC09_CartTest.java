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
import org.testng.asserts.SoftAssert;
import pages.P01_HomePage;
import pages.P02_SignupPage;
import pages.P06_ProductPage;
import pages.P09_CartPage;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC09_CartTest {

    SoftAssert softAssert = new SoftAssert();
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
    public void addProductsToCartTC() {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .AddProductsToCart(readDataFromJsonFile("cartData", "numberOfProducts"))
                .pressCartButton();
        Assert.assertTrue(new P09_CartPage(getDriver()).verifyNumberOfProducts(readDataFromJsonFile("cartData", "numberOfProducts")));
    }

    @Test
    public void verifyPriceInfoTC() {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .AddProductsToCart(readDataFromJsonFile("cartData", "numberOfProducts"))
                .pressCartButton();
        Assert.assertTrue(new P09_CartPage(getDriver()).verifyPriceInfo(readDataFromJsonFile("cartData", "numberOfProducts")));
    }

    @Test
    public void verifyQuantityNumberTC() {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .AddProductMultipleTimes(readDataFromJsonFile("cartData", "numberOfTimes"))
                .pressCartButton();
        Assert.assertTrue(new P09_CartPage(getDriver()).verifyQuantityNumber(readDataFromJsonFile("cartData", "numberOfTimes")));
    }

    @Test
    public void proceedToCheckoutTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnSignupButton();
        LogsUtils.info("Login page URL: " + readDataFromProperties("environments", "LOGIN_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "LOGIN_URL")));

        new P02_SignupPage(getDriver()).enterValidLoginData(readDataFromJsonFile("signupInformation", "email"),
                        readDataFromJsonFile("signupInformation", "password"))
                .clickOnLoginButton();
        LogsUtils.info("User email: " + readDataFromJsonFile("signupInformation", "email"));
        LogsUtils.info("User password: " + readDataFromJsonFile("signupInformation", "password"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "HOME_URL")));


        new P01_HomePage(getDriver()).clickOnProductButton();
        LogsUtils.info("Product page URL: " + readDataFromProperties("environments", "PRODUCT_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "PRODUCT_URL")));


        new P06_ProductPage(getDriver()).AddProductsToCart(readDataFromJsonFile("cartData", "numberOfProducts"))
                .pressCartButton();
        LogsUtils.info("Product page URL: " + readDataFromProperties("environments", "VIEW_CAR_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "VIEW_CART_URL")));

        new P09_CartPage(getDriver()).proceedToCheckout();
        LogsUtils.info("Product page URL: " + readDataFromProperties("environments", "CHECKOUT_URL"));

        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CHECKOUT_URL")));

        softAssert.assertAll();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
