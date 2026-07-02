package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
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
        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);
        boolean isDisplayed = travelHomePage.isSelectTravelTypeVisible();
        boolean isClicked = travelHomePage.isSelectTravelTypeSelected();
        boolean isSelected = travelHomePage.isCountrySelectedCorrectly();

        Assert.assertTrue(isDisplayed, "Select Country Type not Visible");
        Assert.assertTrue(isClicked, "Select Country Type has not been Selected");
        Assert.assertTrue(isSelected, "Wrong Country has been selected");
        Assert.assertEquals(selectedCountry, ConfigReader.getProperty("country"));
    }

}
