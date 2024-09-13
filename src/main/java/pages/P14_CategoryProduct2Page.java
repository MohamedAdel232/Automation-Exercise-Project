package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P14_CategoryProduct2Page {
    // Page locators
    private final By categoryTextLocator = By.xpath("//h2 [contains(@class,'text-center')]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P14_CategoryProduct2Page(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to verify category section visibility
    public boolean verifyCategoryTextVisibility() {
        return Utility.findWebElement(driver, categoryTextLocator).isDisplayed();
    }

}
