package tests;

import basetest.BaseTest;
import org.policy.pages.HealthHomePage;
import org.policy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_21NavigateToHomePage extends BaseTest {

    @Test
    public void verifyHealthInsuranceMenuVisible() {
        HomePage homePage = new HomePage(driver);
        HealthHomePage healthHomePage = new HealthHomePage(driver);

        boolean isMenuVisible = healthHomePage.verifyHealthMenuVisible(homePage);

        Assert.assertTrue(isMenuVisible,
                "TC_21 Failed: Health Insurance menu is NOT visible on the homepage.");
    }
}