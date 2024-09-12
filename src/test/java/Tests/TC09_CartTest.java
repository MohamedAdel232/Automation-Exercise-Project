package Tests;

import Listeners.iInvokedMethodListener;
import Listeners.iTestResultListener;
import Utilities.LogsUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.P01_HomePage;
import pages.P09_CartPage;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC09_CartTest {

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

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
