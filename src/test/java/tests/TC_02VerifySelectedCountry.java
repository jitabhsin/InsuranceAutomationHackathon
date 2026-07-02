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
        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        Assert.assertEquals(selectedCountry, "United Kingdom");
    }

}
