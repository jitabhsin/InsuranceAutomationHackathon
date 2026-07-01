package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.CarPage;

public class TC_11NavigateCarPage extends BaseTest{

    HomePage homePage;
    CarPage carPage;
    
     @Test
    public void verifyCarInsuranceNavigation() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();
        boolean isDisplayed = carPage.isCarPageDisplayed();

        Assert.assertTrue(isDisplayed, "Car Insurance page/form is NOT displayed");
    }
}