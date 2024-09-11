package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    // Screen shoots folder path
    public static final String SCREENSHOOT_PATH = "test-outputs/Screenshoots/";

    // A methode to click on web element
    public static void clickOnElement(WebDriver driver, By locator) {
        // Wait until the element be clickable
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));

        // Click on the element
        driver.findElement(locator).click();
    }

    // A methode to send data to a web element
    public static void sendData(WebDriver driver, By locator, String data) {
        // Wait until the element be visible
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Send data to the element
        driver.findElement(locator).sendKeys(data);
    }

    // A methode to get text from a web element
    public static String getText(WebDriver driver, By locator) {
        // Wait until the element be visible
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        // Get text from the element
        return driver.findElement(locator).getText();
    }

    // A methode for general wait on the page
    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // A methode to take a screen shoot
    public static void takeScreenshoot(WebDriver driver, String screenshootName) {
        try {
            // Take the screen shoot and save it in src file
            File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy the screen shoot to the screen shoot folder path
            File screenDes = new File(SCREENSHOOT_PATH + screenshootName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenSrc, screenDes);

            // Attach the screen shoot with the allure report
            Allure.addAttachment(screenshootName, Files.newInputStream(Path.of(screenDes.getPath())));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    // A methode that take a full screen shoot of the screen
    public static void takeFullScreenshoot(WebDriver driver, By locator) {
        try {
            // Using shutterbug, scroll to the web element that want to be shot, then take the screen and highlight the element and save the screen to the folrder path
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOOT_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    // A methode to scroll to a specific web element
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    // A methode that select option from a dropdown
    public static void selectFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }

    // A methode that return the timestamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ss").format(new Date());
    }

    // A methode that return a random number
    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    // A methode that generate a unique number
    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) {
        Set<Integer> generatedNumber = new HashSet<>();
        while (generatedNumber.size() < numberOfProductsNeeded) {
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumber.add(randomNumber);
        }
        return generatedNumber;
    }

    // A methode that find a web element by a locator (from locator to web element)
    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    // A methode that compare between two urls
    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            Utility.generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // A methode that returns the latest modified file
    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    // A methode that get the session cookies
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    // A methode that restore the session cookies
    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }
}
