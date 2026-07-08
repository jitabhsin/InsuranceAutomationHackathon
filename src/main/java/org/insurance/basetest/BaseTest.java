package org.insurance.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
public class BaseTest {

    protected static WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());
    private String previousTestMethod = "";

    @BeforeTest
    public void setup() {
        deleteOldScreenshots();
        deleteOldLogs();

        logger.info("========== Browser Launch Started ==========");

        String browser = ConfigReader.getProperty("browser");

        if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
            logger.info("Microsoft Edge launched");
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            logger.info("Google Chrome launched");
        }

        driver.manage().window().maximize();

        logger.info("Browser launched successfully");
        logger.info("========== Setup Completed ==========");
    }

    @BeforeMethod
    public void startTest(Method method) {

        logger.info("==================================");
        logger.info("Test Execution Started : " + method.getName());
        logger.info("==================================");

        String currentTestMethod = method.getName();
        if (!currentTestMethod.equals(previousTestMethod)) {
            driver.get(ConfigReader.getProperty("baseUrl"));
            logger.info("Navigated to Base URL");
        } else {
            logger.info("DataProvider iteration - Preserving page state");
        }
        previousTestMethod =
                currentTestMethod;
    }

    @AfterMethod
    public void endTest() {

        logger.info("==================================");
        logger.info("Test Execution Completed");
        logger.info("==================================");
    }

    @AfterTest
    public void tearDown() {
        logger.info("Closing browser");
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void deleteOldScreenshots() {
        File folder = new File(System.getProperty("user.dir") + File.separator + "screenshots");
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            logger.info("Old screenshots deleted");
        }
    }

    private void deleteOldLogs() {
        File folder = new File(System.getProperty("user.dir") + File.separator + "logs");
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
            logger.info("Old Log files deleted");
        }
    }
}