package test;

import basetest.BaseTest;
import org.policy.pages.CarPage;
import org.policy.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_12WithoutCarNumber extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyErrorWithoutCarNumber() {

        homePage = new HomePage(driver);
        homePage.clickCarInsurance();


        carPage = new CarPage(driver);
        carPage.clickNewCar();

        String actualError = carPage.getErrorIfNot();

        Assert.assertTrue(actualError.length() > 0);
        System.out.println(actualError);
    }
}