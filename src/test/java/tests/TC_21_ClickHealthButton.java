package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_21_ClickHealthButton extends BaseTest {
    HealthHomePage healthHomePage;
    @Test
    public void verifyHealthTabButtonsVisibilityAndWorking() {
        healthHomePage = new HealthHomePage(getDriver());

        healthHomePage.clickHealthTab();
        Assert.assertEquals(healthHomePage.isSelectProductDisplayed(), "Select products");
        System.out.println("Product selected successfully");
    }
}
