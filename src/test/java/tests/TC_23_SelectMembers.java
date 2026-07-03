package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_23_SelectMembers extends BaseTest {
    HealthHomePage healthPage;

    @Test
    public void testAddMembers(){
        healthPage = new HealthHomePage(driver);

        healthPage.clickHealthTab();
        healthPage.clickProductDropdwn();
        String actual = healthPage.selectProduct("Activate Booster");
        healthPage.clickMemberBtn();
        healthPage.addMembers("Adult", "12-07-2004");
        healthPage.addMembers("Kids", "21-07-2020");
        boolean verifyClickDoneBtn = healthPage.clickDoneBtn();

        Assert.assertTrue(verifyClickDoneBtn, "Done button clicked");
        Assert.assertEquals(healthPage.verifyMembersResult.getText(), "1 Adult(s), 1 Kid(s)");
        System.out.println("Members details are filled successfully");
    }
}
