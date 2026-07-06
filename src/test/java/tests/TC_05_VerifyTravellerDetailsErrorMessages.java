package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.insurance.utils.ConfigReader;

import java.util.List;

public class TC_05_VerifyTravellerDetailsErrorMessages extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyErrorMessagesWithNoInput(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);

        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        travelHomePage.selectStartAndEndDateElement.click();

        travelHomePage.selectStartDate(ConfigReader.getProperty("startDate"));
        travelHomePage.selectEndDate(ConfigReader.getProperty("endDate"));
        travelHomePage.submitDate();

        travelHomePage.travellerSubmitButton.click();

        String expectedMobileError = "Please enter valid mobile number";
        String expectedEmailError = "Please enter valid email ID";
        String expectedHealthError = "Please select atleast one member";

        String actualMobileError = travelHomePage.retrieveMobileError();
        String actualEmailError = travelHomePage.retrieveEmailError();
        String actualHealthError = travelHomePage.retrieveHealthError();

        List<String> listOfErrors = travelHomePage.listAllTravellerErrorMessages();
        System.out.println("Error Messages: ");
        for(String str : listOfErrors){
            System.out.println(str);
        }

        boolean isErrorsDisplayed = travelHomePage.travellerErrorMessagesDisplayed();
        Assert.assertTrue(isErrorsDisplayed, "Errors NOT displayed");
        Assert.assertNull(travelHomePage.verifyNoNumberSelected(),"Number Selected in the TextBox");
        Assert.assertNull(travelHomePage.verifyNoEmailSelected(),"Email Selected in the TextBox");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMobileError, expectedMobileError);
        softAssert.assertEquals(actualEmailError, expectedEmailError);
        softAssert.assertEquals(actualHealthError, expectedHealthError);
        softAssert.assertAll();
    }
}
