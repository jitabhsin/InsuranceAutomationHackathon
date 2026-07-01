package test;

import basetest.BaseTest;
import org.policy.pages.CarPage;
import org.policy.pages.HomePage;
import org.testng.annotations.Test;

public class TC_13SelectCity extends BaseTest {


    HomePage homePage;
    CarPage carPage;
    CarSelectPage carSelectPage;

    @Test
    public void selectCity() {
        homePage = new HomePage(driver);
        carPage = new CarPage(driver);
        carSelectPage = new CarSelectPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewCar();
        carSelectPage.selectCityElement();



    }
}

