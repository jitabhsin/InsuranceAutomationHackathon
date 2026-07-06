package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_01_NavigateTravelPage extends BaseTest {

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

        Assert.assertTrue(isOpened,"Home Page is NOT displayed Correctly");
        Assert.assertTrue(isTravelScopeDisplayed, "Select Travel Scope NOT displayed");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isSelectOtherCountriesDisplayed, "Select Other Countries NOT displayed");
        softAssert.assertTrue(isDisplayed, "Travel Insurance Page/Form is NOT displayed");
        softAssert.assertAll();
    }
}