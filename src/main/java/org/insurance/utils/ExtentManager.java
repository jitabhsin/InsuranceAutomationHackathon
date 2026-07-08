package org.insurance.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentManager {

    private static ExtentReports extent;
    private ExtentManager() {}

    public static synchronized ExtentReports getReport() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            reporter.config().setReportName("Project Automation Report");
            reporter.config().setDocumentTitle("Insurance Execution Report");
            reporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "USHAR");
            extent.setSystemInfo("Framework", "Selenium TestNG");
            extent.setSystemInfo("Project", "InsuranceAutomation");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}