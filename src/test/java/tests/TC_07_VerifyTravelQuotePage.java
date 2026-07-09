package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.insurance.pages.TravelQuotePage;
import org.insurance.utils.ConfigReader;
import org.insurance.utils.ExcelReader;
import org.insurance.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_07_VerifyTravelQuotePage extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_07_VerifyTravelQuotePage.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @DataProvider(name = "travelData")
    public Object[][] getTravelData() {
        logger.info("Reading first row from travel test data from Excel");
        Object[] travelData = new ExcelReader().readSheetTravelFirstRow();
        return new Object[][]{
                {
                        travelData[0],
                        travelData[1],
                        travelData[2],
                        travelData[3],
                        travelData[4]
                }
        };
    }

    @Test(dataProvider = "travelData")
    public void verifyTravelQuotePage(String country, String startDate, String endDate, String travellerCount, String travellerAges) {

        logger.info("TC_07 - Verify Travel Quote Validation Started");

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

        ScreenshotUtils.captureScreenshot(driver, "TC_07_VerifyTravelQuotePage");
        logger.info("Travel Quotes Page Screenshot taken");

        SoftAssert softAssert = new SoftAssert();

        logger.info("Validating Benefits Title");
        Assert.assertTrue(travelQuotePage.getBenefitsTitle().contains("Benefits curated for you"), "Benefits title mismatch");

        logger.info("Validating Plan Count");
        Assert.assertEquals(travelQuotePage.getPlanCount(), 3, "Incorrect number of plans displayed");

        logger.info("Validating Share Quote Button");
        softAssert.assertTrue(travelQuotePage.isShareQuoteDisplayed(), "Share Quote button not displayed");

        logger.info("Validating Compare Benefits Button");
        softAssert.assertTrue(travelQuotePage.isCompareBenefitsDisplayed(), "Compare Benefits button not displayed");

        logger.info("Validating Essential Plan");
        softAssert.assertTrue(travelQuotePage.isEssentialPlanDisplayed(), "Essential Plan not displayed");
        softAssert.assertFalse(travelQuotePage.getEssentialPremium().trim().isEmpty(), "Essential premium missing");
        logger.info("Essential Premium : " + travelQuotePage.getEssentialPremium());

        logger.info("Validating Value Plan");
        softAssert.assertTrue(travelQuotePage.isValuePlanDisplayed(), "Value Plan not displayed");
        softAssert.assertFalse(travelQuotePage.getValuePremium().trim().isEmpty(), "Value premium missing");
        logger.info("Value Premium : " + travelQuotePage.getValuePremium());

        logger.info("Validating Premium Plan");
        softAssert.assertTrue(travelQuotePage.isPremiumPlanDisplayed(), "Premium Plan not displayed");
        softAssert.assertFalse(travelQuotePage.getPremiumAmount().trim().isEmpty(), "Premium amount missing");
        logger.info("Premium Amount : " + travelQuotePage.getPremiumAmount());

        logger.info("Validating Recommended Tag");
        softAssert.assertTrue(travelQuotePage.isRecommendedTagDisplayed(), "Recommended tag not displayed");

        logger.info("Validating Powered By AI");
        softAssert.assertTrue(travelQuotePage.isPoweredByAIDisplayed(), "Powered By AI section not displayed");

        logger.info("Validating Benefits Section");
        softAssert.assertTrue(travelQuotePage.getBenefitCount() > 0, "Benefits are not displayed");
        logger.info("Total Benefit Count : " + travelQuotePage.getBenefitCount());

        logger.info("Validating Key Highlight Section");
        softAssert.assertTrue(travelQuotePage.getKeyHighlightCount() > 0, "Key Highlights not displayed");

        logger.info("Total Key Highlight Count : " + travelQuotePage.getKeyHighlightCount());

        logger.info("Validating Coverage Navigation");

        logger.info("Validating Next Coverage Enabled");
        softAssert.assertTrue(travelQuotePage.isNextCoverageEnabled(), "Next Coverage button should be enabled");
        logger.info("Next Coverage Button Verified");

        logger.info("Validating Previous Coverage Disabled");
        softAssert.assertTrue(travelQuotePage.isPreviousCoverageDisabled(), "Previous Coverage button should be disabled");
        logger.info("Previous Coverage Button Verified");

        logger.info("Coverage Navigation Verified");

        softAssert.assertAll();

        logger.info("All Travel Quote Summary Validations Passed and Verified Successfully");
        logger.info("TC_07 PASSED");
    }
}