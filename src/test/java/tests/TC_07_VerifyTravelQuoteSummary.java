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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_07_VerifyTravelQuoteSummary extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_07_VerifyTravelQuoteSummary.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @Test
    public void verifyTravelQuoteSummary() {

        SoftAssert softAssert = new SoftAssert();

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetTravelFirstRow();

        String country = data[0].toString();
        String startDate = data[1].toString();
        String endDate = data[2].toString();
        String travellerCount = data[3].toString();
        String travellerAges = data[4].toString();
        String seniorTravellerDOBs = data[5].toString();
        String healthIssue = data[6].toString();
        String healthIssueTravellers = data[7].toString();

        logger.info("==========================================");
        logger.info("TC_07 - Verify Travel Quote Summary Started");
        logger.info("==========================================");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        logger.info("Navigating to Travel Insurance");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        logger.info("Selecting destination country");

        travelHomePage.selectCountry(country);

        logger.info("Selecting travel dates");

        travelHomePage.selectStartAndEndDateElement.click();

        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        travelHomePage.submitDate();

        String contactNo =
                ConfigReader.getProperty("contactNum");

        String email =
                ConfigReader.getProperty("email");

        logger.info("Entering traveller contact details");

        travelHomePage.contactNumber.sendKeys(contactNo);
        travelHomePage.email.sendKeys(email);

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

        logger.info("Selecting traveller details");

        travelHomePage.selectTravellerCount(
                travellerCnt,
                ages);

        logger.info("Selecting No Health Issue");

        travelHomePage.noHealthCheckBox.click();

        logger.info("Submitting traveller details");

        travelHomePage.travellerSubmitButton.click();

        logger.info("Travel Quote Page Loaded");

        // =============================
        // TRAVEL QUOTE VALIDATIONS
        // =============================

        logger.info("Validating Benefits Title");

        Assert.assertTrue(
                travelQuotePage.getBenefitsTitle()
                        .contains("Benefits curated for you"),
                "Benefits title mismatch");

        logger.info("Validating Plan Count");

        Assert.assertEquals(
                travelQuotePage.getPlanCount(),
                3,
                "Incorrect number of plans displayed");

        logger.info("Validating Share Quote Button");

        softAssert.assertTrue(
                travelQuotePage.isShareQuoteDisplayed(),
                "Share Quote button not displayed");

        logger.info("Validating Compare Benefits Button");

        softAssert.assertTrue(
                travelQuotePage.isCompareBenefitsDisplayed(),
                "Compare Benefits button not displayed");

        logger.info("Validating Essential Plan");

        softAssert.assertTrue(
                travelQuotePage.isEssentialPlanDisplayed(),
                "Essential Plan not displayed");

        softAssert.assertFalse(
                travelQuotePage.getEssentialPremium()
                        .trim()
                        .isEmpty(),
                "Essential premium missing");

        logger.info(
                "Essential Premium : "
                        + travelQuotePage.getEssentialPremium());

        logger.info("Validating Value Plan");

        softAssert.assertTrue(
                travelQuotePage.isValuePlanDisplayed(),
                "Value Plan not displayed");

        softAssert.assertFalse(
                travelQuotePage.getValuePremium()
                        .trim()
                        .isEmpty(),
                "Value premium missing");

        logger.info(
                "Value Premium : "
                        + travelQuotePage.getValuePremium());

        logger.info("Validating Premium Plan");

        softAssert.assertTrue(
                travelQuotePage.isPremiumPlanDisplayed(),
                "Premium Plan not displayed");

        softAssert.assertFalse(
                travelQuotePage.getPremiumAmount()
                        .trim()
                        .isEmpty(),
                "Premium amount missing");

        logger.info(
                "Premium Amount : "
                        + travelQuotePage.getPremiumAmount());

        logger.info("Validating Recommended Tag");

        softAssert.assertTrue(
                travelQuotePage.isRecommendedTagDisplayed(),
                "Recommended tag not displayed");

        logger.info("Validating Powered By AI");

        softAssert.assertTrue(
                travelQuotePage.isPoweredByAIDisplayed(),
                "Powered By AI section not displayed");

        logger.info("Validating Benefits Section");

        softAssert.assertTrue(
                travelQuotePage.getBenefitCount() > 0,
                "Benefits are not displayed");

        logger.info(
                "Total Benefit Count : "
                        + travelQuotePage.getBenefitCount());

        logger.info("Validating Key Highlight Section");

        softAssert.assertTrue(
                travelQuotePage.getKeyHighlightCount() > 0,
                "Key Highlights not displayed");

        logger.info(
                "Total Key Highlight Count : "
                        + travelQuotePage.getKeyHighlightCount());

        logger.info("Validating Coverage Navigation");

        softAssert.assertTrue(
                travelQuotePage.isNextCoverageEnabled(),
                "Next Coverage button should be enabled");

        logger.info("Next Coverage Button Verified");

        // Validate all soft assertions
        softAssert.assertAll();

        logger.info("All Travel Quote Summary Validations Passed and Verified Successfully");
        logger.info("TC_07 PASSED");
    }
}