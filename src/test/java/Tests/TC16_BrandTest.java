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
import pages.P01_HomePage;
import pages.P06_ProductPage;
import pages.P15_Brand1Page;
import pages.P16_Brand2Page;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})

public class TC16_BrandTest {
    SoftAssert softAssert = new SoftAssert();
    // Create a web driver
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
    public void brandProductTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnProductButton();
        LogsUtils.info("Product URL: " + readDataFromProperties("environments", "PRODUCT_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "PRODUCT_URL")));

        softAssert.assertTrue(new P06_ProductPage(getDriver()).brandSectionVisibility());

        new P06_ProductPage(getDriver()).clickOnPoloBrand();
        LogsUtils.info("Brand page URL: " + readDataFromProperties("environments", "BRAND_PAGE1_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "BRAND_PAGE1_URL")));

        softAssert.assertTrue(new P15_Brand1Page(getDriver()).brandTextVisibility());

        new P15_Brand1Page(getDriver()).clickOnMadameBrand();
        LogsUtils.info("Brand page URL: " + readDataFromProperties("environments", "BRAND_PAGE2_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "BRAND_PAGE2_URL")));

        softAssert.assertTrue(new P16_Brand2Page(getDriver()).brandTextVisibility());

        softAssert.assertAll();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
