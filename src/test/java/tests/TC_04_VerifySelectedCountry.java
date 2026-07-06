package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.insurance.utils.ConfigReader;
import org.insurance.utils.ExcelReader;

public class TC_04_VerifySelectedCountry extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_04_VerifySelectedCountry.class);

    HomePage homePage;
    TravelHomePage travelHomePage;

    @DataProvider(name = "travelData")
    public Object[][] getData() {
        logger.info("Reading travel test data from Excel");
        ExcelReader excel = new ExcelReader();
        return excel.readSheetTravel();
    }

    @Test(dataProvider = "travelData")
    public void verifyAndDisplaySelectedCountry(String country, String startDate, String endDate, String travellerCount, String travellerAges) {

        logger.info("TC_04 - Verify Selected Country Started");

        Assert.assertFalse(country.trim().isEmpty(),"Country is empty");
        Assert.assertFalse(startDate.trim().isEmpty(),"Start Date is empty");
        Assert.assertFalse(endDate.trim().isEmpty(),"End Date is empty");
        Assert.assertFalse(travellerCount.trim().isEmpty(),"Traveller Count is empty");
        Assert.assertFalse(travellerAges.trim().isEmpty(),"Traveller Ages are empty");

        logger.info("Input data validation passed");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        if (!driver.getCurrentUrl().contains("travel-insurance") || travelHomePage.contactNumber.isDisplayed()) {

            logger.info("Navigating to Travel Insurance");

            driver.get(ConfigReader.getProperty("baseUrl"));

            homePage.clickTravelInsurance();
            homePage.clickTravelScope();
            homePage.clickOtherCountries();

            Assert.assertTrue(driver.getCurrentUrl().contains("travel-insurance"),"Travel Insurance page not loaded");
        }

        logger.info("Travel Insurance page loaded");

        SoftAssert softAssert = new SoftAssert();

        boolean isDateSubmitPresent = travelHomePage.isSubmitButtonPresent();

        logger.info("Selecting country: {}", country);
        travelHomePage.selectCountry(country);

        String selectedCountry = travelHomePage.getSelectedCountry();
        logger.info("Selected Country: {}", selectedCountry);

        boolean isDisplayed = travelHomePage.isSelectTravelTypeVisible();
        boolean isClicked = travelHomePage.isSelectTravelTypeSelected();
        boolean isSelected = travelHomePage.isCountrySelectedCorrectly();

        softAssert.assertTrue(isDisplayed,"Select Country field not visible");
        softAssert.assertTrue(isClicked,"Country field not clicked");
        softAssert.assertTrue(isSelected,"Wrong country selected");
        softAssert.assertEquals(selectedCountry, country,"Selected country does not match expected country");

        logger.info("Country validation completed");

        String termsStatus = travelHomePage.getTermsStatus();

        travelHomePage.selectStartAndEndDateElement.click();

        boolean isCalendarOpen = travelHomePage.isCalenderOpen();

        logger.info("Selecting travel dates");

        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Trip Duration: {}", travelHomePage.retrieveTripDuration());

        travelHomePage.submitDate();
        boolean isRedirected = travelHomePage.isRedirectedToSelectTravellerCount();

        softAssert.assertEquals(termsStatus, "CHECKED", "Terms checkbox should be checked");

        Assert.assertTrue(isCalendarOpen,"Calendar not opened");
        Assert.assertTrue(isDateSubmitPresent,"Submit button not present");
        Assert.assertTrue(isRedirected,"Traveller Count page not loaded");
        softAssert.assertAll();

        logger.info("Successfully redirected to Traveller Count page");

        logger.info("TC_04 PASSED");
    }
}