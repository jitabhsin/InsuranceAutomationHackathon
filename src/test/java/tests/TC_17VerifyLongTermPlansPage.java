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

public class TC_17VerifyLongTermPlansPage extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_17VerifyLongTermPlansPage.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyLongTermPlansPageStructure() {

        logger.info("TC_17 - Verify Long Term Plans Page Structure Started");

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

        Assert.assertFalse(city.trim().isEmpty(), "City is empty");
        Assert.assertFalse(make.trim().isEmpty(), "Make is empty");
        Assert.assertFalse(model.trim().isEmpty(), "Model is empty");
        Assert.assertEquals(mobile.length(), 10, "Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"), "Mobile should contain only digits");

        logger.info("Input validation completed successfully");

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

        Assert.assertTrue(carPage.isSelectPlansPageDisplayed(), "Select Plans page not displayed");
        logger.info("Select Plans page displayed successfully");

        carPage.enterCity(city);
        logger.info("Entered City");

        carPage.selectCity(city);
        logger.info("Selected City");

        carPage.enterCarMake(make);
        logger.info("Entered Car Make");

        carPage.selectCarModel(model);
        logger.info("Selected Car Model");

        Assert.assertTrue(driver.getCurrentUrl().contains("select-plans"), "User is not on Select Plans page");
        logger.info("User successfully navigated to Select Plans page");

        carPage.clickProceedBtnCity();
        logger.info("Clicked Proceed Button");

        boolean longTermSelected = carPage.selectLongTermPolicy();
        logger.info("Long Term Policy Selected : {}", longTermSelected);

        Assert.assertTrue(longTermSelected, "Long Term Policy option is not selected");
        boolean noFrillsExists = carPage.isNoFrillsCardDisplayed();
        boolean zeroDepExists = carPage.isZeroDepCardDisplayed();
        boolean smartCoverExists = carPage.isSmartCoverCardDisplayed();

        logger.info("No Frills Card Displayed : {}", noFrillsExists);
        logger.info("Zero Dep Card Displayed : {}", zeroDepExists);
        logger.info("Smart Cover Card Displayed : {}", smartCoverExists);

        Assert.assertTrue(noFrillsExists, "No Frills plan card not displayed");
        Assert.assertTrue(zeroDepExists, "Zero Dep plan card not displayed");
        Assert.assertTrue(smartCoverExists, "Smart Cover plan card not displayed");

        logger.info("All plan cards verified successfully");
        boolean addonExists = carPage.isPersonalProtectDisplayed();

        logger.info("Personal Protect Policy Displayed : {}", addonExists);
        Assert.assertTrue(addonExists, "Personal Protect Policy section not displayed");

        boolean expandExists = carPage.isExpandLinkDisplayed();
        logger.info("Expand Link Displayed : {}", expandExists);
        Assert.assertTrue(expandExists, "Expand link not displayed");

        ScreenshotUtils.captureScreenshot(driver, "TC17_Long_Term_Plans");

        carPage.clickExpand();
        logger.info("Clicked Expand Link");

        String cityOfReg = carPage.getCityOfRegistration();
        logger.info("City Of Registration : {}", cityOfReg);
        Assert.assertEquals(cityOfReg, city, "City of Registration does not match input city");

        logger.info("City Of Registration validated successfully");
        logger.info("All structural checks passed on Long Term Plans Page");
        logger.info("TC_17 PASSED");
    }
}