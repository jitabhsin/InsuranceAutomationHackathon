package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class TC_04_VerifySelectedCountry extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyAndDisplaySelectedCountry(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();
        boolean isDateSubmitPresent = travelHomePage.isSubmitButtonPresent();

        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);
        boolean isDisplayed = travelHomePage.isSelectTravelTypeVisible();
        boolean isClicked = travelHomePage.isSelectTravelTypeSelected();
        boolean isSelected = travelHomePage.isCountrySelectedCorrectly();

        softAssert.assertTrue(isDisplayed, "Select Country Type not Visible");
        softAssert.assertTrue(isClicked, "Select Country Type has not been Selected");
        softAssert.assertTrue(isSelected, "Wrong Country has been selected");
        softAssert.assertEquals(selectedCountry, ConfigReader.getProperty("country"), "Selected country does not match expected country");
        softAssert.assertAll();

        String termsStatus = travelHomePage.getTermsStatus();
        travelHomePage.selectStartAndEndDateElement.click();
        boolean isCalenderOpen = travelHomePage.isCalenderOpen();

        travelHomePage.selectStartDate(ConfigReader.getProperty("startDate"));
        travelHomePage.selectEndDate(ConfigReader.getProperty("endDate"));
        System.out.println(travelHomePage.retrieveTripDuration());
        travelHomePage.submitDate();

        boolean verifyTravelCountRedirection = travelHomePage.isRedirectedToSelectTravellerCount();

        softAssert.assertEquals(termsStatus, "CHECKED", "Terms checkbox should be unchecked");

        Assert.assertTrue(isCalenderOpen, "Calendar NOT Opened");
        Assert.assertTrue(isDateSubmitPresent, "Date Submit Button NOT Present");
        Assert.assertTrue(verifyTravelCountRedirection, "Traveller Count Selection NOT loaded");

    }

}
