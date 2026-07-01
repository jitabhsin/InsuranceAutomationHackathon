package tests;

import basetest.BaseTest;
import org.policy.pages.TravelQuotePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_06_VerifyTotalPlan extends BaseTest {

    @Test
    public void testTotalPlan(){
        TravelQuotePage totalPlan = new TravelQuotePage(driver);
        String total = totalPlan.totalTravelPlan();

        Assert.assertEquals(total, "Showing 16 plans for 2 members");
    }
}
