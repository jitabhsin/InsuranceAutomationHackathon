package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import basetest.BaseTest;
import org.insurance.pages.HomePage;
import org.insurance.pages.CarPage;

public class TC_11NavigateCarPage extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyCarInsuranceNavigation() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        // Added Validation
        Assert.assertTrue(
                homePage.isHomePageDisplayed(),
                "Home Page is not loaded");

        // Added Validation
        Assert.assertTrue(
                homePage.isCarInsurancePresent(),
                "Car Insurance option is not displayed");

        homePage.clickCarInsurance();

        Assert.assertTrue(
                carPage.isCarPageDisplayed(),
                "Car Insurance page/form is NOT displayed");

        // Added Validation
        Assert.assertTrue(
                carPage.isRegistrationFieldDisplayed(),
                "Registration field is not displayed");

        // Added Validation
        Assert.assertFalse(
                carPage.getCurrentUrl().trim().isEmpty(),
                "URL is empty");

        // Added Validation
        Assert.assertFalse(
                carPage.getPageTitle().trim().isEmpty(),
                "Page title is empty");

        System.out.println("Title : " + carPage.getPageTitle());
        System.out.println("URL : " + carPage.getCurrentUrl());
    }
}