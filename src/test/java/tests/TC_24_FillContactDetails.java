package tests;

import org.testng.Assert;
import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class TC_24_FillContactDetails extends BaseTest {
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        return new ExcelReader().readSheetHealth();
    }

    @Test(dataProvider = "healthData")
    public void fillContactDetails(String name, String mobileNo, String email, String pincode, String dob){

        healthHomePage = new HealthHomePage(driver);
        healthHomePage.clickHealthTab();

        Assert.assertTrue(healthHomePage.isContactDetailsDisplayed(), "Contact tab is not displayed");
        System.out.println("Contact detail button is displayed");

        healthHomePage.clickContactDetails();
        healthHomePage.enterMobileNo(mobileNo);
        healthHomePage.enterEmailId(email);
        healthHomePage.enterPincode(pincode);
        healthHomePage.enterName(name);

        Assert.assertTrue(healthHomePage.isDoneButtonDisplayed(), "Done Button is not displayed");
        System.out.println("Done button is displayed");

        healthHomePage.clickDoneButton();
        healthHomePage.clearContactDetails();
    }

}
