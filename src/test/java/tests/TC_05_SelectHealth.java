package tests;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.TravelHomePage;
import org.insurance.pages.TravelQuotePage;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_05_SelectHealth extends BaseTest {
    HomePage homePage;
    TravelHomePage travelHomePage;

    String startDate = "Jul 8, 2026";
    String endDate = "Aug 8, 2026";

    int count = 2;
    int age1 = 22;
    int age2 = 21;
    String diabetesCheck = "No";

    @Test
    public void selectTravellersHealth(){
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

        travelHomePage.selectHealthOfTravellers(diabetesCheck);

        travelHomePage.ageSubmitButton.click();

        travelHomePage.submitButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        TravelQuotePage totalPlan = new TravelQuotePage(driver);
        String total = totalPlan.totalTravelPlan();
    }
}
