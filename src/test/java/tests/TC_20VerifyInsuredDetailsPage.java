package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

import java.util.List;


public class TC_20VerifyInsuredDetailsPage extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyInsuredDetailsPage() {
        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city   = data[0].toString();
        String make   = data[1].toString();
        String model  = data[2].toString();
        String mobile = data[3].toString();
        String email  = data[4].toString();

        homePage = new HomePage(driver);
        carPage  = new CarPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewVehicleLink();
        carPage.enterMobile(mobile);
        carPage.enterEmail(email);
        carPage.clickNewVehicleGetQuote();

        carPage.enterCity(city);
        carPage.selectCity(city);
        carPage.enterCarMake(make);
        carPage.selectCarModel(model);

        carPage.clickProceedBtnCity();

        carPage.selectLongTermPolicy();
        carPage.selectZeroDepIfNotSelected();

        carPage.clickContinue();
        System.out.println("Clicked Continue button");

        Assert.assertTrue(carPage.isVerifyInfoHeaderDisplayed(), "Verify Information header not displayed");

        carPage.clickThatsCorrect();
        System.out.println("Clicked That's correct button");

        Assert.assertTrue(carPage.isInsuredDetailsPage(),
                "Not navigated to Insured Details page");
        System.out.println("Landed on Insured Details page");

        carPage.closeKycPopupIfPresent();

        String carDetail = carPage.getInsuredCarDetail();
        String regNo     = carPage.getInsuredRegNo();
        String idv       = carPage.getInsuredIdv();

        System.out.println("Car Detail        : " + carDetail);
        System.out.println("Registration No.  : " + regNo);
        System.out.println("IDV               : " + idv);

        Assert.assertTrue(carDetail.toUpperCase().contains(make.toUpperCase()),
                "Car detail does not match make");
        Assert.assertTrue(regNo.equalsIgnoreCase("NEW"),
                "Registration no. should be NEW");

        Assert.assertTrue(carPage.isPremiumSummaryDisplayed(),
                "Premium summary block not displayed");

        String base   = carPage.getInsuredBasePremium();
        String addOn  = carPage.getInsuredAdditionalCovers();
        String sub    = carPage.getInsuredSubTotal();
        String net    = carPage.getInsuredNetPremium();
        String tax    = carPage.getInsuredTax();
        String total  = carPage.getInsuredTotalPremium();

        System.out.println("Base premium      : " + base);
        System.out.println("Additional covers : " + addOn);
        System.out.println("Sub Total         : " + sub);
        System.out.println("Net Premium       : " + net);
        System.out.println("Tax (18% GST)     : " + tax);
        System.out.println("Total Premium     : " + total);

        Assert.assertFalse(base.isEmpty(),  "Base premium empty");
        Assert.assertFalse(sub.isEmpty(),   "Sub Total empty");
        Assert.assertFalse(net.isEmpty(),   "Net Premium empty");
        Assert.assertFalse(total.isEmpty(), "Total Premium empty");

        boolean proceedEnabled = carPage.isProceedToPayEnabled();
        System.out.println("Proceed to Pay enabled : " + proceedEnabled);
        Assert.assertTrue(proceedEnabled, "Proceed to Pay button not enabled/clickable");

        System.out.println("---- TC_20 Verify Insured Details Page PASSED ----");
    }
}