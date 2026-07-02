package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_11NavigateCarPage extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyCarInsuranceNavigation() {

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        // Step 1 & 2 : Click Car Insurance
        homePage.clickCarInsurance();

        // Step 3 : Verify Car Insurance Form
        Assert.assertTrue(
                carPage.isCarPageDisplayed(),
                "Car Insurance form is not displayed");
        System.out.println("✓ Car Insurance form displayed successfully");

        // Verify Car Registration Field
        Assert.assertTrue(
                carPage.isRegistrationFieldDisplayed(),
                "Car Registration Number field is not displayed");
        System.out.println("✓ Car Registration Number field displayed successfully");

        // Verify Mobile Number Field
        Assert.assertTrue(
                carPage.isMobileFieldDisplayed(),
                "Mobile Number field is not displayed");
        System.out.println("✓ Mobile Number field displayed successfully");

        // Verify Email Field
        Assert.assertTrue(
                carPage.isEmailFieldDisplayed(),
                "Email field is not displayed");
        System.out.println("✓ Email field displayed successfully");

        System.out.println("TC_11 PASSED");
    }
}
