package test;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.CarPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11NavigateCarPage extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyCarInsuranceNavigation() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();

        boolean isDisplayed = false;

        for (int i = 0; i < 15; i++) {
            if (carPage.isCarPageDisplayed()) {
                isDisplayed = true;
                break;
            }
        }

        Assert.assertTrue(isDisplayed, "Car Insurance page/form is NOT displayed");
    }
}
