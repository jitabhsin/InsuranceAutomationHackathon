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
    public void verifyPlansPage() {
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
        System.out.println("Own Damage + TP selected : " + odtpSelected);

        Assert.assertTrue(carPage.checkODTP(),"Own Damage + TP option is not selected by default");

        boolean zeroDepSelected = carPage.selectZeroDepIfNotSelected();
        System.out.println("Zero Dep selected : " + zeroDepSelected);
        Assert.assertTrue(zeroDepSelected,"Zero Dep plan is not selected");


        String zeroDepAmount = carPage.getZeroDepAmount();
        System.out.println("Zero Dep Amount : " + zeroDepAmount);
        Assert.assertFalse(zeroDepAmount.isEmpty(),"Zero Dep amount is empty");
        Assert.assertTrue(zeroDepAmount.contains("₹"),"Zero Dep amount missing ₹ symbol");


        String totalPremium = carPage.getTotalPremiumAmount();
        String planAmount   = carPage.getPlanAmount();

        System.out.println("Total Premium Amount : " + totalPremium);
        System.out.println("Plan Amount          : " + planAmount);

        Assert.assertTrue(totalPremium.contains(zeroDepAmount.replace("₹","").trim()),
                "Total Premium does not match Zero Dep amount");
        Assert.assertTrue(planAmount.contains(zeroDepAmount.replace("₹","").trim()),
                "Plan Amount does not match Zero Dep amount");

        System.out.println("All three amounts match : " + zeroDepAmount);

    }
}