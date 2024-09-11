package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.driverFactory.getDriver;

public class iInvokedMethodListener implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }

    // This methode will be invoked after every methode (test case) call
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        // Only return the last log file
        File logFile = Utility.getLatestFile(LogsUtils.LOGS_PATH);
        try {
            // Attach the log file with the allure report
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Generate info log if the test case failed
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogsUtils.info("Test case: " + testResult.getName() + " failed");
            Utility.takeScreenshoot(getDriver(), testResult.getName());
        }
    }
}
