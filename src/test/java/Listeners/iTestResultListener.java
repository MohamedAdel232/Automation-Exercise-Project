package Listeners;

import Utilities.LogsUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class iTestResultListener implements ITestListener {
    // This methode will be invoked at the test start
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test case: " + result.getName() + " started");
    }

    // This methode will be invoked at the test success
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test case: " + result.getName() + " passed");
    }

    // This methode will be invoked at the test skip
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test case: " + result.getName() + " skipped");
    }
}
