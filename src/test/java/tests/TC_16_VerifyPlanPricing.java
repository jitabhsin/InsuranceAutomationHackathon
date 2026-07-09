package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insurance.basetest.BaseTest;
import org.insurance.pages.CarPage;
import org.insurance.pages.HomePage;
import org.insurance.utils.ExcelReader;
import org.insurance.utils.ScreenshotUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_16_VerifyPlanPricing extends BaseTest {
    private static final Logger logger =
            LogManager.getLogger(TC_16_VerifyPlanPricing.class);

    HomePage homePage;
    CarPage carPage;

    @Test
    public void verifyPlanPricing() {

        logger.info("TC_16 - Verify Plan Pricing Started");

        ExcelReader excel = new ExcelReader();
        Object[] data = excel.readSheetCarFirstRow();

        String city = data[0].toString();
        String make = data[1].toString();
        String model = data[2].toString();
        String mobile = data[3].toString();

        logger.info("Excel Data Loaded Successfully");
        logger.info("City : {}", city);
        logger.info("Make : {}", make);
        logger.info("Model : {}", model);
        logger.info("Mobile : {}", mobile);

        Assert.assertFalse(city.trim().isEmpty(), "City is empty");
        Assert.assertFalse(make.trim().isEmpty(), "Make is empty");
        Assert.assertFalse(model.trim().isEmpty(), "Model is empty");
        Assert.assertEquals(mobile.length(), 10, "Invalid mobile length");
        Assert.assertTrue(mobile.matches("\\d+"), "Mobile should contain only digits");
        logger.info("Input validation completed successfully");

        homePage = new HomePage(driver);
        carPage = new CarPage(driver);

        homePage.clickCarInsurance();
        carPage.clickNewVehicleLink();
        carPage.enterMobile(mobile);
        carPage.clickNewVehicleGetQuote();

        Assert.assertTrue(carPage.isSelectPlansPageDisplayed(), "Select Plans page not displayed");
        logger.info("Select Plans page displayed");

        carPage.enterCity(city);
        carPage.selectCity(city);
        carPage.enterCarMake(make);
        carPage.selectCarModel(model);

        Assert.assertTrue(driver.getCurrentUrl().contains("select-plans"), "User is not on Select Plans page");
        logger.info("User successfully reached Select Plans page");

        carPage.clickProceedBtnCity();
        boolean zeroDepSelected = carPage.selectZeroDepIfNotSelected();
        ScreenshotUtils.captureScreenshot(driver, "TC_16_Plan_Pricing");
        logger.info("Zero Dep selected : {}", zeroDepSelected);

        Assert.assertTrue(zeroDepSelected, "Zero Dep plan is not selected");
        verifyPlan("Zero Dep", carPage.getZeroDepAmount(), carPage.getTotalPremiumAmount(), carPage.getPlanAmount());
        loopPersonalProtectSums("Zero Dep");

        boolean noFrillsSelected = carPage.selectNoFrillsIfNotSelected();
        logger.info("No Frills selected : {}", noFrillsSelected);

        Assert.assertTrue(noFrillsSelected, "No Frills plan is not selected");
        verifyPlan("No Frills", carPage.getNoFrillsAmount(), carPage.getTotalPremiumAmount(), carPage.getPlanAmount());
        loopPersonalProtectSums("No Frills");

        boolean smartCoverSelected = carPage.selectSmartCoverIfNotSelected();
        logger.info("Smart Cover selected : {}", smartCoverSelected);

        Assert.assertTrue(smartCoverSelected, "Smart Cover plan is not selected");
        verifyPlan("Smart Cover", carPage.getSmartCoverAmount(), carPage.getTotalPremiumAmount(), carPage.getPlanAmount());

        loopPersonalProtectSums("Smart Cover");
        logger.info("TC_16 PASSED");
    }

    private void loopPersonalProtectSums(String planName) {

        String baseTotal = carPage.getTotalPremiumAmount();
        logger.info("Base Total for {} : {}", planName, baseTotal);
        carPage.openPersonalProtectDropdown();

        int optionCount = carPage.getPersonalProtectSumOptions().size();
        logger.info("Sum Assured options found for {} : {}", planName, optionCount);

        for (int i = 0; i < optionCount; i++) {
            carPage.openPersonalProtectDropdown();
            List<WebElement> options = carPage.getPersonalProtectSumOptions();
            WebElement option = options.get(i);
            String optionLabel = option.getText().trim();

            carPage.selectPersonalProtectSum(option);
            logger.info("{} | Sum Assured selected : {}", planName, optionLabel);

            carPage.togglePersonalProtect();
            String totalAfterAdd = carPage.getTotalPremiumAmount();
            String planAfterAdd = carPage.getPlanAmount();
            verifyAfterAddon(planName + " [" + optionLabel + "]", totalAfterAdd, planAfterAdd);

            carPage.togglePersonalProtect();
            String totalAfterRemove = carPage.getTotalPremiumAmount();
            logger.info("{} | After Remove Total : {}", planName, totalAfterRemove);
            Assert.assertEquals(
                    totalAfterRemove.replaceAll("[^0-9,]", ""),
                    baseTotal.replaceAll("[^0-9,]", ""),
                    planName + " Total did not reset after removing addon [" + optionLabel + "]");
        }
        logger.info("{} Personal Protect loop completed", planName);
    }

    private void verifyPlan(String planName, String cardAmount, String totalPremium, String planAmount) {
        logger.info("Verifying Plan : {}", planName);
        logger.info("{} Card Amount : {}", planName, cardAmount);
        logger.info("Total Premium Amount : {}", totalPremium);
        logger.info("Plan Amount : {}", planAmount);

        Assert.assertFalse(cardAmount.isEmpty(), planName + " amount is empty");
        Assert.assertTrue(totalPremium.contains(cardAmount.replace("₹", "").trim()), "Total Premium does not match " + planName + " amount");
        Assert.assertTrue(planAmount.contains(cardAmount.replace("₹", "").trim()), "Plan Amount does not match " + planName + " amount");
        logger.info("All three amounts match : {}", cardAmount);
    }

    private void verifyAfterAddon(String planName, String totalPremium, String planAmount) {
        logger.info("{} (After Personal Protect Add)", planName);
        logger.info("Total Premium Amount : {}", totalPremium);
        logger.info("Plan Amount : {}", planAmount);

        Assert.assertFalse(totalPremium.isEmpty(), "Total Premium empty after addon for " + planName);
        Assert.assertFalse(planAmount.isEmpty(), "Plan Amount empty after addon for " + planName);

        String totalDigits = totalPremium.replaceAll("[^0-9,]", "");
        String planDigits = planAmount.replaceAll("[^0-9,]", "");
        Assert.assertEquals(totalDigits, planDigits, "Total Premium and Plan Amount mismatch after Personal Protect Add for " + planName);
        logger.info("Total Premium and Plan Amount match after addon : {}", totalPremium);
    }
}