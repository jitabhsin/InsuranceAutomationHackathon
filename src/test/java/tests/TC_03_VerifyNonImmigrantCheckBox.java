package tests;

<<<<<<< HEAD
import org.insurance.basetest.BaseTest;
=======
import basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
>>>>>>> 42a42db32d10d3d1afa2ff4165a0b855e529a612
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<< HEAD
import org.insurance.utils.ConfigReader;
=======
import utils.ExcelReader;
>>>>>>> 42a42db32d10d3d1afa2ff4165a0b855e529a612

public class TC_03_VerifyNonImmigrantCheckBox extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_03_VerifyNonImmigrantCheckBox.class);

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyNonImmigrantCheckBox() {
        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetTravelFirstRow();

        String country   = data[0].toString();
        String startDate   = data[1].toString();
        String endDate  = data[2].toString();

        logger.info("TC_03 - Verify Non-Immigrant Check Box Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        logger.info("Navigating to Travel Insurance page");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        logger.info("Selecting country: {}", country);
        travelHomePage.selectCountry(country);

        String selectedCountry = travelHomePage.getSelectedCountry();
        logger.info("Selected Country: {}", selectedCountry);

        Assert.assertEquals(selectedCountry, country, "Selected country does not match expected country");

        logger.info("Opening travel date calendar");
        travelHomePage.selectStartAndEndDateElement.click();

        Assert.assertTrue(travelHomePage.isCalenderOpen(),"Calendar is NOT opened");

        logger.info("Selecting travel dates");

        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Trip Duration: {}", travelHomePage.retrieveTripDuration());

        logger.info("Selecting Non-Immigrant checkbox");
        travelHomePage.selectNonImmigrantCheckBox();

        String alertMessage =travelHomePage.retrieveAlertMessage();
        logger.info("Alert Message: {}", alertMessage);

        boolean isAlertFrameOpened = travelHomePage.attentionFrameDisplayed();
        boolean isOkButtonPresent = travelHomePage.verifyOkButtonPresence();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(isAlertFrameOpened,"Attention frame is NOT displayed");
        softAssert.assertTrue(isOkButtonPresent,"OK button is NOT displayed");

        logger.info("Clicking OK button");
        travelHomePage.okButton.click();

        boolean isEndDateOptionAvailable = travelHomePage.verifyEndDateOptionAvailableAgain();

        softAssert.assertTrue(isEndDateOptionAvailable,"End Date option is NOT displayed after closing alert");
        softAssert.assertAll();

        logger.info("Non-Immigrant checkbox validation completed successfully");
        logger.info("TC_03 PASSED");
    }
}