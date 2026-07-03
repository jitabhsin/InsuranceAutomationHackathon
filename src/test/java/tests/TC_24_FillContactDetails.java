package tests;

import org.testng.Assert;
import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.annotations.Test;

public class TC_24_FillContactDetails extends BaseTest {
    HealthHomePage healthHomePage;

    @Test
    public void fillContactDetails(){
        healthHomePage = new HealthHomePage(driver);
        healthHomePage.clickHealthTab();

        Assert.assertTrue(healthHomePage.isContactDetailsDisplayed(), "Contact tab is not displayed");

        healthHomePage.clickContactDetails();
        healthHomePage.enterMobileNo("9876543210");
        healthHomePage.enterEmailId("abc@xyz.com");
        healthHomePage.enterPincode("628008");
        healthHomePage.enterName("John");
        healthHomePage.clickDoneButton();

    }


}
