package pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P13_CategoryProduct1Page {
    // Page locators
    private final By categoryTextLocator = By.xpath("//h2 [contains(@class,'text-center')]");
    private final By menCategory = By.xpath("(//a[@data-toggle='collapse'])[2]");
    private final By shirtsSection = By.xpath("(//div [@id='Men'] //div //ul //li //a)[1]");

    // Create a web driver
    private final WebDriver driver;

    // Class construction
    public P13_CategoryProduct1Page(WebDriver driver) {
        this.driver = driver;
    }

    // Methode to verify category section visibility
    public boolean verifyCategoryTextVisibility() {
        return Utility.findWebElement(driver, categoryTextLocator).isDisplayed();
    }

    // Methode to select dress section from women category
    public P14_CategoryProduct2Page selectShirtSection() {
        Utility.clickOnElement(driver, menCategory);
        Utility.clickOnElement(driver, shirtsSection);
        return new P14_CategoryProduct2Page(driver);
    }
}
