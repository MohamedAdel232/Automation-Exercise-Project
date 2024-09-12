package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P07_ProductDetailsPage {
    // Page locators
    private final By productNameLocator = By.xpath("//div [@class='col-sm-7']//h2");
    private final By productCategoryLocator = By.xpath("//div[@class='product-information'] //p[1]");
    private final By productPriceLocator = By.xpath("(//div [@class='col-sm-7']//span//span)");
    private final By productAvailabilityLocator = By.xpath("//p //b [text()='Availability:']");
    private final By productConditionLocator = By.xpath("//p //b [text()='Condition:']");
    private final By productBrandLocator = By.xpath("//p //b [text()='Brand:']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P07_ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode for checking the visibility of product info
    public boolean checkingVisibilityOfProductInfo() {
        return Utility.findWebElement(driver, productNameLocator).isDisplayed()
                && Utility.findWebElement(driver, productCategoryLocator).isDisplayed()
                && Utility.findWebElement(driver, productPriceLocator).isDisplayed()
                && Utility.findWebElement(driver, productAvailabilityLocator).isDisplayed()
                && Utility.findWebElement(driver, productConditionLocator).isDisplayed()
                && Utility.findWebElement(driver, productBrandLocator).isDisplayed();
    }

}
