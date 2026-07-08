package org.insurance.basetest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.insurance.utils.ConfigReader;

import java.lang.reflect.Method;
import org.testng.annotations.Listeners;
import org.insurance.utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    protected static WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    private String previousTestMethod = "";

    @BeforeTest
    public void setup() {
        logger.info("========== Browser Launch Started ==========");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
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
            logger.info("Navigated to base URL");
        } else {
            logger.info("DataProvider iteration - preserving page state");
        }

        previousTestMethod = currentTestMethod;
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
}