package test;

import basetest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TC_06_VerifyTotalPlan {

    BaseTest base;

    @Test
    public void testTotalPlan(){
        base = new BaseTest();
        WebDriver driver = base.getDriver();
    }
}
