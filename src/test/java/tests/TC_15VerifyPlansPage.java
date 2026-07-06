package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class TC_15VerifyPlansPage extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyPlansPageStructure() {
        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city   = data[0].toString();
        String make   = data[1].toString();
        String model  = data[2].toString();
        String mobile = data[3].toString();
        String email  = data[4].toString();

        Assert.assertFalse(city.trim().isEmpty(),"City is empty");
        Assert.assertFalse(make.trim().isEmpty(),"Make is empty");
        Assert.assertFalse(model.trim().isEmpty(),"Model is empty");
        Assert.assertEquals(mobile.length(),10,"Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"),"Mobile should contain only digits");
        Assert.assertTrue(email.contains("@"),"Invalid email");

        homePage = new HomePage(driver);
        carPage  = new CarPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewVehicleLink();
        carPage.enterMobile(mobile);
        carPage.enterEmail(email);
        carPage.clickNewVehicleGetQuote();

        Assert.assertTrue(carPage.isSelectPlansPageDisplayed(),"Select Plans page not displayed");

        carPage.enterCity(city);
        carPage.selectCity(city);
        carPage.enterCarMake(make);
        carPage.selectCarModel(model);

        Assert.assertTrue(driver.getCurrentUrl().contains("select-plans"),"User is not on Select Plans page");

        carPage.clickProceedBtnCity();

        boolean odtpSelected = carPage.checkODTP();
        System.out.println("Own Damage + TP selected by default : " + odtpSelected);
        Assert.assertTrue(odtpSelected,"Own Damage + TP option is not selected by default");

        boolean noFrillsExists   = carPage.isNoFrillsCardDisplayed();
        boolean zeroDepExists    = carPage.isZeroDepCardDisplayed();
        boolean smartCoverExists = carPage.isSmartCoverCardDisplayed();

        System.out.println("No Frills card displayed   : " + noFrillsExists);
        System.out.println("Zero Dep card displayed    : " + zeroDepExists);
        System.out.println("Smart Cover card displayed : " + smartCoverExists);

        Assert.assertTrue(noFrillsExists,   "No Frills plan card not displayed");
        Assert.assertTrue(zeroDepExists,    "Zero Dep plan card not displayed");
        Assert.assertTrue(smartCoverExists, "Smart Cover plan card not displayed");

        boolean addonExists = carPage.isPersonalProtectDisplayed();
        System.out.println("Personal Protect Policy displayed : " + addonExists);
        Assert.assertTrue(addonExists, "Personal Protect Policy section not displayed");

        boolean expandExists = carPage.isExpandLinkDisplayed();
        System.out.println("Expand link displayed : " + expandExists);
        Assert.assertTrue(expandExists, "Expand link not displayed");

        carPage.clickExpand();

        String cityOfReg = carPage.getCityOfRegistration();
        System.out.println("City of Registration : " + cityOfReg);
        Assert.assertEquals(cityOfReg, city, "City of Registration does not match input city");

        System.out.println("All structural checks passed on Plans Page");
    }
}