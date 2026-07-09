package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.utils.PlanDetails;
import org.insurance.pages.TravelHomePage;
import org.insurance.pages.TravelQuotePage;
import org.insurance.utils.ConfigReader;
import org.insurance.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class TC_10_RetrieveLowestThreePlans extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_10_RetrieveLowestThreePlans.class);

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
    public void verifyTravelQuoteSummary(String country, String startDate, String endDate, String travellerCount, String travellerAges) {

        logger.info("TC_10 - Verify Travel Quote Summary Started");

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

        Assert.assertTrue(travelQuotePage.isTravelQuotePageLoaded(), "Travel Quote Page not loaded properly");
        logger.info("Travel Quote Page verified successfully");

        travelQuotePage.scrollIntoMedicalFilterView();
        logger.info("Scrolled to Medical Cover section");

        SoftAssert softAssert = new SoftAssert();
        List<PlanDetails> allPlans = new ArrayList<>();

        logger.info("Selecting lowest coverage for first plan");

        String coverage1 = travelQuotePage.selectLowestCoverage(0);
        PlanDetails plan1 = travelQuotePage.getCurrentPlanDetails(0);
        allPlans.add(plan1);

        logger.info("Captured Plan: {}", plan1);
        softAssert.assertNotNull(plan1, "Plan 1 details are null");

        logger.info("Selecting lowest coverage for second plan");

        String coverage2 = travelQuotePage.selectLowestCoverage(1);
        PlanDetails plan2 = travelQuotePage.getCurrentPlanDetails(1);
        allPlans.add(plan2);

        logger.info("Captured Plan: {}", plan2);
        softAssert.assertNotNull(plan2, "Plan 2 details are null");

        logger.info("Selecting lowest coverage for third plan");

        String coverage3 = travelQuotePage.selectLowestCoverage(2);
        PlanDetails plan3 = travelQuotePage.getCurrentPlanDetails(2);
        allPlans.add(plan3);

        logger.info("Captured Plan: {}", plan3);
        softAssert.assertNotNull(plan3, "Plan 3 details are null");

        logger.info("Navigating to next coverage");
        travelQuotePage.clickNextCoverage();

        logger.info("Selecting lowest coverage for fourth plan");

        String coverage4 = travelQuotePage.selectLowestCoverage(2);
        PlanDetails plan4 = travelQuotePage.getCurrentPlanDetails(2);
        allPlans.add(plan4);

        logger.info("Captured Plan: {}", plan4);
        softAssert.assertNotNull(plan4, "Plan 4 details are null");

        logger.info("Navigating to final coverage");
        travelQuotePage.clickNextCoverage();

        logger.info("Selecting lowest coverage for fifth plan");

        String coverage5 = travelQuotePage.selectLowestCoverage(2);
        PlanDetails plan5 = travelQuotePage.getCurrentPlanDetails(2);
        allPlans.add(plan5);

        logger.info("Captured Plan: {}", plan5);
        softAssert.assertNotNull(plan5, "Plan 5 details are null");

        logger.info("Total plans collected: {}", allPlans.size());
        softAssert.assertEquals(allPlans.size(), 5, "Expected 5 plans to be collected");

        List<PlanDetails> lowestPlans = travelQuotePage.getLowestThreePlans(allPlans);
        logger.info("Lowest premium plans identified. Count: {}", lowestPlans.size());
        softAssert.assertEquals(lowestPlans.size(), 3, "Lowest premium plan count mismatch");

        logger.info("Printing Lowest 3 Premium Plans");

        for (PlanDetails plan : lowestPlans) {
            int i = 1;
            logger.info("Lowest Premium Plan {} => {}",i, plan);
            i++;

            softAssert.assertTrue(travelQuotePage.isPlanDetailValid(plan), "Invalid plan details found");
            softAssert.assertNotNull(plan.getPlanName(), "Plan name is null");
            softAssert.assertNotNull(plan.getPremiumAmount(), "Premium amount is null");
            softAssert.assertNotNull(plan.getMedicalCover(), "Medical cover is null");
        }


        softAssert.assertTrue(travelQuotePage.arePlansSortedByPremium(lowestPlans), "Lowest plans are not sorted by premium");
        softAssert.assertAll();

        logger.info("All Travel Plans have been retrieved and top 3 lowest plans have been listed and verified successfully");
        logger.info("TC_10 PASSED");
    }
}