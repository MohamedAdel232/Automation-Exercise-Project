package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P16_Brand2Page {
    // Page locators
    private final By brandTextLocator = By.xpath("//h2 [contains (@class,'text-center')]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P16_Brand2Page(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to verify brand text is visible
    public boolean brandTextVisibility() {
        return Utility.findWebElement(driver, brandTextLocator).isDisplayed();
    }
}
