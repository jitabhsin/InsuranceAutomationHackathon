package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_21HealthButtonChecking extends BaseTest {
    @Test
    public void verifyHealthTabButtonsVisibilityAndWorking() {
        HealthHomePage healthHomePage = new HealthHomePage(getDriver());

        healthHomePage.clickHealthTab();
        Assert.assertTrue(healthHomePage.isHealthTabDisplayed(), "Health tab is not displayed");
    }
}
