package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;
import utils.ExcelReader;

public class TC_03_SelectDates extends BaseTest {

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void selectDates(){

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();
        boolean isDateSubmitPresent = travelHomePage.isSubmitButtonPresent();

        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        travelHomePage.selectStartAndEndDateElement.click();
        boolean isCalenderOpen = travelHomePage.isCalenderOpen();

        travelHomePage.selectStartDate(ConfigReader.getProperty("startDate"));
        travelHomePage.selectEndDate(ConfigReader.getProperty("endDate"));
        System.out.println(travelHomePage.retrieveTripDuration());
        travelHomePage.submitDate();

        boolean verifyTravelCountRedirection = travelHomePage.isRedirectedToSelectTravellerCount();

        Assert.assertTrue(isCalenderOpen, "Calendar NOT Opened");
        Assert.assertTrue(isDateSubmitPresent, "Date Submit Button NOT Present");

        String termsStatus = travelHomePage.getTermsStatus();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(termsStatus, "UNCHECKED", "Terms checkbox should be unchecked");

        Assert.assertTrue(verifyTravelCountRedirection, "Traveller Count Selection NOT loaded");
    }
}
