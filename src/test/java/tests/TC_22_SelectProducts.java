package tests;

import org.insurance.basetest.BaseTest;
import org.insurance.pages.HealthHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.insurance.utils.ExcelReader;

public class TC_22_SelectProducts extends BaseTest {
    HealthHomePage healthHomePage;

    @DataProvider(name = "healthData")
    public Object[][] getHealthData() {
        Object[][] data = new ExcelReader().readSheetHealth();
        Object[][] productData = new Object[data.length][1];
        for (int i = 0; i < data.length; i++) {
            productData[i][0] = data[i][0];
        }
        return productData;
    }

    @Test(dataProvider = "healthData")
    public void testSelectProducts(String product){
        healthHomePage = new HealthHomePage(driver);

        healthHomePage.clickHealthTab();
        logger.info("Health tab clicked");
        Assert.assertEquals(healthHomePage.isSelectProductTextDisplayed(), "Select products");
        Assert.assertEquals(healthHomePage.isInsureMembersTextDisplayed(), "Insure members");
        Assert.assertEquals(healthHomePage.isContactDetailsTextDisplayed(), "Contact details");

        int totalProduct = healthHomePage.clickProductDropdwn();
        logger.info("Click product button");

        Assert.assertEquals(totalProduct, 3);
        String actual = healthHomePage.selectProduct(product);
        logger.info("Product selected");

        Assert.assertEquals(actual, product);
        logger.info(product + " selected");
    }
}