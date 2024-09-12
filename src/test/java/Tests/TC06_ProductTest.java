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
import pages.P06_ProductPage;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.driverFactory.*;
import static Utilities.DataUtils.readDataFromJsonFile;
import static Utilities.DataUtils.readDataFromProperties;

@Listeners({iTestResultListener.class, iInvokedMethodListener.class})
public class TC06_ProductTest {

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
    public void checkAllProductIsVisibleTC() {
        new P01_HomePage(getDriver()).clickOnProductButton();
        Assert.assertTrue(new P06_ProductPage(getDriver()).checkAllProductIsVisible());
    }

    @Test
    public void clickOnViewProductButtonTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnProductButton().clickOnViewProductButton();
        LogsUtils.info(readDataFromProperties("environments", "PRODUCT_DETAILS_URL") + " website is opened");
        Assert.assertTrue(Utility.verifyURL(getDriver(), readDataFromProperties("environments", "PRODUCT_DETAILS_URL")));
    }

    @Test
    public void searchForItemTC() throws IOException {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .searchForItem(readDataFromJsonFile("searchData", "product"));

        LogsUtils.info("Product: " + readDataFromJsonFile("searchData", "product"));
        LogsUtils.info("Product URL: " + readDataFromProperties("environments", "PRODUCT_SEARCH_URL") +
                readDataFromJsonFile("searchData", "product"));

        Assert.assertEquals(getDriver().getCurrentUrl(),
                readDataFromProperties("environments", "PRODUCT_SEARCH_URL") +
                        readDataFromJsonFile("searchData", "product"));
    }

    @Test
    public void addProductsToCartTC() {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .AddProductsToCart(readDataFromJsonFile("cartData", "numberOfProducts"))
                .pressCartButton();
    }

    @Test
    public void AddProductMultipleTimesTC() {
        new P01_HomePage(getDriver()).clickOnProductButton()
                .AddProductMultipleTimes(readDataFromJsonFile("cartData", "numberOfTimes"))
                .pressCartButton();
    }

    @AfterMethod
    public void quit() {
        // Quit the browser and delete the driver
        quitDriver();
    }
}
