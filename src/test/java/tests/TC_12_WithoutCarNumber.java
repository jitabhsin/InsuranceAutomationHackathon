package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_12_WithoutCarNumber extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_12_WithoutCarNumber.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyErrorWithoutCarNumber() {

        logger.info("TC_12 - Verify Error Without Car Number Started");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        logger.info("Navigating to Car Insurance page");
        homePage.clickCarInsurance();

        logger.info("Clicking Get Quote without entering car number");
        carPage.clickGetQuote();

        logger.info("Verifying validation message visibility");

        Assert.assertTrue(carPage.isValidationMessageDisplayed(),"Error message is not displayed");
        logger.info("Validation message displayed successfully");

        String actualMessage = carPage.getValidationMessage();
        ScreenshotUtils.captureScreenshot(driver, "TC_12_Reg_Number_Error");
        logger.info("Captured Validation Message: {}", actualMessage);

        Assert.assertEquals(actualMessage,"Please enter a valid reg number","Incorrect validation message displayed");
        logger.info("Validation message verified successfully");

        logger.info("TC_12 PASSED");
    }
}