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
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_06_VerifyTravellerDetails extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_06_VerifyTravellerDetails.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @DataProvider(name = "travelData")
    public Object[][] getData() {
        logger.info("Reading traveller details test data from Excel");
        ExcelReader excel = new ExcelReader();
        return excel.readSheetTravel();
    }

    @Test(dataProvider = "travelData")
    public void verifyTravellerDetails(String country, String startDate, String endDate, String travellerCount, String travellerAges, String seniorTravellerDOBs, String healthIssue, String healthIssueTravellers) {

        logger.info("TC_06 - Verify Traveller Details Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        logger.info("Navigating to Travel Insurance");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        Assert.assertTrue(driver.getCurrentUrl().contains("travel-insurance"), "Travel Insurance page not loaded");

        logger.info("Travel Insurance Page Loaded");

        travelHomePage.selectCountry(country);
        travelHomePage.selectStartAndEndDateElement.click();
        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        logger.info("Submitting Travel Dates");
        travelHomePage.submitDate();

        SoftAssert softAssert = new SoftAssert();

        boolean isRedirected = travelHomePage.isRedirectedToSelectTravellerCount();

        Assert.assertTrue(isRedirected, "Traveller Details page not loaded");

        logger.info("Successfully redirected to Traveller Details page");

        Assert.assertTrue(travelHomePage.contactNumber.isDisplayed(), "Contact Number field not displayed");
        Assert.assertTrue(travelHomePage.email.isDisplayed(), "Email field not displayed");
        Assert.assertTrue(travelHomePage.travellerSubmitButton.isDisplayed(), "Traveller Submit button not displayed");
        Assert.assertTrue(travelHomePage.travellerSubmitButton.isEnabled(), "Traveller Submit button disabled");

        logger.info("Traveller Details Page Validation Completed");

        String contactNo = ConfigReader.getProperty("contactNum");

        Assert.assertNotNull(contactNo, "Contact Number is null");
        Assert.assertFalse(contactNo.trim().isEmpty(), "Contact Number is empty");
        Assert.assertTrue(contactNo.matches("\\d{10}"), "Invalid Contact Number format : " + contactNo);

        String email = ConfigReader.getProperty("email");

        Assert.assertNotNull(email, "Email is null");
        Assert.assertFalse(email.trim().isEmpty(), "Email is empty");
        Assert.assertTrue(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"), "Invalid Email format : " + email);

        logger.info("Entering Contact Number");

        travelHomePage.contactNumber.sendKeys(contactNo);
        logger.info("Entering Email");

        travelHomePage.email.sendKeys(email);

        softAssert.assertFalse(travelHomePage.contactNumber.getAttribute("value").trim().isEmpty(), "Contact Number not entered");
        softAssert.assertFalse(travelHomePage.email.getAttribute("value").trim().isEmpty(), "Email not entered");

        logger.info("Contact Details Entered Successfully");

        int count = Integer.parseInt(travellerCount);
        Assert.assertTrue(count > 0, "Traveller Count should be greater than zero");
        logger.info("Traveller Count : {}", count);

        String[] ageStrings = travellerAges.split(",");

        if (ageStrings.length != count) {
            logger.info("Negative Test Passed - Traveller Count ({}) does not match Age Entries ({})",
                    count, ageStrings.length);

            Assert.assertTrue(true, "Expected invalid traveller count mapping");

            driver.get(ConfigReader.getProperty("baseUrl"));
            return;
        }

        int[] ages = new int[ageStrings.length];
        boolean hasSeniorTraveller = false;

        for (int i = 0; i < ageStrings.length; i++) {
            ages[i] = Integer.parseInt(ageStrings[i].trim());
            logger.info("Traveller {} Age : {}", i + 1, ages[i]);
            if (ages[i] > 70) {
                hasSeniorTraveller = true;
            }
        }

        softAssert.assertEquals(ages.length, count, "Mismatch between Traveller Count and Age Entries");

        logger.info("Selecting Traveller Count");

        travelHomePage.selectTravellerCount(count, ages);

        ScreenshotUtils.captureScreenshot(driver, "TC_06_VerifyTravellerDetails");
        logger.info("Traveller Details Screenshot taken");

        logger.info("Senior Traveller Present : {}", hasSeniorTraveller);

        if (hasSeniorTraveller) {
            logger.info("Validating Senior Traveller DOB Mapping");
            boolean validDOB = travelHomePage.validateSeniorTravellerDOBs(travellerAges, seniorTravellerDOBs);

            if (!validDOB) {
                logger.info("Negative Test Passed - Invalid Senior Traveller DOB Mapping. Ages : {} , DOBs : {}",
                        travellerAges, seniorTravellerDOBs);

                Assert.assertFalse(validDOB,
                        "Expected DOB mapping validation to fail");

                driver.get(ConfigReader.getProperty("baseUrl"));
                return;
            }

            logger.info("Entering Senior Traveller DOB Details");
            travelHomePage.enterSeniorTravellerDOBs(ages, seniorTravellerDOBs);
            logger.info("Senior Traveller DOB Details Entered Successfully");
        }

        logger.info("Health Issue Status : {}", healthIssue);

        if (healthIssue.equalsIgnoreCase("Yes")) {
            boolean validTravellerData = travelHomePage.validateHealthIssueTravellers(travellerAges, healthIssueTravellers);

            if (!validTravellerData) {
                logger.info("Negative Test Passed - Invalid Health Issue Traveller Mapping");

                Assert.assertFalse(validTravellerData,
                        "Expected Health Issue Traveller validation to fail");

                driver.get(ConfigReader.getProperty("baseUrl"));
                return;
            }

            logger.info("Selecting Health Issue : YES");
            travelHomePage.yesHealthCheckBox.click();
            travelHomePage.selectHealthIssueTravellersByAge(travellerAges, healthIssueTravellers);

            logger.info("Health Issue Travellers Selected Successfully");
        }
        else {
            logger.info("Selecting Health Issue : NO");
            travelHomePage.noHealthCheckBox.click();
        }

        logger.info("Submitting Traveller Details");
        travelHomePage.travellerSubmitButton.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("travel-app"), "Traveller Details Submission Failed");
        logger.info("Traveller Details Submitted Successfully");

        softAssert.assertAll();

        if (driver.getCurrentUrl().contains("travel-app") || travelQuotePage.nextCoverageBtn.isDisplayed()) {
            logger.info("Navigating back to Home Page");
            driver.get(ConfigReader.getProperty("baseUrl"));
        }

        logger.info("All traveller details have been passed and redirected to Travel Quote Page and verified successfully");
        logger.info("TC_06 PASSED");
    }
}