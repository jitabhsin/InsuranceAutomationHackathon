package tests;

import org.insurance.basetest.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

public class TC_14SelectCarAndModel extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_14SelectCarAndModel.class);

    HomePage homePage;
    CarPage carPage;


    @DataProvider(name = "carData")
    public Object[][] getData() {
        logger.info("Reading test data from Excel");
        ExcelReader excel = new ExcelReader();
        return excel.readSheetCar();
    }

    @Test(dataProvider = "carData")
    public void verifySelectCarAndModel(String city, String make, String model, String mobile, String email) {
        logger.info("TC_14 - Verify Select Car And Model Started");
        Assert.assertFalse(city.trim().isEmpty(),"City is empty");
        Assert.assertFalse(make.trim().isEmpty(),"Make is empty");
        Assert.assertFalse(model.trim().isEmpty(),"Model is empty");

        logger.info("Input data validation passed");
        Assert.assertEquals(mobile.length(),10,"Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"),"Mobile should contain only digits");
        Assert.assertTrue(email.contains("@"),"Invalid email");
        logger.info("Mobile and Email validation passed");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        if (!driver.getCurrentUrl().contains("select-plans")) {
            logger.info("Navigating to Car Insurance flow");

            homePage.clickCarInsurance();
            logger.info("Clicked Car Insurance");

            carPage.clickNewVehicleLink();
            logger.info("Clicked New Vehicle");

            carPage.enterMobile(mobile);
            logger.info("Entered mobile number");

            carPage.enterEmail(email);
            logger.info("Entered email address");

            carPage.clickNewVehicleGetQuote();
            logger.info("Clicked Get Quote");

            Assert.assertTrue(carPage.isSelectPlansPageDisplayed(),"Select Plans page is not displayed");
            logger.info("Select Plans page displayed successfully");
        }
        logger.info("Entering city");
        carPage.enterCity(city);

        logger.info("Selecting city");
        carPage.selectCity(city);

        logger.info("Entering car make");
        carPage.enterCarMake(make);

        logger.info("Selecting car model");
        carPage.selectCarModel(model);

        Assert.assertTrue(driver.getCurrentUrl().contains("select-plans"),"User is not on Select Plans page");
        logger.info("User successfully reached Select Plans page");
        logger.info("TC_14 PASSED");
    }
}