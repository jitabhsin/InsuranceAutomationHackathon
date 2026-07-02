package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_12WithoutCarNumber extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyErrorWithoutCarNumber() {

        homePage = new HomePage(driver);

        // Added Validation
        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "Home Page is not loaded");

        // Added Validation
        Assert.assertTrue(
                homePage.isCarInsurancePresent(),
                "Car Insurance option is not displayed");

        homePage.clickCarInsurance();

        carPage = new CarPage(driver);

        // Added Validation
        Assert.assertTrue(
                carPage.isCarPageDisplayed(),
                "Car page not displayed");

        // Added Validation
        Assert.assertTrue(
                carPage.isRegistrationFieldDisplayed(),
                "Registration field not displayed");

        String actualError = carPage.getErrorIfNot();

        Assert.assertNotNull(
                actualError,
                "Error message is null");

        Assert.assertFalse(
                actualError.trim().isEmpty(),
                "Error message is blank");

        Assert.assertTrue(
                actualError.length() > 0,
                "Error message length should be greater than 0");

        System.out.println("Validation Message : " + actualError);
    }
}