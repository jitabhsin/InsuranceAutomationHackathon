package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class TC_14SelectCarAndModel extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @DataProvider(name = "carData")
    public Object[][] getData() {
        ExcelReader excel = new ExcelReader();
        return excel.readSheetCar();
    }

    @Test(dataProvider = "carData")
    public void verifySelectCarAndModel(String city, String make, String model, String mobile, String email) {
        System.out.println("CITY=" + city);
        System.out.println("MAKE=" + make);
        System.out.println("MODEL=" + model);
        System.out.println("MOBILE=" + mobile);
        System.out.println("EMAIL=" + email);
        System.out.println("CURRENT URL=" + driver.getCurrentUrl());
        Assert.assertNotNull(city);
        Assert.assertNotNull(make);
        Assert.assertNotNull(model);
        Assert.assertFalse(city.trim().isEmpty());
        Assert.assertFalse(make.trim().isEmpty());
        Assert.assertFalse(model.trim().isEmpty());
        Assert.assertEquals(mobile.length(), 10);
        Assert.assertTrue(mobile.matches("\\d+"));
        Assert.assertTrue(email.contains("@"));
        Assert.assertTrue(email.contains("."));
        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        if (!driver.getCurrentUrl().contains("select-plans")) {
            homePage.clickCarInsurance();
            carPage.clickNewVehicleLink();
            carPage.enterMobile(mobile);
            carPage.enterEmail(email);
            carPage.clickNewVehicleGetQuote();
            Assert.assertTrue(
                    carPage.isSelectPlansPageDisplayed(), "Select Plans page is not displayed");
        }
        carPage.enterCity(city);
        carPage.selectCity(city);
        carPage.enterCarMake(make);
        carPage.selectCarModel(model);
        Assert.assertTrue(
                driver.getCurrentUrl().contains("select-plans"),
                "User is not on Select Plans page");
        Assert.assertNotNull(city);
        Assert.assertNotNull(make);
        Assert.assertNotNull(model);

        System.out.println("TC_14 PASSED");
    }
}