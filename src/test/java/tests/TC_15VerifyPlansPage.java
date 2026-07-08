package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_15VerifyPlansPage extends BaseTest {

    private static final Logger logger =
            LogManager.getLogger(TC_15VerifyPlansPage.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyPlansPageStructure() {

        logger.info("TC_15 - Verify Plans Page Structure Started");

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city   = data[0].toString();
        String make   = data[1].toString();
        String model  = data[2].toString();
        String mobile = data[3].toString();

        logger.info("Excel Data Loaded Successfully");
        logger.info("City : {}", city);
        logger.info("Make : {}", make);
        logger.info("Model : {}", model);
        logger.info("Mobile : {}", mobile);

        Assert.assertFalse(city.trim().isEmpty(), "City is empty");
        Assert.assertFalse(make.trim().isEmpty(), "Make is empty");
        Assert.assertFalse(model.trim().isEmpty(), "Model is empty");

        Assert.assertEquals(mobile.length(), 10, "Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"), "Mobile should contain only digits");
        logger.info("Input data validation passed");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        logger.info("Page objects created successfully");

        homePage.clickCarInsurance();
        logger.info("Clicked Car Insurance");

        carPage.clickNewVehicleLink();
        logger.info("Clicked New Vehicle");

        carPage.enterMobile(mobile);
        logger.info("Entered Mobile Number");

        carPage.clickNewVehicleGetQuote();
        logger.info("Clicked Get Quote button");

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
        logger.info("User is on Select Plans page");

        carPage.clickProceedBtnCity();
        logger.info("Clicked Proceed Button");

        boolean odtpSelected = carPage.checkODTP();
        logger.info("Own Damage + TP selected by default : {}", odtpSelected);
        Assert.assertTrue(odtpSelected, "Own Damage + TP option is not selected by default");

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

        carPage.clickExpand();
        logger.info("Clicked Expand Link");

        String cityOfReg = carPage.getCityOfRegistration();
        logger.info("City Of Registration : {}", cityOfReg);

        Assert.assertEquals(cityOfReg, city, "City of Registration does not match input city");
        logger.info("City Of Registration verified successfully");
        logger.info("All structural checks passed on Plans Page");

        logger.info("TC_15 PASSED");
    }
}