package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P08_SearchResultsPage {
    // Page locators
    private final By searchedProductsTextLocator = By.xpath("//section[2]/div[1]/div/div[2]/div/h2");
    private final By searchResultsLocator = By.xpath("//div[@class='features_items']");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P08_SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to make sure search results text are visible
    public boolean verifyVisibilityOfSearchedProductsText() {
        return Utility.findWebElement(driver, searchedProductsTextLocator).isDisplayed();
    }

    // Methode to make sure search results  are visible
    public boolean verifyVisibilityOfSearchResults() {
        return Utility.findWebElement(driver, searchResultsLocator).isDisplayed();
    }
}
