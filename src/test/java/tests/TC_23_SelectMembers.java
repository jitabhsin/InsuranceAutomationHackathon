package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

public class TC_23_SelectMembers extends BaseTest {
    HealthHomePage healthPage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        Object[][] data = new ExcelReader().readSheetHealth();
        Object[][] memberDob = new Object[data.length][2];
        for (int i = 0; i < data.length; i++) {
            memberDob[i][0] = data[i][1];
            memberDob[i][1] = data[i][2];
        }
        return memberDob;
    }

    @Test(dataProvider = "healthData")
    public void testAddMembers(String member, String dob){
        healthPage = new HealthHomePage(driver);

        healthPage.clickMemberBtn();
        logger.info("Click member button");

        healthPage.addMembers(member, dob);
        logger.info("Member data entered");

        boolean verifyClickDoneBtn = healthPage.clickDoneBtn();
        Assert.assertTrue(verifyClickDoneBtn, "Done button not clicked");
        logger.info("Done button click");

        Assert.assertEquals(healthPage.verifyMembersResult.getText(), "1 Adult(s), 0 Kid(s)");
        logger.info("Members details are filled successfully");
    }
}
