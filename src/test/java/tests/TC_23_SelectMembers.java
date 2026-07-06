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
        return new ExcelReader().readSheetHealth();
    }

    @Test(dataProvider = "healthData")
    public void testAddMembers(String name, String mobileNo, String email, String pincode, String product, String member, String dob){
        healthPage = new HealthHomePage(driver);

        healthPage.clickMemberBtn();
        healthPage.addMembers(member, "12-07-2002");
        boolean verifyClickDoneBtn = healthPage.clickDoneBtn();

        Assert.assertTrue(verifyClickDoneBtn, "Done button clicked");
        Assert.assertEquals(healthPage.verifyMembersResult.getText(), "1 Adult(s), 0 Kid(s)");
        System.out.println("Members details are filled successfully");
    }
}
