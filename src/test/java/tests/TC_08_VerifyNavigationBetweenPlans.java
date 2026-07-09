package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.insurance.pages.TravelQuotePage;
import org.insurance.utils.ConfigReader;
import org.insurance.utils.ExcelReader;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_08_VerifyNavigationBetweenPlans extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_08_VerifyNavigationBetweenPlans.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @DataProvider(name = "travelData")
    public Object[][] getTravelData() {
        Object[][] data = new ExcelReader().readSheetTravel();
        Object[][] travelData = new Object[data.length][5];
        travelData[0][0] = data[0][0];
        travelData[0][1] = data[0][1];
        travelData[0][2] = data[0][2];
        travelData[0][3] = data[0][3];
        travelData[0][4] = data[0][4];
        return travelData;
    }

    @Test(dataProvider = "travelData")
    public void verifyTravelQuotaPlanNavigation(String country, String startDate, String endDate, String travellerCount, String travellerAges) {

        logger.info("TC_08 - Verify Travel Quote Navigation Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        logger.info("Country received from test data: {}", country);
        Assert.assertFalse(country.trim().isEmpty(), "Country is empty");

        logger.info("Navigating to Travel Insurance page");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        Assert.assertTrue(driver.getCurrentUrl().contains("travel-insurance"), "Travel Insurance page not loaded");

        logger.info("Travel Insurance page loaded successfully");

        logger.info("Selecting country: {}", country);
        travelHomePage.selectCountry(country);

        logger.info("Opening travel date calendar");
        travelHomePage.selectStartAndEndDateElement.click();

        logger.info("Selecting Start Date: {}", startDate);
        travelHomePage.selectStartDate(startDate);

        logger.info("Selecting End Date: {}", endDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Submitting travel dates");
        travelHomePage.submitDate();

        Assert.assertTrue(travelHomePage.isRedirectedToSelectTravellerCount(), "Traveller Details page not loaded");

        logger.info("Traveller Details page loaded successfully");

        String contactNo = ConfigReader.getProperty("contactNum");
        String email = ConfigReader.getProperty("email");

        logger.info("Entering Contact Number");
        travelHomePage.contactNumber.sendKeys(contactNo);

        logger.info("Entering Email");
        travelHomePage.email.sendKeys(email);

        int travellerCnt = Integer.parseInt(travellerCount);
        logger.info("Traveller Count: {}", travellerCnt);

        String[] ageStrings = travellerAges.split(",");
        int[] ages = new int[ageStrings.length];

        for (int i = 0; i < ageStrings.length; i++) {
            ages[i] = Integer.parseInt(ageStrings[i].trim());
            logger.info("Traveller {} Age: {}", i + 1, ages[i]);
        }

        logger.info("Selecting traveller count and age groups");
        travelHomePage.selectTravellerCount(travellerCnt, ages);

        logger.info("Selecting Health Issue option: NO");
        travelHomePage.noHealthCheckBox.click();

        logger.info("Submitting traveller details");
        travelHomePage.travellerSubmitButton.click();
        logger.info("Traveller details submitted successfully");

        logger.info("Waiting for navigation to Travel Quote page");
        Assert.assertTrue(driver.getCurrentUrl().contains("travel-app"), "Navigation to Travel Quote page failed");
        logger.info("Successfully navigated to Travel Quote page");

        travelQuotePage.waitForElementstoLoad();
        logger.info("Travel Quote Page Loaded Successfully");

        SoftAssert softAssert = new SoftAssert();

        logger.info("Verifying Next Coverage button is enabled");
        softAssert.assertTrue(travelQuotePage.isNextCoverageEnabled(), "Next Coverage Button not enabled for selecting next plans");

        logger.info("Navigating to last coverage page");
        travelQuotePage.navigateToLastCoverage();

        logger.info("Verifying Basic Plan visibility");
        boolean isBasicPlanDisplayed = travelQuotePage.isBasicAmountVisible();

        softAssert.assertTrue(isBasicPlanDisplayed, "Basic Plan NOT displayed");

        logger.info("Basic Plan displayed: {}", isBasicPlanDisplayed);

        logger.info("Verifying Premium Plus Plan visibility");
        boolean isPremiumPlusPlanDisplayed = travelQuotePage.isPremiumPlusVisible();

        softAssert.assertTrue(isPremiumPlusPlanDisplayed, "Premium Plus Plan NOT displayed");

        logger.info("Premium Plus Plan displayed: {}", isPremiumPlusPlanDisplayed);

        logger.info("Verifying Next Coverage button is disabled on last coverage");
        softAssert.assertFalse(travelQuotePage.isNextCoverageEnabled(), "Next Coverage Button enabled");

        logger.info("Verifying Previous Coverage button is enabled");
        softAssert.assertTrue(travelQuotePage.isPreviousCoverageEnabled(), "Previous Coverage Button NOT enabled for selecting previous plans");

        logger.info("Navigating back to first coverage page");
        travelQuotePage.navigateToFirstCoverage();

        logger.info("Verifying Essential Plan visibility");
        boolean isEssentialPlanDisplayed = travelQuotePage.isEssentialPlanDisplayed();

        softAssert.assertTrue(isEssentialPlanDisplayed, "Essential Plan NOT displayed");

        logger.info("Essential Plan displayed: {}", isEssentialPlanDisplayed);

        logger.info("Verifying Value Plan visibility");
        boolean isValuePlanDisplayed = travelQuotePage.isValuePlanDisplayed();
        softAssert.assertTrue(isValuePlanDisplayed, "Value Plan NOT displayed");
        logger.info("Value Plan displayed: {}", isValuePlanDisplayed);

        logger.info("Verifying Premium Plan visibility");
        boolean isPremiumPlanDisplayed = travelQuotePage.isPremiumPlanDisplayed();
        softAssert.assertTrue(isPremiumPlanDisplayed, "Premium Plan NOT displayed");
        logger.info("Premium Plan displayed: {}", isPremiumPlanDisplayed);

        logger.info("Verifying Previous Coverage button is disabled");
        softAssert.assertFalse(travelQuotePage.isPreviousCoverageEnabled(), "Previous Coverage Button Enabled");

        logger.info("Verifying Next Coverage button is enabled");
        softAssert.assertTrue(travelQuotePage.isNextCoverageEnabled(), "Next Coverage Button is NOT enabled");

        int visiblePlanCount = travelQuotePage.getVisiblePlanCount();
        logger.info("Visible plan count: {}", visiblePlanCount);
        softAssert.assertEquals(visiblePlanCount, 3, "Visible Plans are greater than 3");

        logger.info("Completed all Travel Quote Plan Navigations");

        softAssert.assertAll();

        logger.info("All traveller plans have been navigated and verified successfully");
        logger.info("TC_08 PASSED");
    }
}