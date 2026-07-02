package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class TC_03_SelectDates extends BaseTest {

    String startDate = "5, Sept 2026";
    String endDate = "30, Nov 2026";
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void selectDates(){

        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        homePage.clickTravelInsurance();
        homePage.clickTravelScope();
        homePage.clickOtherCountries();
        travelHomePage.selectCountry(ConfigReader.getProperty("country"));
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        travelHomePage.selectStartDateElement.click();
        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);

        travelHomePage.submitDate();
    }
}
