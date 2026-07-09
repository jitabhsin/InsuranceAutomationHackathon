package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11_NavigateCarPage extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_11_NavigateCarPage.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyCarInsuranceNavigation() {

        logger.info("TC_11 - Verify Car Insurance Navigation Started");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);
        homePage.clickCarInsurance();
        ScreenshotUtils.captureScreenshot(driver, "TC_11_Car_Page");

        Assert.assertTrue(carPage.isCarPageDisplayed(),"Car Insurance form is not displayed");
        logger.info("Car Insurance form displayed successfully");

        Assert.assertTrue(carPage.isRegistrationFieldDisplayed(),"Car Registration Number field is not displayed");
        logger.info("Registration Number field displayed successfully");

        Assert.assertTrue(carPage.isMobileFieldDisplayed(),"Mobile Number field is not displayed");
        logger.info("Mobile Number field displayed successfully");

        Assert.assertTrue(carPage.isEmailFieldDisplayed(),"Email field is not displayed");
        logger.info("Email field displayed successfully");
        logger.info("TC_11 PASSED");
    }
}