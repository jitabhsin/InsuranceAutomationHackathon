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

public class TC_20_VerifyInsuredDetailsPage extends BaseTest {
    private static final Logger logger =
            LogManager.getLogger(TC_20_VerifyInsuredDetailsPage.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyInsuredDetailsPage() {

        logger.info("TC_20 - Verify Insured Details Page Started");

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
        logger.info("Verify Information page displayed");

        carPage.clickThatsCorrect();
        logger.info("Clicked That's Correct Button");

        Assert.assertTrue(carPage.isInsuredDetailsPage(), "Not navigated to Insured Details page");
        logger.info("Successfully navigated to Insured Details Page");

        carPage.closeKycPopupIfPresent();
        logger.info("KYC popup handled if present");

        String carDetail = carPage.getInsuredCarDetail();
        String regNo = carPage.getInsuredRegNo();
        String idv = carPage.getInsuredIdv();

        logger.info("Car Detail : {}", carDetail);
        logger.info("Registration Number : {}", regNo);
        logger.info("IDV : {}", idv);

        Assert.assertTrue(carDetail.toUpperCase().contains(make.toUpperCase()), "Car detail does not match make");
        Assert.assertTrue(regNo.equalsIgnoreCase("NEW"), "Registration no. should be NEW");
        Assert.assertTrue(idv.contains("₹"), "IDV missing ₹ symbol");
        logger.info("Vehicle details validated successfully");

        Assert.assertTrue(carPage.isPremiumSummaryDisplayed(), "Premium summary block not displayed");
        logger.info("Premium Summary block displayed");

        String base = carPage.getInsuredBasePremium();
        String addOn = carPage.getInsuredAdditionalCovers();
        String sub = carPage.getInsuredSubTotal();
        String net = carPage.getInsuredNetPremium();
        String tax = carPage.getInsuredTax();
        String total = carPage.getInsuredTotalPremium();

        logger.info("Base Premium : {}", base);
        logger.info("Additional Covers : {}", addOn);
        logger.info("Sub Total : {}", sub);
        logger.info("Net Premium : {}", net);
        logger.info("Tax (18% GST) : {}", tax);
        logger.info("Total Premium : {}", total);

        Assert.assertFalse(base.isEmpty(), "Base premium empty");
        Assert.assertFalse(sub.isEmpty(), "Sub Total empty");
        Assert.assertFalse(net.isEmpty(), "Net Premium empty");
        Assert.assertFalse(total.isEmpty(), "Total Premium empty");

        logger.info("Premium calculations validated");

        ScreenshotUtils.captureScreenshot(driver, "TC_20_Insured_Details");

        boolean proceedEnabled = carPage.isProceedToPayEnabled();
        logger.info("Proceed To Pay Enabled : {}", proceedEnabled);
        Assert.assertTrue(proceedEnabled, "Proceed to Pay button not enabled/clickable");

        logger.info("Proceed To Pay button is enabled");
        logger.info("TC_20 PASSED");
    }
}