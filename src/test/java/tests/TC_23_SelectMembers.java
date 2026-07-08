package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

public class TC_23_SelectMembers extends BaseTest {
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        Object[][] data = new ExcelReader().readSheetHealth();
        Object[][] memberDob = new Object[data.length][3];
        for (int i = 0; i < data.length; i++) {
            memberDob[i][0] = data[i][0];
            memberDob[i][1] = data[i][1];
            memberDob[i][2] = data[i][2];
        }
        return memberDob;
    }

    @Test(dataProvider = "healthData")
    public void testAddMembers(String product, String member, String dob){
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

        Assert.assertEquals(healthHomePage.verifyMembersResult.getText(), (healthHomePage.adultCount-1) + " Adult(s), " +  (healthHomePage.kidsCount-1) + " Kid(s)");
        logger.info("Members details are filled successfully");
    }
}
