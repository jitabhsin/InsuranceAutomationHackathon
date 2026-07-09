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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_09_VerifyFilteringForMedicalCovers extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_09_VerifyFilteringForMedicalCovers.class);

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
    public void verifyTravelMedicalCoverFiltering(String country, String startDate, String endDate, String travellerCount, String travellerAges) {

        logger.info("TC_09 - Verify Travel Quote Filtering Started");

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

        travelQuotePage.scrollIntoMedicalFilterView();
        logger.info("Scrolled to Medical Cover section");

        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i < 3; i++) {
            logger.info("Validating Medical Cover Dropdown {}", i + 1);
            travelQuotePage.clickMedicalCoverDropdown(i);

            ScreenshotUtils.captureScreenshot(driver, "TC_09_VerifyFilteringForMedicalCovers");
            logger.info("Travel Quotes Medical Filters Dropdown Screenshot taken");

            List<String> values = travelQuotePage.getDropdownValues();
            logger.info("Dropdown {} contains {} values", i + 1, values.size());

            softAssert.assertTrue(values.size() >= 3, "Dropdown " + (i + 1) + " contains less than 3 options");
            softAssert.assertFalse(values.isEmpty(), "Dropdown " + (i + 1) + " has no values");
            softAssert.assertTrue(travelQuotePage.areDropdownValuesUnique(values), "Dropdown " + (i + 1) + " contains duplicate values");
            softAssert.assertTrue(travelQuotePage.hasValidDropdownValues(values), "Invalid values found in Dropdown " + (i + 1));

            logger.info("Values present in Dropdown {}", i + 1);

            for (String value : values) {
                logger.info(value);
                softAssert.assertFalse(value.trim().isEmpty(), "Blank value found in Dropdown " + (i + 1));
            }

            logger.info("Dropdown {} validation completed", i + 1);
        }

        logger.info("All medical cover dropdown validations completed in the displayed page is completed");

        softAssert.assertAll();

        logger.info("All Filters have been selected and verified successfully");
        logger.info("TC_09 PASSED");
    }
}