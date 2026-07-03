package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class TC_02VerifySelectedCountry extends BaseTest {

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyAndDisplaySelectedCountry(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);
        boolean isDisplayed = travelHomePage.isSelectTravelTypeVisible();
        boolean isClicked = travelHomePage.isSelectTravelTypeSelected();
        boolean isSelected = travelHomePage.isCountrySelectedCorrectly();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed, "Select Country Type not Visible");
        softAssert.assertTrue(isClicked, "Select Country Type has not been Selected");
        softAssert.assertTrue(isSelected, "Wrong Country has been selected");
        softAssert.assertEquals(selectedCountry, ConfigReader.getProperty("country"), "Selected country does not match expected country");
        softAssert.assertAll();

    }

}
