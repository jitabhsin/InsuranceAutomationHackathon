package tests;

import basetest.BaseTest;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_002WithoutInputs extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void verifyErrorMessagesWithNoInput(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();

        travelHomePage.dateSubmitButton.click();

        String expectedCountryError = "Please enter the countries you are travelling";
        String expectedStartDateError = "Please select trip start date";
        String expectedEndDateError = "Please select trip end date";

        String actualCountryError = travelHomePage.retrieveCountryError();
        String actualStartDateError = travelHomePage.retrieveStartDateError();
        String actualEndDateError = travelHomePage.retrieveEndDateError();

        List<String> listOfErrors = travelHomePage.listAllErrorMessages();
        System.out.println("Error Messages: ");
        for(String str : listOfErrors){
            System.out.println(str);
        }

        boolean isErrorsDisplayed = travelHomePage.countryErrorMessagesDisplayed();
        Assert.assertTrue(isErrorsDisplayed, "Errors NOT displayed");
        Assert.assertNull(travelHomePage.verifyNoCountrySelected(),"Country Selected in the TextBox");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualCountryError, expectedCountryError);
        softAssert.assertEquals(actualStartDateError, expectedStartDateError);
        softAssert.assertEquals(actualEndDateError, expectedEndDateError);
        softAssert.assertAll();
    }
}
