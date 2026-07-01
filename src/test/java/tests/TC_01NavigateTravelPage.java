package tests;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01NavigateTravelPage extends BaseTest {

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyTravelInsuranceNavigation(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        homePage.clickTravelInsurance();
        boolean isDisplayed = travelHomePage.isTravelPageDisplayed();

        Assert.assertTrue(isDisplayed, "Travel Insurance Page/Form is NOT displayed");
    }
}