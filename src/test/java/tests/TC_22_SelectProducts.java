package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_22_SelectProducts extends BaseTest {
    HealthHomePage healthHomePage;

    @Test
    public void testSelectProducts(){
        healthHomePage = new HealthHomePage(driver);
        String actual = healthHomePage.selectProduct("Elevate");

        Assert.assertEquals(actual, "Elevate");
        System.out.println("Elevate selected");
    }
}