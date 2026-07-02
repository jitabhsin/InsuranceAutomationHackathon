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
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();

        carPage.clickGetQuote();

        Assert.assertTrue(
                carPage.isValidationMessageDisplayed(),
                "Error message is not displayed");
        System.out.println("Error message displayed successfully");

        String actualMessage = carPage.getValidationMessage();

        Assert.assertEquals(actualMessage,
                "Please enter a valid reg number",
                "Incorrect validation message displayed");

        System.out.println("✓Validation Message : " + actualMessage);

        System.out.println("TC_12 PASSED");
    }
}