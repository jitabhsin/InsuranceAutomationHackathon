package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_01_NavigateTravelPage extends BaseTest {

    private static final Logger logger = LogManager.getLogger(TC_01_NavigateTravelPage.class);

    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyTravelInsuranceNavigation() {

        logger.info("TC_01 - Verify Travel Insurance Navigation Started");

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        logger.info("Verifying Home Page is displayed");
        Assert.assertTrue(homePage.isHomePageDisplayed(),"Home Page is NOT displayed correctly");

        logger.info("Clicking Travel Insurance");
        homePage.clickTravelInsurance();

        logger.info("Verifying Travel Scope option is available");
        Assert.assertTrue(homePage.isTravelScopeOptionAvailable(), "Travel Scope option is NOT displayed");

        logger.info("Selecting Travel Scope");
        homePage.clickTravelScope();

        logger.info("Verifying Other Countries option is available");
        Assert.assertTrue(homePage.isSelectOtherCountriesOptionAvailable(),"Select Other Countries option is NOT displayed");

        logger.info("Selecting Other Countries");
        homePage.clickOtherCountries();

        logger.info("Verifying Travel Insurance page is displayed");
        Assert.assertTrue(travelHomePage.isTravelPageDisplayed(),"Travel Insurance Page/Form is NOT displayed");

        logger.info("User successfully navigated to Travel Insurance page");

        logger.info("TC_01 PASSED");
    }
}