package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_ProductPage {
    // Page locators
    private final By allProductLocator = By.className("features_items");
    private final By viewProductButtonLocator = By.xpath("//a[@href='/product_details/1']");
    private final By searchBarLocator = By.xpath("//input [@type='text']");
    private final By searchIconLocator = By.xpath("//button[@type='button']");
    
    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P06_ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to make sure that all product is visible
    public boolean checkAllProductIsVisible() {
        return Utility.findWebElement(driver, allProductLocator).isDisplayed();
    }

    // methode to click on view product button
    public P07_ProductDetailsPage clickOnViewProductButton() {
        Utility.clickOnElement(driver, viewProductButtonLocator);
        return new P07_ProductDetailsPage(driver);
    }

    // Methode for searching for an item
    public P08_SearchResultsPage searchForItem(String data) {
        Utility.sendData(driver, searchBarLocator, data);
        Utility.clickOnElement(driver, searchIconLocator);
        return new P08_SearchResultsPage(driver);
    }
}
