package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class TC_04_SelectTravellers extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    String startDate = "Jul 3, 2026";
    String endDate = "Jul 8, 2026";

    int count = 2;
    int age1 = 22;
    int age2 = 21;

    @Test
    public void selectTravellers(){
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

        travelHomePage.selectEndDateElement.click();
        travelHomePage.selectEndDate(endDate);

        travelHomePage.submitDate();

        travelHomePage.selectTravellerCount(count, age1, age2);

    }
}
