package org.insurance.utils;

import com.aventstack.extentreports.*;
import org.insurance.basetest.BaseTest;
import org.testng.*;

public class TestListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getReport();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshot = ScreenshotUtils.captureScreenshot(BaseTest.getDriver(), result.getMethod().getMethodName() + "_FAIL");
        test.get().fail(result.getThrowable());
        try {
            test.get().addScreenCaptureFromPath(screenshot);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}