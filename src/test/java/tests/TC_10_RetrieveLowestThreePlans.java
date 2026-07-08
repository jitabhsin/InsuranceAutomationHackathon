package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.PlanDetails;
import org.insurance.pages.TravelHomePage;
import org.insurance.pages.TravelQuotePage;
import org.insurance.utils.ConfigReader;
import org.insurance.utils.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
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

    @Test
    public void verifyTravelQuoteSummary() {

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

        logger.info("TC_07 - Verify Travel Quote Summary Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        Assert.assertFalse(
                country.trim().isEmpty(),
                "Country is empty");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("travel-insurance"),
                "Travel Insurance page not loaded");

        travelHomePage.selectCountry(country);

        travelHomePage.selectStartAndEndDateElement.click();

        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        travelHomePage.submitDate();

        Assert.assertTrue(
                travelHomePage
                        .isRedirectedToSelectTravellerCount(),
                "Traveller Details page not loaded");

        String contactNo =
                ConfigReader.getProperty("contactNum");

        String email =
                ConfigReader.getProperty("email");

        travelHomePage.contactNumber.sendKeys(contactNo);
        travelHomePage.email.sendKeys(email);

        int travellerCnt =
                Integer.parseInt(travellerCount);

        String[] ageStrings =
                travellerAges.split(",");

        if (ageStrings.length != travellerCnt) {

            driver.get(
                    ConfigReader.getProperty("baseUrl"));

            throw new SkipException(
                    "Invalid Traveller Count Mapping");
        }

        int[] ages =
                new int[ageStrings.length];

        boolean seniorTravellerPresent = false;

        for (int i = 0; i < ageStrings.length; i++) {

            ages[i] =
                    Integer.parseInt(ageStrings[i].trim());

            if (ages[i] > 70) {
                seniorTravellerPresent = true;
            }
        }

        travelHomePage.selectTravellerCount(
                travellerCnt,
                ages);

        if (seniorTravellerPresent) {

            if (!travelHomePage
                    .validateSeniorTravellerDOBs(
                            travellerAges,
                            seniorTravellerDOBs)) {

                throw new SkipException(
                        "Invalid Senior Traveller DOB Mapping");
            }

            travelHomePage.enterSeniorTravellerDOBs(
                    ages,
                    seniorTravellerDOBs);
        }

        if (healthIssue.equalsIgnoreCase("Yes")) {

            travelHomePage.yesHealthCheckBox.click();

            travelHomePage
                    .selectHealthIssueTravellersByAge(
                            travellerAges,
                            healthIssueTravellers);
        }
        else {

            travelHomePage.noHealthCheckBox.click();
        }

        travelHomePage.travellerSubmitButton.click();

        logger.info("Travel Quote Page Loaded");

        travelQuotePage.waitForPage();

        Assert.assertTrue(
                travelQuotePage.isTravelQuotePageLoaded(),
                "Travel Quote Page not loaded properly");

        logger.info("Travel Quote Page verified successfully");

        WebElement ele =
                driver.findElement(
                        By.className("multi-sub-limit"));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                ele);

        logger.info("Scrolled to Medical Cover section");

        SoftAssert softAssert =
                new SoftAssert();

        List<PlanDetails> allPlans =
                new ArrayList<>();

        logger.info(
                "Selecting lowest coverage for first plan");

        String coverage1 =
                travelQuotePage.selectLowestCoverage(0);

        PlanDetails plan1 =
                travelQuotePage.getCurrentPlanDetails(0);

        allPlans.add(plan1);

        logger.info(
                "Captured Plan: {}",
                plan1);

        softAssert.assertNotNull(
                plan1,
                "Plan 1 details are null");

        logger.info(
                "Selecting lowest coverage for second plan");

        String coverage2 =
                travelQuotePage.selectLowestCoverage(1);

        PlanDetails plan2 =
                travelQuotePage.getCurrentPlanDetails(1);

        allPlans.add(plan2);

        logger.info(
                "Captured Plan: {}",
                plan2);

        softAssert.assertNotNull(
                plan2,
                "Plan 2 details are null");

        logger.info(
                "Selecting lowest coverage for third plan");

        String coverage3 =
                travelQuotePage.selectLowestCoverage(2);

        PlanDetails plan3 =
                travelQuotePage.getCurrentPlanDetails(2);

        allPlans.add(plan3);

        logger.info(
                "Captured Plan: {}",
                plan3);

        softAssert.assertNotNull(
                plan3,
                "Plan 3 details are null");

        logger.info("Navigating to next coverage");

        travelQuotePage.clickNextCoverage();

        logger.info(
                "Selecting lowest coverage for fourth plan");

        String coverage4 =
                travelQuotePage.selectLowestCoverage(2);

        PlanDetails plan4 =
                travelQuotePage.getCurrentPlanDetails(2);

        allPlans.add(plan4);

        logger.info(
                "Captured Plan: {}",
                plan4);

        softAssert.assertNotNull(
                plan4,
                "Plan 4 details are null");

        logger.info("Navigating to final coverage");

        travelQuotePage.clickNextCoverage();

        logger.info(
                "Selecting lowest coverage for fifth plan");

        String coverage5 =
                travelQuotePage.selectLowestCoverage(2);

        PlanDetails plan5 =
                travelQuotePage.getCurrentPlanDetails(2);

        allPlans.add(plan5);

        logger.info(
                "Captured Plan: {}",
                plan5);

        softAssert.assertNotNull(
                plan5,
                "Plan 5 details are null");

        logger.info(
                "Total plans collected: {}",
                allPlans.size());

        softAssert.assertEquals(
                allPlans.size(),
                5,
                "Expected 5 plans to be collected");

        List<PlanDetails> lowestPlans =
                travelQuotePage.getLowestThreePlans(allPlans);

        logger.info(
                "Lowest premium plans identified. Count: {}",
                lowestPlans.size());

        softAssert.assertEquals(
                lowestPlans.size(),
                3,
                "Lowest premium plan count mismatch");

        logger.info("Printing Lowest 3 Premium Plans");

        for (PlanDetails plan : lowestPlans) {

            logger.info(
                    "Lowest Premium Plan => {}",
                    plan);

            softAssert.assertTrue(
                    travelQuotePage.isPlanDetailValid(plan),
                    "Invalid plan details found");

            softAssert.assertNotNull(
                    plan.getPlanName(),
                    "Plan name is null");

            softAssert.assertNotNull(
                    plan.getPremiumAmount(),
                    "Premium amount is null");

            softAssert.assertNotNull(
                    plan.getMedicalCover(),
                    "Medical cover is null");
        }


        softAssert.assertTrue(
                travelQuotePage.arePlansSortedByPremium(lowestPlans),
                "Lowest plans are not sorted by premium");

        softAssert.assertAll();

        logger.info("All Travel Plans have been retrieved and top 3 lowest plans have been listed and verified successfully");
        logger.info("TC_10 PASSED");
    }
}