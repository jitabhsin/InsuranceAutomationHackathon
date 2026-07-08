package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.insurance.pages.HealthResultPage;
import org.insurance.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_25_FetchPlanResult extends BaseTest {
    HealthResultPage healthResultPage;
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        return new ExcelReader().readSheetHealth();
    }

    @Test(dataProvider = "healthData")
    public void testFetchPlanResult(String product, String member, String dob, String name, String mobileNo, String email, String pincode){
        healthResultPage = new HealthResultPage(driver);
        healthHomePage = new HealthHomePage(driver);

        healthHomePage.clickHealthTab();
        logger.info("Health tab clicked");
        Assert.assertEquals(healthHomePage.isSelectProductTextDisplayed(), "Select products");
        Assert.assertEquals(healthHomePage.isInsureMembersTextDisplayed(), "Insure members");
        Assert.assertEquals(healthHomePage.isContactDetailsTextDisplayed(), "Contact details");

        int totalProduct = healthHomePage.clickProductDropdwn();
        logger.info("Click product button");

        Assert.assertEquals(totalProduct, 3);
        String actual = healthHomePage.selectProduct(product);
        logger.info("Product selected");

        Assert.assertEquals(actual, product);
        logger.info(product + " selected");

        healthHomePage.clickMemberBtn();
        logger.info("Click member button");

        healthHomePage.addMembers(member, dob);
        logger.info("Member data entered");

        boolean verifyClickDoneBtn = healthHomePage.clickDoneBtn();
        Assert.assertTrue(verifyClickDoneBtn, "Done button not clicked");
        logger.info("Done button click");

        Assert.assertEquals(healthHomePage.verifyMembersResult.getText(), (healthHomePage.adultCount-1) + " Adult(s), " +  (healthHomePage.kidsCount-1) + " Kid(s)");        logger.info("Members details are filled successfully");

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

        String currUrl = driver.getCurrentUrl();
        String title = driver.getTitle();
        logger.info("Current URL: " + currUrl);
        logger.info("Page title: " + title);

        Assert.assertEquals(title, "Elevate App");
        Assert.assertTrue(currUrl.contains("plan-page"), "Plan page is not opened");

        int total = healthResultPage.resultPlans();
        Assert.assertEquals(total, 3);
        logger.info("Total plans: " + total);
    }
}
