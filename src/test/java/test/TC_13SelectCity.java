package test;

import basetest.BaseTest;
import org.policy.pages.CarPage;
import org.policy.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_13SelectCity extends BaseTest {


    HomePage homePage;
    CarPage carPage;

    @Test
    public void selectCity() {
        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewCar();

    }
}