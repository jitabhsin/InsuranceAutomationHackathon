package tests;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.TravelHomePage;
import org.testng.annotations.Test;

public class TC_03_SelectDates extends BaseTest {

    String startDate = "Jul 3, 2026";
    String endDate = "Jul 8, 2026";
    HomePage homePage;
    TravelHomePage travelHomePage;

    @Test
    public void selectDates(){
        homePage = new HomePage(driver);
        travelHomePage = new TravelHomePage(driver);
        homePage.clickTravelInsurance();
        travelHomePage.selectCountry("United Kingdom");
        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);
        travelHomePage.selectDateElement.click();
        travelHomePage.selectStartDate(startDate);
        travelHomePage.selectEndDate(endDate);
        travelHomePage.dateSubmitButton.click();
    }
}
