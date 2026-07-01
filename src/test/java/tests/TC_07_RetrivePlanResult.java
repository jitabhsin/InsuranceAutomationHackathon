package tests;

import basetest.BaseTest;
import org.openqa.selenium.WebElement;
import org.policy.pages.TravelQuotePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_07_RetrivePlanResult extends BaseTest {

    @Test
    public void testRetrivePlans(){
        TravelQuotePage retrievePlans = new TravelQuotePage(driver);

        List<WebElement> plans = retrievePlans.retrieveTotalPlans();

        Assert.assertEquals(plans.size(), 16);
    }
}
