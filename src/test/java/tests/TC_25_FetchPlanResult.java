package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_25_FetchPlanResult extends BaseTest {
    HealthResultPage healthResultPage;

    @Test
    public void testFetchPlanResult(){
        healthResultPage = new HealthResultPage(driver);

        int total = healthResultPage.resultPlans();
        Assert.assertEquals(total, 3);
        System.out.println("Total plans: " + total);
    }
}
