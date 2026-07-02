package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01NavigateTravelPage extends BaseTest {

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyTravelInsuranceNavigation(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        boolean isOpened = homePage.isHomePageDisplayed();
        homePage.clickTravelInsurance();
        boolean isTravelScopeDisplayed = homePage.isTravelScopeOptionAvailable();
        homePage.clickTravelScope();
        boolean isSelectOtherCountriesDisplayed = homePage.isSelectOtherCountriesOptionAvailable();
        homePage.clickOtherCountries();
        boolean isDisplayed = travelHomePage.isTravelPageDisplayed();
        Assert.assertTrue(isDisplayed, "Travel Insurance Page/Form is NOT displayed");
        Assert.assertTrue(isOpened, "Home Page is NOT displayed Correctly");
        Assert.assertTrue(isTravelScopeDisplayed, "Select Travel Scope NOT displayed");
        Assert.assertTrue(isSelectOtherCountriesDisplayed, "Select Other Countries NOT displayed");
    }
}