package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.insurance.utils.ConfigReader;

public class TC_13_AfterNavigateWithoutData extends BaseTest {
    private static final Logger logger =
            LogManager.getLogger(TC_13_AfterNavigateWithoutData.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyNewVehicleValidationMessages() {

        logger.info("TC_13 - Verify New Vehicle Validation Messages Started");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        String invalidMobile = ConfigReader.getProperty("invalidMobile");
        String expectedMobileError = ConfigReader.getProperty("expectedMobileErrorForCar");

        logger.info("Test data loaded from config.properties");

        homePage.clickCarInsurance();
        logger.info("Navigated to Car Insurance page");

        carPage.clickNewVehicleLink();
        logger.info("Clicked New Vehicle link");

        Assert.assertTrue(carPage.isNewVehiclePageDisplayed(), "New Vehicle page is not displayed");
        logger.info("New Vehicle page displayed successfully");

        Assert.assertTrue(carPage.isNewVehicleMobileFieldDisplayed(), "Mobile Number field is not displayed");
        logger.info("Mobile Number field displayed successfully");

        logger.info("Clicking Get Quote without entering any data");
        carPage.clickNewVehicleGetQuote();

        String blankMobileError = carPage.getMobileErrorMessage();
        logger.info("Blank Mobile Error : {}", blankMobileError);
        Assert.assertEquals(blankMobileError, expectedMobileError, "Blank mobile validation failed");
        logger.info("Blank field validations verified successfully");

        logger.info("Entering invalid mobile");
        carPage.enterMobile(invalidMobile);
        carPage.clickNewVehicleGetQuote();

        String invalidMobileError = carPage.getMobileErrorMessage();
        ScreenshotUtils.captureScreenshot(driver, "TC_13_Validation_Errors");
        logger.info("Invalid Mobile Error : {}", invalidMobileError);
        Assert.assertEquals(invalidMobileError, expectedMobileError, "Invalid mobile validation failed");
        Assert.assertTrue(invalidMobileError.toLowerCase().contains("mobile"), "Mobile error text validation failed");
        logger.info("Invalid data validations verified successfully");

        logger.info("TC_13 PASSED");
    }
}