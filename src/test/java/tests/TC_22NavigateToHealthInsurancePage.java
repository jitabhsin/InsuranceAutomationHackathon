package tests;

import basetest.BaseTest;
import org.policy.pages.HealthHomePage;
import org.policy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_22NavigateToHealthInsurancePage extends BaseTest {

    @Test
    public void verifyNavigationToHealthInsurancePage() {
        HomePage homePage = new HomePage(driver);
        HealthHomePage healthHomePage = new HealthHomePage(driver);

        boolean submenuLoaded = healthHomePage.navigateAndWaitForSubmenu(homePage);

        Assert.assertTrue(submenuLoaded,
                "TC_22 Failed: Health Insurance submenu/menu items did not load. "
                        + "Current URL: " + driver.getCurrentUrl());
    }
}