package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
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

import java.util.List;

public class TC_09_VerifyFilteringForMedicalCovers extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_09_VerifyFilteringForMedicalCovers.class);

    HomePage homePage;
    TravelHomePage travelHomePage;
    TravelQuotePage travelQuotePage;

    @Test
    public void verifyTravelMedicalCoverFiltering() {

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetTravelFirstRow();

        String country = data[0].toString();
        String startDate = data[1].toString();
        String endDate = data[2].toString();
        String travellerCount = data[3].toString();
        String travellerAges = data[4].toString();

        logger.info("TC_07 - Verify Travel Quote Summary Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        travelQuotePage = new TravelQuotePage(driver);

        Assert.assertFalse(country.trim().isEmpty(), "Country is empty");

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        Assert.assertTrue(driver.getCurrentUrl().contains("travel-insurance"), "Travel Insurance page not loaded");

        travelHomePage.selectCountry(country);
        travelHomePage.selectStartAndEndDateElement.click();
        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        travelHomePage.submitDate();

        Assert.assertTrue(travelHomePage.isRedirectedToSelectTravellerCount(), "Traveller Details page not loaded");

        String contactNo = ConfigReader.getProperty("contactNum");
        String email = ConfigReader.getProperty("email");

        travelHomePage.contactNumber.sendKeys(contactNo);
        travelHomePage.email.sendKeys(email);

        int travellerCnt = Integer.parseInt(travellerCount);
        String[] ageStrings = travellerAges.split(",");

        int[] ages = new int[ageStrings.length];

        for (int i = 0; i < ageStrings.length; i++) {
            ages[i] = Integer.parseInt(ageStrings[i].trim());
        }

        travelHomePage.selectTravellerCount(travellerCnt, ages);
        travelHomePage.noHealthCheckBox.click();

        travelHomePage.travellerSubmitButton.click();

        travelQuotePage.waitForPage();

        logger.info("Travel Quote Page Loaded Successfully");

        Assert.assertTrue(
                travelQuotePage.isTravelQuotePageLoaded(),
                "Travel Quote Page not loaded");

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

        for (int i = 0; i < 3; i++) {

            logger.info(
                    "Validating Medical Cover Dropdown {}",
                    i + 1);

            travelQuotePage.clickMedicalCoverDropdown(i);

            List<String> values =
                    travelQuotePage.getDropdownValues();

            logger.info(
                    "Dropdown {} contains {} values",
                    i + 1,
                    values.size());

            softAssert.assertTrue(
                    values.size() >= 3,
                    "Dropdown " + (i + 1)
                            + " contains less than 3 options");

            softAssert.assertFalse(
                    values.isEmpty(),
                    "Dropdown " + (i + 1)
                            + " has no values");

            softAssert.assertTrue(
                    travelQuotePage.areDropdownValuesUnique(values),
                    "Dropdown " + (i + 1)
                            + " contains duplicate values");

            softAssert.assertTrue(
                    travelQuotePage.hasValidDropdownValues(values),
                    "Invalid values found in Dropdown "
                            + (i + 1));

            logger.info(
                    "Values present in Dropdown {}",
                    i + 1);

            for (String value : values) {

                logger.info(value);

                softAssert.assertFalse(
                        value.trim().isEmpty(),
                        "Blank value found in Dropdown "
                                + (i + 1));
            }

            logger.info(
                    "Dropdown {} validation completed",
                    i + 1);
        }

        logger.info("All medical cover dropdown validations completed in the displayed page is completed");

        softAssert.assertAll();

        logger.info("All Filters have been selected and verified successfully");
        logger.info("TC_09 PASSED");
    }
}