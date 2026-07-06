package tests;

import basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelReader;

import java.util.List;

public class TC_16VerifyPlanPricing extends BaseTest {

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyPlanPricing() {
        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city   = data[0].toString();
        String make   = data[1].toString();
        String model  = data[2].toString();
        String mobile = data[3].toString();
        String email  = data[4].toString();

        Assert.assertFalse(city.trim().isEmpty(),"City is empty");
        Assert.assertFalse(make.trim().isEmpty(),"Make is empty");
        Assert.assertFalse(model.trim().isEmpty(),"Model is empty");
        Assert.assertEquals(mobile.length(),10,"Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"),"Mobile should contain only digits");
        Assert.assertTrue(email.contains("@"),"Invalid email");

        homePage = new HomePage(driver);
        carPage  = new CarPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewVehicleLink();
        carPage.enterMobile(mobile);
        carPage.enterEmail(email);
        carPage.clickNewVehicleGetQuote();

        Assert.assertTrue(carPage.isSelectPlansPageDisplayed(),"Select Plans page not displayed");
        carPage.enterCity(city);
        carPage.selectCity(city);
        carPage.enterCarMake(make);
        carPage.selectCarModel(model);
        Assert.assertTrue(driver.getCurrentUrl().contains("select-plans"),"User is not on Select Plans page");
        carPage.clickProceedBtnCity();
        //  Zero Dep
        boolean zeroDepSelected = carPage.selectZeroDepIfNotSelected();
        System.out.println("Zero Dep selected : " + zeroDepSelected);
        Assert.assertTrue(zeroDepSelected,"Zero Dep plan is not selected");
        verifyPlan("Zero Dep", carPage.getZeroDepAmount(),
                carPage.getTotalPremiumAmount(), carPage.getPlanAmount());
        loopPersonalProtectSums("Zero Dep");

        // No Frills
        boolean noFrillsSelected = carPage.selectNoFrillsIfNotSelected();
        System.out.println("No Frills selected : " + noFrillsSelected);
        Assert.assertTrue(noFrillsSelected,"No Frills plan is not selected");
        verifyPlan("No Frills", carPage.getNoFrillsAmount(),
                carPage.getTotalPremiumAmount(), carPage.getPlanAmount());
        loopPersonalProtectSums("No Frills");
        // Smart Cover
        boolean smartCoverSelected = carPage.selectSmartCoverIfNotSelected();
        System.out.println("Smart Cover selected : " + smartCoverSelected);
        Assert.assertTrue(smartCoverSelected,"Smart Cover plan is not selected");

        verifyPlan("Smart Cover", carPage.getSmartCoverAmount(),
                carPage.getTotalPremiumAmount(), carPage.getPlanAmount());
        loopPersonalProtectSums("Smart Cover");
    }

    private void loopPersonalProtectSums(String planName) {
        String baseTotal = carPage.getTotalPremiumAmount();
        System.out.println("Base Total for " + planName + " : " + baseTotal);
        carPage.openPersonalProtectDropdown();
        int optionCount = carPage.getPersonalProtectSumOptions().size();
        System.out.println("Sum Assured options found for " + planName + " : " + optionCount);

        for (int i = 0; i < optionCount; i++) {
            carPage.openPersonalProtectDropdown();
            List<WebElement> options = carPage.getPersonalProtectSumOptions();
            WebElement option = options.get(i);
            String optionLabel = option.getText().trim();

            carPage.selectPersonalProtectSum(option);
            System.out.println(planName + " | Sum Assured selected : " + optionLabel);

            carPage.togglePersonalProtect();
            String totalAfterAdd = carPage.getTotalPremiumAmount();
            String planAfterAdd  = carPage.getPlanAmount();
            verifyAfterAddon(planName + " [" + optionLabel + "]", totalAfterAdd, planAfterAdd);

            carPage.togglePersonalProtect();
            String totalAfterRemove = carPage.getTotalPremiumAmount();
            System.out.println(planName + " | After Remove Total : " + totalAfterRemove);

            Assert.assertEquals(
                    totalAfterRemove.replaceAll("[^0-9,]",""),
                    baseTotal.replaceAll("[^0-9,]",""),
                    planName + " Total did not reset after removing addon [" + optionLabel + "]"
            );
        }

        System.out.println( planName + " Personal Protect loop complete ");
    }

    private void verifyPlan(String planName, String cardAmount, String totalPremium, String planAmount) {
        System.out.println(planName + " : ");
        System.out.println(planName + " Card Amount   : " + cardAmount);
        System.out.println("Total Premium Amount : " + totalPremium);
        System.out.println("Plan Amount          : " + planAmount);

        Assert.assertFalse(cardAmount.isEmpty(), planName + " amount is empty");
        Assert.assertTrue(totalPremium.contains(cardAmount.replace("₹","").trim()),
                "Total Premium does not match " + planName + " amount");
        Assert.assertTrue(planAmount.contains(cardAmount.replace("₹","").trim()),
                "Plan Amount does not match " + planName + " amount");

        System.out.println("All three amounts match : " + cardAmount);
    }

    private void verifyAfterAddon(String planName, String totalPremium, String planAmount) {
        System.out.println(planName + " (after Personal Protect Add) ");
        System.out.println("Total Premium Amount : " + totalPremium);
        System.out.println("Plan Amount          : " + planAmount);

        Assert.assertFalse(totalPremium.isEmpty(), "Total Premium empty after addon for " + planName);
        Assert.assertFalse(planAmount.isEmpty(),   "Plan Amount empty after addon for " + planName);

        String totalDigits = totalPremium.replaceAll("[^0-9,]","");
        String planDigits  = planAmount.replaceAll("[^0-9,]","");

        Assert.assertEquals(totalDigits, planDigits,
                "Total Premium and Plan Amount mismatch after Personal Protect Add for " + planName);

        System.out.println("Total Premium & Plan Amount still match after addon : " + totalPremium);
    }
}