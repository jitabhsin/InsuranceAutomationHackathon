package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class TC_13AfterNavigateWithoutData extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyNewVehicleValidationMessages() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        String invalidMobile = ConfigReader.getProperty("invalidMobile");
        String invalidEmail = ConfigReader.getProperty("invalidEmail");
        String expectedMobileError = ConfigReader.getProperty("expectedMobileError");
        String expectedEmailError = ConfigReader.getProperty("expectedEmailError");
        homePage.clickCarInsurance();
        carPage.clickNewVehicleLink();
        Assert.assertTrue(carPage.isNewVehiclePageDisplayed(),
                "New Vehicle page is not displayed");
        System.out.println("New Vehicle page displayed successfully");
        Assert.assertTrue(
                carPage.isNewVehicleMobileFieldDisplayed(),
                "Mobile Number field is not displayed");
        System.out.println("Mobile Number field displayed successfully");
        Assert.assertTrue(
                carPage.isNewVehicleEmailFieldDisplayed(),
                "Email field is not displayed");
        System.out.println("Email field displayed successfully");
        carPage.clickNewVehicleGetQuote();
        String blankMobileError =
                carPage.getMobileErrorMessage();
        String blankEmailError =
                carPage.getEmailErrorMessage();
        System.out.println("Blank Mobile Error = " + blankMobileError);
        System.out.println("Blank Email Error = " + blankEmailError);
        Assert.assertEquals(
                blankMobileError,
                expectedMobileError,
                "Blank mobile validation failed");
        Assert.assertEquals(
                blankEmailError,
                expectedEmailError,
                "Blank email validation failed");
        carPage.enterMobile(invalidMobile);
        carPage.enterEmail(invalidEmail);
        carPage.clickNewVehicleGetQuote();
        String invalidMobileError =
                carPage.getMobileErrorMessage();
        String invalidEmailError =
                carPage.getEmailErrorMessage();
        System.out.println("Invalid Mobile Error = " + invalidMobileError);
        System.out.println("Invalid Email Error = " + invalidEmailError);
        Assert.assertEquals(
                invalidMobileError,
                expectedMobileError,
                "Invalid mobile validation failed");
        Assert.assertEquals(
                invalidEmailError,
                expectedEmailError,
                "Invalid email validation failed");
        Assert.assertTrue(
                invalidMobileError.contains("mobile"),
                "Mobile error text validation failed");
        Assert.assertTrue(
                invalidEmailError.contains("email"),
                "Email error text validation failed");
        System.out.println("TC_13 PASSED");
    }
}