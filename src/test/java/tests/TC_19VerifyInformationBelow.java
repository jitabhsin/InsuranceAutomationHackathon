package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ExcelReader;
import org.insurance.utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_19VerifyInformationBelow extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_19VerifyInformationBelow.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyInformationBelow() {

        logger.info("TC_19 - Verify Information Below Started");

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city = data[0].toString();
        String make = data[1].toString();
        String model = data[2].toString();
        String mobile = data[3].toString();

        logger.info("Excel data loaded successfully");
        logger.info("City : {}", city);
        logger.info("Make : {}", make);
        logger.info("Model : {}", model);

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        logger.info("Page objects initialized");

        homePage.clickCarInsurance();
        logger.info("Clicked Car Insurance");

        carPage.clickNewVehicleLink();
        logger.info("Clicked New Vehicle");

        carPage.enterMobile(mobile);
        logger.info("Entered Mobile Number");

        carPage.clickNewVehicleGetQuote();
        logger.info("Clicked Get Quote");

        carPage.enterCity(city);
        logger.info("Entered City");

        carPage.selectCity(city);
        logger.info("Selected City");

        carPage.enterCarMake(make);
        logger.info("Entered Car Make");

        carPage.selectCarModel(model);
        logger.info("Selected Car Model");

        carPage.clickProceedBtnCity();
        logger.info("Clicked Proceed Button");

        carPage.selectLongTermPolicy();
        logger.info("Selected Long Term Policy");

        carPage.selectZeroDepIfNotSelected();
        logger.info("Selected Zero Dep Plan");

        carPage.clickContinue();
        logger.info("Clicked Continue Button");

        Assert.assertTrue(carPage.isVerifyInfoHeaderDisplayed(), "Verify Information header not displayed");
        logger.info("Verify Information header displayed successfully");

        String verifyCity = carPage.getVerifyCity();
        String verifyMakeModel = carPage.getVerifyMakeModel();
        String verifyRegUnder = carPage.getVerifyRegUnderIndividual();

        logger.info("City Of Registration : {}", verifyCity);
        logger.info("Car Make And Model : {}", verifyMakeModel);
        logger.info("Registered Under Individual : {}", verifyRegUnder);
        logger.info("Modify Button Displayed : {}", carPage.isModifyBtnDisplayed());
        logger.info("That's Correct Button Displayed : {}", carPage.isThatsCorrectBtnDisplayed());

        Assert.assertEquals(verifyCity, city, "City of Registration does not match Excel input");
        Assert.assertEquals(verifyMakeModel, model, "Car Make & Model does not match Excel input");
        Assert.assertTrue(verifyRegUnder.equalsIgnoreCase("YES"), "Registered Under Individual should be YES");

        ScreenshotUtils.captureScreenshot(driver, "TC19_Verify_Information");

        logger.info("All information validated successfully");
        logger.info("TC_19 PASSED");
    }
}