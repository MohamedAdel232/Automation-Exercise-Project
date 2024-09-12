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
    private final By continueShoppingButtonLocator = By.xpath("//button [contains (@class, 'btn-success')]");
    private final By cartButtonLocator = By.xpath("//li //a [@href='/view_cart']");
    private final By addProductOneToCartButtonLocator = By.xpath("//a[@data-product-id='1']");


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

    // Methode to click on add to cart button
    public P06_ProductPage AddProductsToCart(String numberOfProducts) {
        int temp = Integer.parseInt(numberOfProducts);
        for (int index = 1; index <= temp; index++) {
            By addToCartButtonLocator = By.xpath("//a[@data-product-id='" + index + "']");
            Utility.clickOnElement(driver, addToCartButtonLocator);
            Utility.clickOnElement(driver, continueShoppingButtonLocator);
        }
        return this;
    }

    // Methode for searching for an item
    public P09_CartPage pressCartButton() {
        Utility.clickOnElement(driver, cartButtonLocator);
        return new P09_CartPage(driver);
    }

    // Methode to click on add to cart button
    public P06_ProductPage AddProductMultipleTimes(String numberOfTimes) {
        int number = Integer.parseInt(numberOfTimes);
        for (int index = 1; index <= number; index++) {
            Utility.clickOnElement(driver, addProductOneToCartButtonLocator);
            Utility.clickOnElement(driver, continueShoppingButtonLocator);
        }
        return this;
    }
}
