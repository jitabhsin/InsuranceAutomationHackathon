package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

import java.util.List;

public class TC_19VerifyInformationBelow extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyInformationBelow() {
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

        String verifyCity      = carPage.getVerifyCity();
        String verifyMakeModel = carPage.getVerifyMakeModel();
        String verifyRegUnder  = carPage.getVerifyRegUnderIndividual();

        System.out.println("City of Registration        : " + verifyCity);
        System.out.println("Car Make & Model            : " + verifyMakeModel);
        System.out.println("Registered under Individual : " + verifyRegUnder);
        System.out.println("Modify button displayed        : " + carPage.isModifyBtnDisplayed());
        System.out.println("That's correct button displayed: " + carPage.isThatsCorrectBtnDisplayed());

        Assert.assertEquals(verifyCity, city, "City of Registration does not match Excel input");
        Assert.assertEquals(verifyMakeModel, model, "Car Make & Model does not match Excel input");
        Assert.assertTrue(verifyRegUnder.equalsIgnoreCase("YES"), "Registered Under Individual should be YES");
    }
}