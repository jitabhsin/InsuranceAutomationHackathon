package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.insurance.utils.ConfigReader;

public class TC_13AfterNavigateWithoutData extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_13AfterNavigateWithoutData.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyNewVehicleValidationMessages() {

        logger.info("TC_13 - Verify New Vehicle Validation Messages Started");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        String invalidMobile = ConfigReader.getProperty("invalidMobile");
        String invalidEmail = ConfigReader.getProperty("invalidEmail");
        String expectedMobileError = ConfigReader.getProperty("expectedMobileError");
        String expectedEmailError = ConfigReader.getProperty("expectedEmailError");

        logger.info("Test data loaded from config.properties");

        homePage.clickCarInsurance();
        logger.info("Navigated to Car Insurance page");

        carPage.clickNewVehicleLink();
        logger.info("Clicked New Vehicle link");

        Assert.assertTrue(carPage.isNewVehiclePageDisplayed(), "New Vehicle page is not displayed");
        logger.info("New Vehicle page displayed successfully");

        Assert.assertTrue(carPage.isNewVehicleMobileFieldDisplayed(),"Mobile Number field is not displayed");
        logger.info("Mobile Number field displayed successfully");

        Assert.assertTrue(carPage.isNewVehicleEmailFieldDisplayed(),"Email field is not displayed");
        logger.info("Email field displayed successfully");

        logger.info("Clicking Get Quote without entering any data");
        carPage.clickNewVehicleGetQuote();

        String blankMobileError = carPage.getMobileErrorMessage();
        String blankEmailError = carPage.getEmailErrorMessage();
        logger.info("Blank Mobile Error : {}", blankMobileError);
        logger.info("Blank Email Error : {}", blankEmailError);
        Assert.assertEquals(blankMobileError,expectedMobileError,"Blank mobile validation failed");
        Assert.assertEquals(blankEmailError, expectedEmailError,"Blank email validation failed");
        logger.info("Blank field validations verified successfully");
        logger.info("Entering invalid mobile and email");

        carPage.enterMobile(invalidMobile);
        carPage.enterEmail(invalidEmail);
        carPage.clickNewVehicleGetQuote();

        String invalidMobileError = carPage.getMobileErrorMessage();
        String invalidEmailError = carPage.getEmailErrorMessage();
        logger.info("Invalid Mobile Error : {}", invalidMobileError);
        logger.info("Invalid Email Error : {}", invalidEmailError);
        Assert.assertEquals(invalidMobileError, expectedMobileError,"Invalid mobile validation failed");
        Assert.assertEquals(invalidEmailError, expectedEmailError,"Invalid email validation failed");
        Assert.assertTrue(invalidMobileError.toLowerCase().contains("mobile"),"Mobile error text validation failed");
        Assert.assertTrue(invalidEmailError.toLowerCase().contains("email"),"Email error text validation failed");
        logger.info("Invalid data validations verified successfully");

        logger.info("TC_13 PASSED");
    }
}