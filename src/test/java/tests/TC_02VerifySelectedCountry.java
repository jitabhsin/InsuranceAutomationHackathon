package tests;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.TravelHomePage;
import org.policy.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_02VerifySelectedCountry extends BaseTest {

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyAndDisplaySelectedCountry(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        homePage.clickTravelInsurance();
        travelHomePage.selectCountry("United Kingdom");

        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        Assert.assertEquals(selectedCountry, "United Kingdom");
    }

}
