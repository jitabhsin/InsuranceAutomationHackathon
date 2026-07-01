package tests;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.TravelHomePage;
import org.testng.annotations.Test;

public class TC_03_SelectDates extends BaseTest {

    String startDate = "Jul 1, 2026";
    String endDate = "Jul 8, 2026";

    @Test
    public void selectDates(){
        HomePage homePage = new HomePage(driver);
        TravelHomePage travelHome = new TravelHomePage(driver);

        homePage.clickTravelInsurance();
        travelHome.selectCountry("United Kingdom");

        String selectedCountry = travelHome.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        travelHome.selectDateElement.click();

        travelHome.selectStartDate(startDate);

        travelHome.selectEndDate(endDate);

        travelHome.dateSubmitButton.click();
    }
}
