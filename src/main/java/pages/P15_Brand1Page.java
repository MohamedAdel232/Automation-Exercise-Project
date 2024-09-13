package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P15_Brand1Page {
    // Page locators
    private final By brandTextLocator = By.xpath("//h2 [contains (@class,'text-center')]");
    private final By madameBrandLocator = By.xpath("((//ul [contains (@class,'nav-stacked')]) //li)[3]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P15_Brand1Page(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to verify brand text is visible
    public boolean brandTextVisibility() {
        return Utility.findWebElement(driver, brandTextLocator).isDisplayed();
    }

    // Methode to click on polo brand
    public P16_Brand2Page clickOnMadameBrand() {
        Utility.clickOnElement(driver, madameBrandLocator);
        return new P16_Brand2Page(driver);
    }
}
