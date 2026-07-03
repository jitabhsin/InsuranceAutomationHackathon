package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.insurance.utils.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_22SelectProducts extends BaseTest {
    HealthHomePage healthHomePage;

    @Test
    public void testSelectProducts(){
        healthHomePage = new HealthHomePage(driver);

        healthHomePage.clickHealthTab();
        healthHomePage.clickProductDropdwn();
        String actual = healthHomePage.selectProduct("Activate Booster");

        Assert.assertEquals(actual, "Activate Booster");
        System.out.println("Activate Booster selected");
    }
}