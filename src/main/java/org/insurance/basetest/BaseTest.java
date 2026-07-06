    package org.insurance.basetest;

    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.testng.annotations.*;
    import org.insurance.utils.ConfigReader;

    public class BaseTest {

        protected static WebDriver driver;
        protected Logger logger = LogManager.getLogger(this.getClass());

        @BeforeTest
        public void setup() {

            logger.info("========== Browser Launch Started ==========");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[]{"enable-automation"});
            options.setExperimentalOption(
                    "useAutomationExtension",
                    false);
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            logger.info("Browser launched successfully");
            driver.get(ConfigReader.getProperty("baseUrl"));
            logger.info("Navigated to URL: " + ConfigReader.getProperty("baseUrl"));
            logger.info("========== Setup Completed ==========");
        }

        @BeforeMethod
        public void startTest() {
            logger.info("==================================");
            logger.info("Test Execution Started");
            logger.info("==================================");
        }

        @AfterMethod
        public void endTest() {
            logger.info("==================================");
            logger.info("Test Execution Completed");
            logger.info("==================================");
        }

    //    @AfterTest
    //    public void tearDown() {
    //        logger.info("Closing browser");
    //        if (driver != null) {
    //            driver.quit();
    //            logger.info("Browser closed successfully");
    //        }
    //    }

        public static WebDriver getDriver() {
            return driver;
        }
    }