package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.insurance.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.insurance.utils.ExcelReader;

import java.util.List;

public class TC_05_VerifyTravellerDetailsErrorMessages extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_05_VerifyTravellerDetailsErrorMessages.class);

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyErrorMessagesWithNoInput() {
        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetTravelFirstRow();

        String country   = data[0].toString();
        String startDate   = data[1].toString();
        String endDate  = data[2].toString();

        logger.info("TC_05 - Verify Traveller Details Error Messages Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        logger.info("Navigating to Travel Insurance page");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        logger.info("Selecting country");

        travelHomePage.selectCountry(country);

        logger.info("Opening calendar");

        travelHomePage.selectStartAndEndDateElement.click();
        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Submitting travel dates");
        travelHomePage.submitDate();

        logger.info("Submitting traveller details without entering any data");
        travelHomePage.travellerSubmitButton.click();

        String expectedMobileError = ConfigReader.getProperty("expectedMobileError");
        String expectedEmailError = ConfigReader.getProperty("expectedEmailError");
        String expectedHealthError = ConfigReader.getProperty("expectedHealthError");

        String actualMobileError = travelHomePage.retrieveMobileError();
        String actualEmailError = travelHomePage.retrieveEmailError();
        String actualHealthError = travelHomePage.retrieveHealthError();

        logger.info("Validation messages captured successfully");
        List<String> listOfErrors = travelHomePage.listAllTravellerErrorMessages();

        logger.info("Displaying all traveller validation messages");

        for (String error : listOfErrors) {
            logger.info(error);
        }

        Assert.assertTrue(travelHomePage.travellerErrorMessagesDisplayed(),"Traveller validation errors are not displayed");
        Assert.assertNull(travelHomePage.verifyNoNumberSelected(), "Mobile number field contains a value");
        Assert.assertNull(travelHomePage.verifyNoEmailSelected(),"Email field contains a value");

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualMobileError, expectedMobileError,"Mobile number validation message mismatch");
        softAssert.assertEquals(actualEmailError, expectedEmailError,"Email validation message mismatch");
        softAssert.assertEquals(actualHealthError, expectedHealthError, "Traveller selection validation message mismatch");
        softAssert.assertAll();

        logger.info("All traveller validation messages verified successfully");
        logger.info("TC_05 PASSED");
    }
}