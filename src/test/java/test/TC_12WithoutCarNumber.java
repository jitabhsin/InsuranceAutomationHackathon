package test;

import basetest.BaseTest;
import org.policy.pages.CarPage;
import org.policy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_12WithoutCarNumber extends BaseTest {

    @Test
    public void verifyErrorWithoutCarNumber() {

        HomePage homePage = new HomePage(driver);
        homePage.clickCarInsurance();

        CarPage carPage = new CarPage(driver);
        carPage.clickNewCar();

        String actualError = carPage.getErrorIfNot();

        Assert.assertTrue(actualError.length() > 0);
        System.out.println(actualError);
    }
}