package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import basetest.BaseTest;
import org.policy.pages.HomePage;
import org.policy.pages.CarPage;

public class TC_11NavigateCarPage {

    BaseTest base;
    HomePage homePage;
    CarPage carPage;

    @BeforeMethod
    public void setup() {
        base = new BaseTest();
        base.setup();
        homePage = new HomePage(base.getDriver());
        carPage = new CarPage(base.getDriver());
    }

    @AfterMethod
    public void tearDown() {
        base.tearDown();
    }

    @Test
    public void verifyCarInsuranceNavigation() {
        homePage.clickCarInsurance();
        boolean isDisplayed = carPage.isCarPageDisplayed();
        Assert.assertTrue(isDisplayed, "Car Insurance page/form is NOT displayed");
    }
}
