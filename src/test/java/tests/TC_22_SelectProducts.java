package tests;

import basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelReader;

public class TC_22_SelectProducts extends BaseTest {
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        return new ExcelReader().readSheetHealth();
    }

    @Test(dataProvider = "healthData")
    public void testSelectProducts(String name, String mobileNo, String email, String pincode, String product, String member, String dob){
        healthHomePage = new HealthHomePage(driver);
        String actual = healthHomePage.selectProduct(product);

        Assert.assertEquals(actual, product);
        System.out.println(product + " selected");
    }
}