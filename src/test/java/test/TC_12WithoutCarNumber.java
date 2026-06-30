package test;

import basetest.BaseTest;
import org.policy.pages.CarPage;
import org.policy.pages.HomePage;
import org.testng.annotations.BeforeMethod;

public class TC_12WithoutCarNumber {

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

}
