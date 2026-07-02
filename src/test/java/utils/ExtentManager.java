package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentReports getReport() {
        // Report Path
        ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReports/ExtentReport.html");
        // Report Name
        reporter.config().setReportName("Mini Project Automation Report");
        // Document Title
        reporter.config().setDocumentTitle("OrangeHRM Execution Report");
        // Create Extent Report
        reporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        // Attach Reporter
        extent.attachReporter(reporter);
        // System Information
        extent.setSystemInfo("Tester Name", "Spartans");
        extent.setSystemInfo("Framework", "Selenium with hybrid framework");
        extent.setSystemInfo("Project", "Insurance Major Project");
        extent.setSystemInfo("Environment", "QA");
        return extent;
    }
}
