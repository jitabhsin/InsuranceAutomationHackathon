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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_08_VerifyNavigationBetweenPlans extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_08_VerifyNavigationBetweenPlans.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @Test
    public void verifyTravelQuotaPlanNavigation() {

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetTravelFirstRow();

        String country = data[0].toString();
        String startDate = data[1].toString();
        String endDate = data[2].toString();
        String travellerCount = data[3].toString();
        String travellerAges = data[4].toString();

        logger.info("TC_08 - Verify Travel Quote Summary Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        Assert.assertFalse(
                country.trim().isEmpty(),
                "Country is empty");

        logger.info("Navigating to Travel Insurance section");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        logger.info("Travel Insurance page loaded successfully");

        travelHomePage.selectCountry(country);

        logger.info("Selected country: {}", country);

        travelHomePage.selectStartAndEndDateElement.click();

        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Selected travel dates. Start Date: {}, End Date: {}",
                startDate, endDate);

        travelHomePage.submitDate();

        logger.info("Traveller Details page loaded");

        String contactNo =
                ConfigReader.getProperty("contactNum");

        String email =
                ConfigReader.getProperty("email");

        travelHomePage.contactNumber.sendKeys(contactNo);
        travelHomePage.email.sendKeys(email);

        logger.info("Entered contact details");

        int travellerCnt =
                Integer.parseInt(travellerCount);

        String[] ageStrings =
                travellerAges.split(",");

        int[] ages =
                new int[ageStrings.length];

        for (int i = 0; i < ageStrings.length; i++) {
            ages[i] =
                    Integer.parseInt(ageStrings[i].trim());
        }

        logger.info("Traveller ages processed successfully");

        travelHomePage.selectTravellerCount(
                travellerCnt,
                ages);

        logger.info("Selected traveller count: {}", travellerCnt);

        logger.info("No health issues declared");

        travelHomePage.noHealthCheckBox.click();

        travelHomePage.travellerSubmitButton.click();

        logger.info("Travel Quote Page Loaded");

        SoftAssert softAssert = new SoftAssert();

        logger.info("Verifying Next Coverage button is enabled");
        softAssert.assertTrue(
                travelQuotePage.isNextCoverageEnabled(),
                "Next Coverage Button not enabled for selecting next plans");

        logger.info("Navigating to last coverage page");
        travelQuotePage.navigateToLastCoverage();

        logger.info("Verifying Basic Plan visibility");
        boolean isBasicPlanDisplayed =
                travelQuotePage.isBasicAmountVisible();

        softAssert.assertTrue(
                isBasicPlanDisplayed,
                "Basic Plan NOT displayed");

        logger.info("Basic Plan displayed: {}", isBasicPlanDisplayed);

        logger.info("Verifying Premium Plus Plan visibility");
        boolean isPremiumPlusPlanDisplayed =
                travelQuotePage.isPremiumPlusVisible();

        softAssert.assertTrue(
                isPremiumPlusPlanDisplayed,
                "Premium Plus Plan NOT displayed");

        logger.info("Premium Plus Plan displayed: {}",
                isPremiumPlusPlanDisplayed);

        logger.info("Verifying Next Coverage button is disabled on last coverage");
        softAssert.assertFalse(
                travelQuotePage.isNextCoverageEnabled(),
                "Next Coverage Button enabled");

        logger.info("Verifying Previous Coverage button is enabled");
        softAssert.assertTrue(
                travelQuotePage.isPreviousCoverageEnabled(),
                "Previous Coverage Button NOT enabled for selecting previous plans");

        logger.info("Navigating back to first coverage page");
        travelQuotePage.navigateToFirstCoverage();

        logger.info("Verifying Essential Plan visibility");
        boolean isEssentialPlanDisplayed =
                travelQuotePage.isEssentialPlanDisplayed();

        softAssert.assertTrue(
                isEssentialPlanDisplayed,
                "Essential Plan NOT displayed");

        logger.info("Essential Plan displayed: {}",
                isEssentialPlanDisplayed);

        logger.info("Verifying Value Plan visibility");
        boolean isValuePlanDisplayed =
                travelQuotePage.isValuePlanDisplayed();

        softAssert.assertTrue(
                isValuePlanDisplayed,
                "Value Plan NOT displayed");

        logger.info("Value Plan displayed: {}",
                isValuePlanDisplayed);

        logger.info("Verifying Premium Plan visibility");
        boolean isPremiumPlanDisplayed =
                travelQuotePage.isPremiumPlanDisplayed();

        softAssert.assertTrue(
                isPremiumPlanDisplayed,
                "Premium Plan NOT displayed");

        logger.info("Premium Plan displayed: {}",
                isPremiumPlanDisplayed);

        logger.info("Verifying Previous Coverage button is disabled");
        softAssert.assertFalse(
                travelQuotePage.isPreviousCoverageEnabled(),
                "Previous Coverage Button Enabled");

        logger.info("Verifying Next Coverage button is enabled");
        softAssert.assertTrue(
                travelQuotePage.isNextCoverageEnabled(),
                "Next Coverage Button is NOT enabled");

        int visiblePlanCount =
                travelQuotePage.getVisiblePlanCount();

        logger.info("Visible plan count: {}", visiblePlanCount);

        softAssert.assertEquals(
                visiblePlanCount,
                3,
                "Visible Plans are greater than 3");

        logger.info("Completed all Travel Quote page validations");

        softAssert.assertAll();

        logger.info("All traveller plans have been navigated and verified successfully");
        logger.info("TC_08 PASSED");
    }
}