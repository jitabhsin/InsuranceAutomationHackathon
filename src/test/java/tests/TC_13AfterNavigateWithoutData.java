package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_13AfterNavigateWithoutData extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyNewVehicleValidationMessages() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();

        carPage.clickNewVehicleLink();

        Assert.assertTrue(
                carPage.isNewVehiclePageDisplayed(),
                "New Vehicle page is not displayed");
        System.out.println("✓ New Vehicle page displayed successfully");

        Assert.assertTrue(
                carPage.isNewVehicleMobileFieldDisplayed(),
                "Mobile Number field is not displayed");
        System.out.println("✓ Mobile Number field displayed successfully");

        Assert.assertTrue(
                carPage.isNewVehicleEmailFieldDisplayed(),
                "Email field is not displayed");
        System.out.println("✓ Email field displayed successfully");

        carPage.clickNewVehicleGetQuote();

        Assert.assertEquals(
                carPage.getMobileErrorMessage(),
                "Please enter a valid mobile number",
                "Mobile validation message mismatch");
        System.out.println("✓ Mobile validation displayed successfully");

        Assert.assertEquals(
                carPage.getEmailErrorMessage(),
                "Please enter valid email id",
                "Email validation message mismatch");
        System.out.println("✓ Email validation displayed successfully");

        System.out.println("TC_13 PASSED");
    }
}