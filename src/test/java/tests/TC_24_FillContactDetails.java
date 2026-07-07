package tests;

import org.testng.Assert;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

public class TC_24_FillContactDetails extends BaseTest {
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        Object[][] data = new ExcelReader().readSheetHealth();
        Object[][] contactDetails = new Object[data.length][4];
        for (int i = 0; i < data.length; i++) {
            contactDetails[i][0] = data[i][3];
            contactDetails[i][1] = data[i][4];
            contactDetails[i][2] = data[i][5];
            contactDetails[i][3] = data[i][6];
        }
        return contactDetails;
    }

    @Test(dataProvider = "healthData")
    public void fillContactDetails(String name, String mobileNo, String email, String pincode){

        healthHomePage = new HealthHomePage(driver);

        Assert.assertTrue(healthHomePage.isContactDetailsDisplayed(), "Contact tab is not displayed");
        logger.info("Contact detail button is displayed");

        healthHomePage.clickContactDetails();
        logger.info("Contact detail tab clicked");
        healthHomePage.enterMobileNo(mobileNo);
        logger.info("Mobile number entered");
        healthHomePage.enterEmailId(email);
        logger.info("Email entered");
        healthHomePage.enterPincode(pincode);
        logger.info("Pincode entered");
        healthHomePage.enterName(name);
        logger.info("Name entered");

        Assert.assertTrue(healthHomePage.isDoneButtonDisplayed(), "Done Button is not displayed");
        logger.info("Done button is displayed");
        healthHomePage.clickDoneButton();
        logger.info("Done button clicked");
        healthHomePage.clickGetQuote();
        logger.info("Get quote button clicked");
    }
}
