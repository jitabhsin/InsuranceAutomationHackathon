
package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.*;

public class TC_02_VerifyCountryErrorMessages extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_02_VerifyCountryErrorMessages.class);

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyErrorMessagesWithNoInput() {

        logger.info("TC_02 - Verify Country Error Messages Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        logger.info("Navigating to Travel Insurance page");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        logger.info("Submitting form without entering any details");

        travelHomePage.dateSubmitButton.click();

        String expectedCountryError = "Please enter the countries you are travelling";
        String expectedStartDateError = "Please select trip start date";
        String expectedEndDateError = "Please select trip end date";

        String actualCountryError = travelHomePage.retrieveCountryError();
        String actualStartDateError = travelHomePage.retrieveStartDateError();
        String actualEndDateError = travelHomePage.retrieveEndDateError();

        logger.info("Validation messages captured successfully");

        List<String> listOfErrors = travelHomePage.listAllErrorMessages();

        logger.info("Displaying all validation messages");

        for (String error : listOfErrors) {
            logger.info(error);
        }

        Assert.assertTrue(travelHomePage.countryErrorMessagesDisplayed(),"Validation errors are not displayed");
        Assert.assertTrue(travelHomePage.isNoCountrySelected(), "A country is already selected");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualCountryError, expectedCountryError, "Country validation message mismatch");
        softAssert.assertEquals(actualStartDateError, expectedStartDateError,"Start Date validation message mismatch");
        softAssert.assertEquals(actualEndDateError, expectedEndDateError, "End Date validation message mismatch");
        softAssert.assertAll();

        logger.info("All validation messages verified successfully");
        logger.info("TC_02 PASSED");
    }
}