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
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TC_10 extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(
                    TC_07_VerifyTravelQuoteSummary.class);

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


        for (int i = 0; i < 3; i++) {

            String selectedCover =
                    travelQuotePage.selectLowestCoverage(i);

            System.out.println(
                    "Selected Lowest Cover : "
                            + selectedCover);
        }

        List<PlanDetails> lowestPlans =
                travelQuotePage.getLowestThreePlans();

        Assert.assertEquals(
                lowestPlans.size(),
                3);

        System.out.println(
                "Lowest 3 Premium Plans");

        for (PlanDetails plan : lowestPlans) {

            System.out.println(plan);
        }

        logger.info("TC_07 PASSED");
    }

}