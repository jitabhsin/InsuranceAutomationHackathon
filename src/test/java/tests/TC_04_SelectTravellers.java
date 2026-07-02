package tests;

import basetest.BaseTest;
import io.cucumber.java.en_old.Tha;
import org.policy.pages.HomePage;
import org.policy.pages.TravelHomePage;
import org.testng.annotations.Test;

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
        travelHomePage.selectCountry("United Kingdom");

        String selectedCountry = travelHomePage.getSelectedCountry();
        System.out.println("Selected Country: " + selectedCountry);

        travelHomePage.selectDateElement.click();

        travelHomePage.selectStartDate(startDate);

        travelHomePage.selectEndDate(endDate);

        travelHomePage.dateSubmitButton.click();

        travelHomePage.selectTravellerCount(count, age1, age2);

    }
}
