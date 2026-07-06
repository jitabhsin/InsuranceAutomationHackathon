package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_25_FetchPlanResult extends BaseTest {
    HealthResultPage healthResultPage;

    @Test
    public void testFetchPlanResult(){
        healthResultPage = new HealthResultPage(driver);
        String currUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        Assert.assertEquals(title, "Elevate App");
        Assert.assertTrue(currUrl.contains("plan-page"), "Plan page is not opened");

        int total = healthResultPage.resultPlans();
        Assert.assertEquals(total, 3);
        System.out.println("Total plans: " + total);
    }
}
