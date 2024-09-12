package Tests;

import Listeners.iInvokedMethodListener;
import Listeners.iTestResultListener;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC12_OrderPlaced {

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
    public void checkConfirmationMessageTC() throws IOException {
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
        LogsUtils.info("Checkout page URL: " + readDataFromProperties("environments", "CHECKOUT_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CHECKOUT_URL")));

        new P10_AddressDetailsPage(getDriver())
                .enterCommentAndPlaceOrder(readDataFromJsonFile("checkOut", "comment"));
        LogsUtils.info("Comment: " + readDataFromJsonFile("checkOut", "comment"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "PAYMENT_URL")));

        new P11_PaymentPage(getDriver()).fillPaymentDataAndConfirm(
                readDataFromJsonFile("payment", "name"),
                readDataFromJsonFile("payment", "cardNumber"),
                readDataFromJsonFile("payment", "cvc"),
                readDataFromJsonFile("payment", "monthExpiration"),
                readDataFromJsonFile("payment", "yearExpiration"));

        LogsUtils.info("Name: " + readDataFromJsonFile("payment", "name"));
        LogsUtils.info("Card number: " + readDataFromJsonFile("payment", "cardNumber"));
        LogsUtils.info("cvc: " + readDataFromJsonFile("payment", "cvc"));
        LogsUtils.info("Month expiration: " + readDataFromJsonFile("payment", "monthExpiration"));
        LogsUtils.info("Year expiration: " + readDataFromJsonFile("payment", "yearExpiration"));
        LogsUtils.info("Order Placed page URL: " + readDataFromProperties("environments", "ORDER_PLACED_URL"));
        LogsUtils.info(getDriver().getCurrentUrl());

        softAssert.assertTrue(new P12_OrderPlaced(getDriver()).checkConfirmationMessage());
        softAssert.assertAll();
    }

    @Test
    public void finishTheOrder() throws IOException {
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
        LogsUtils.info("Checkout page URL: " + readDataFromProperties("environments", "CHECKOUT_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CHECKOUT_URL")));

        new P10_AddressDetailsPage(getDriver())
                .enterCommentAndPlaceOrder(readDataFromJsonFile("checkOut", "comment"));
        LogsUtils.info("Comment: " + readDataFromJsonFile("checkOut", "comment"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "PAYMENT_URL")));

        new P11_PaymentPage(getDriver()).fillPaymentDataAndConfirm(
                readDataFromJsonFile("payment", "name"),
                readDataFromJsonFile("payment", "cardNumber"),
                readDataFromJsonFile("payment", "cvc"),
                readDataFromJsonFile("payment", "monthExpiration"),
                readDataFromJsonFile("payment", "yearExpiration"));

        LogsUtils.info("Name: " + readDataFromJsonFile("payment", "name"));
        LogsUtils.info("Card number: " + readDataFromJsonFile("payment", "cardNumber"));
        LogsUtils.info("cvc: " + readDataFromJsonFile("payment", "cvc"));
        LogsUtils.info("Month expiration: " + readDataFromJsonFile("payment", "monthExpiration"));
        LogsUtils.info("Year expiration: " + readDataFromJsonFile("payment", "yearExpiration"));
        LogsUtils.info("Order Placed page URL: " + readDataFromProperties("environments", "ORDER_PLACED_URL"));
        LogsUtils.info(getDriver().getCurrentUrl());

        new P12_OrderPlaced(getDriver()).finishTheOrder();
        LogsUtils.info("Home URL: " + readDataFromProperties("environments", "HOME_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "HOME_URL")));

        softAssert.assertAll();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}