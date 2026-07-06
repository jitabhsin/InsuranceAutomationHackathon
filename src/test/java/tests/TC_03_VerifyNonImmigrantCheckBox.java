package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.insurance.utils.ConfigReader;

public class TC_03_VerifyNonImmigrantCheckBox extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyNonImmigrantCheckBox(){
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

        travelHomePage.selectStartAndEndDateElement.click();
        boolean isCalenderOpen = travelHomePage.isCalenderOpen();

        Assert.assertTrue(isCalenderOpen, "Calendar NOT Opened");

        travelHomePage.selectStartDate(ConfigReader.getProperty("startDate"));
        travelHomePage.selectEndDate(ConfigReader.getProperty("endDate"));
        System.out.println(travelHomePage.retrieveTripDuration());

        travelHomePage.selectNonImmigrantCheckBox();
        travelHomePage.retrieveAlertMessage();

        boolean isAlertFrameOpened = travelHomePage.attentionFrameDisplayed();
        boolean isOkButtonPresent = travelHomePage.verifyOkButtonPresence();
        travelHomePage.okButton.click();
        boolean isEndDateOptionAvailable = travelHomePage.verifyEndDateOptionAvailableAgain();

        softAssert.assertTrue(isAlertFrameOpened, "Attention Frame NOT opened and displayed");
        softAssert.assertTrue(isOkButtonPresent, "Ok Button NOT displayed");
        softAssert.assertTrue(isEndDateOptionAvailable, "End Date Option NOT displayed");
        softAssert.assertAll();
    }

}
