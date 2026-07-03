package utils;

import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getName() + " FAILED");
        ScreenshotUtils.captureScreenshot(
                BaseTest.getDriver(),
                result.getName());
        logger.info("Screenshot captured");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getName() + " SKIPPED");
    }
}