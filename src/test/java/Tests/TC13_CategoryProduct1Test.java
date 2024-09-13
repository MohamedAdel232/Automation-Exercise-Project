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
import pages.P13_CategoryProduct1Page;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC13_CategoryProduct1Test {
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
    void verifyCategoryTextVisibilityTC() {
        Assert.assertTrue(new P13_CategoryProduct1Page(getDriver()).verifyCategoryTextVisibility());
    }

    @Test
    void selectShirtSectionTC() throws IOException {
        new P13_CategoryProduct1Page(getDriver()).selectShirtSection();
        softAssert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "CATEGORY_PAGE2_URL")));
        softAssert.assertAll();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
