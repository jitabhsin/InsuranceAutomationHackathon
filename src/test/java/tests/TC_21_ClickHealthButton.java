package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_21_ClickHealthButton extends BaseTest {
    HealthHomePage healthHomePage;
    @Test
    public void verifyHealthTabButtonsVisibilityAndWorking() {
        healthHomePage = new HealthHomePage(getDriver());

        healthHomePage.clickHealthTab();
        logger.info("Health tab clicked");
        Assert.assertEquals(healthHomePage.isSelectProductTextDisplayed(), "Select products");
        Assert.assertEquals(healthHomePage.isInsureMembersTextDisplayed(), "Insure members");
        Assert.assertEquals(healthHomePage.isContactDetailsTextDisplayed(), "Contact details");
    }
}
