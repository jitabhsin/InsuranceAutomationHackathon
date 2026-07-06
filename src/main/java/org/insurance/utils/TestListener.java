package org.insurance.utils;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("{} STARTED", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("{} PASSED", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("{} FAILED", result.getName());

        try {
            if (BaseTest.getDriver() != null) {

                String screenshotPath =
                        ScreenshotUtils.captureScreenshot(
                                BaseTest.getDriver(),
                                result.getName());

                logger.info("Screenshot saved at: {}", screenshotPath);
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("{} SKIPPED", result.getName());
    }
}