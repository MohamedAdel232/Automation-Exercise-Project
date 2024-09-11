package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class driverFactory {
    // Create a tread local driver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // A methode that set up the driver (browser - maximize)
    public static void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--guest");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--guest");
                driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--guest");
                driverThreadLocal.set(new EdgeDriver(edgeOptions));
                break;
        }
    }

    // A methode to use get driver function
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // A methode to remove driver after finishing
    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
