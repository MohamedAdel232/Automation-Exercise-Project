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
import pages.P13_CategoryProduct1Page;
import pages.P14_CategoryProduct2Page;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC14_CategoryProduct2Test {
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
    void verifyCategoryProduct() throws IOException {
        softAssert.assertTrue(new P01_HomePage(getDriver()).verifyCategorySectionVisibility());
        LogsUtils.info("Home page URL: " + readDataFromProperties("environments", "HOME_URL"));

        new P01_HomePage(getDriver()).selectDressSection();
        LogsUtils.info("Home page URL: " + readDataFromProperties("environments", "CATEGORY_PAGE1_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CATEGORY_PAGE1_URL")));

        softAssert.assertTrue(new P13_CategoryProduct1Page(getDriver()).verifyCategoryTextVisibility());

        new P13_CategoryProduct1Page(getDriver()).selectShirtSection();
        LogsUtils.info("Home page URL: " + readDataFromProperties("environments", "CATEGORY_PAGE2_URL"));
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CATEGORY_PAGE2_URL")));
        
        softAssert.assertTrue(new P14_CategoryProduct2Page(getDriver()).verifyCategoryTextVisibility());
        softAssert.assertAll();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}