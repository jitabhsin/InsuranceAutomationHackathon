package org.insurance.pages;

import java.util.*;

import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelQuotePage {

    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    public TravelQuotePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // =====================================
    // PAGE LEVEL ELEMENTS
    // =====================================

    @FindBy(xpath = "//h3[contains(@class,'travel-plan-title')]")
    WebElement benefitsCuratedTitle;

    @FindBy(xpath = "//span[normalize-space()='Share quote']")
    WebElement shareQuoteBtn;

    @FindBy(id = "compare-plan-btn")
    WebElement compareBenefitsBtn;

    @FindBy(xpath = "//a[contains(@class,'next-coverage')]")
    WebElement nextCoverageBtn;

    @FindBy(xpath = "//a[contains(@class,'prev-coverage')]")
    WebElement previousCoverageBtn;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-block')]")
    List<WebElement> availablePlans;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-block')]//h4")
    List<WebElement> providerNames;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-block')]//span[contains(@class,'premium-amnt')]")
    List<WebElement> premiumAmounts;

    // =====================================
    // ESSENTIAL PLAN
    // =====================================

    @FindBy(xpath = "//h4[contains(normalize-space(),'Essential')]")
    WebElement essentialPlanHeader;

    @FindBy(xpath = "//h4[normalize-space()='Essential']/ancestor::div[contains(@class,'plan-curated-heading')]//span[contains(@class,'premium-amnt')]")
    WebElement essentialPremiumAmount;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-block')]//h4[contains(normalize-space(),'Essential')]/ancestor::div[contains(@class,'plan-curated-block')]//p[contains(@class,'medcover-txt')]/span")
    WebElement essentialMedicalCover;

    @FindBy(xpath = "//div[contains(@class,'dropdown')]//input[contains(@class,'selected')]")
    List<WebElement> medicalCoverDropdowns;

    @FindBy(xpath = "//span[contains(@class,'si-amount')]")
    List<WebElement> allCoverAmounts;

    @FindBy(xpath = "//li[contains(@class,'active')]//span[contains(@class,'si-amount')]")
    List<WebElement> selectedCoverAmounts;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-list')]//span[contains(@class,'si-amount')]")
    List<WebElement> medicalCoverOptions;

    @FindBy(xpath =
            "//div[contains(@class,'plan-curated-list')]")
    List<WebElement> planCards;

    @FindBy(xpath = "//h4[normalize-space()='Essential']/ancestor::div[contains(@class,'plan-curated-block')]//a[normalize-space()='See all']")
    WebElement essentialSeeAllBtn;


    @FindBy(xpath = "//span[contains(@class,'si-amount')]")
    List<WebElement> allMedicalCoverOptions;

    @FindBy(xpath = "//p[contains(@class,'medcover-txt')]/span")
    List<WebElement> selectedMedicalCovers;

    @FindBy(xpath = "//div[contains(@class,'dropdown')]//input[contains(@class,'selected')]")
    private List<WebElement> dropdownInputs;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-heading')]//h4")
    private List<WebElement> visiblePlanNames;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-heading')]//span[contains(@class,'premium-amnt')]")
    private List<WebElement> visiblePremiums;

    @FindBy(xpath = "//div[contains(@class,'plan-curated-heading')]//p[contains(@class,'medcover-txt')]/span")
    private List<WebElement> visibleMedicalCovers;

    // =====================================
    // VALUE PLAN
    // =====================================

    @FindBy(xpath = "//h4[contains(normalize-space(),'Value')]")
    WebElement valuePlanHeader;

    @FindBy(xpath = "//p[normalize-space()='Recommended']")
    WebElement recommendedTag;

    @FindBy(xpath = "//h4[contains(normalize-space(),'Value')]/ancestor::div[contains(@class,'plan-curated-heading')]//span[contains(@class,'premium-amnt')]")
    WebElement valuePremiumAmount;

    @FindBy(xpath = "//h4[contains(normalize-space(),'Value')]/ancestor::div[contains(@class,'plan-curated-heading')]//p[contains(@class,'medcover-txt')]/span")
    WebElement valueMedicalCover;

    @FindBy(xpath = "//h4[contains(normalize-space(),'Value')]/ancestor::div[contains(@class,'plan-curated-block')]//a[normalize-space()='See all']")
    WebElement valueSeeAllBtn;

    @FindBy(xpath = "//div[contains(@class,'powerai-block')]")
    WebElement poweredByAI;

    // =====================================
    // PREMIUM PLAN
    // =====================================

    @FindBy(xpath = "//h4[contains(normalize-space(),'Premium')]")
    WebElement premiumPlanHeader;

    @FindBy(xpath = "//h4[normalize-space()='Premium']/ancestor::div[contains(@class,'plan-curated-heading')]//span[contains(@class,'premium-amnt')]")
    WebElement premiumAmount;

    @FindBy(xpath = "//h4[normalize-space()='Premium']/ancestor::div[contains(@class,'plan-curated-heading')]//p[contains(@class,'medcover-txt')]/span")
    WebElement premiumMedicalCover;

    @FindBy(xpath = "//h4[normalize-space()='Premium']/ancestor::div[contains(@class,'plan-curated-block')]//a[normalize-space()='See all']")
    WebElement premiumSeeAllBtn;

    // =====================================
    // BENEFITS
    // =====================================

    @FindBy(xpath = "//div[contains(@class,'plan-benefit-list')]//li")
    List<WebElement> allBenefits;

    @FindBy(xpath = "//h5[contains(text(),'benefits includes')]")
    List<WebElement> benefitHeaders;

    // =====================================
    // KEY HIGHLIGHTS
    // =====================================

    @FindBy(xpath = "//h6[contains(text(),'Key Highlight')]")
    List<WebElement> keyHighlightHeaders;

    @FindBy(xpath = "//div[contains(@class,'key-details')]")
    List<WebElement> keyHighlightItems;

    // =====================================
    // METHODS
    // =====================================

    public String getBenefitsTitle() {
        return waitUtils.waitForVisibility(benefitsCuratedTitle).getText();
    }

    public boolean isShareQuoteDisplayed() {
        return waitUtils.waitForVisibility(shareQuoteBtn).isDisplayed();
    }

    public boolean isCompareBenefitsDisplayed() {
        return waitUtils.waitForVisibility(compareBenefitsBtn).isDisplayed();
    }

    public int getPlanCount() {
        return availablePlans.size();
    }

    public boolean isEssentialPlanDisplayed() {
        return waitUtils.waitForVisibility(essentialPlanHeader).isDisplayed();
    }

    public boolean isValuePlanDisplayed() {
        return waitUtils.waitForVisibility(valuePlanHeader).isDisplayed();
    }

    public boolean isPremiumPlanDisplayed() {
        return waitUtils.waitForVisibility(premiumPlanHeader).isDisplayed();
    }

    public String getEssentialPremium() {
        return essentialPremiumAmount.getText();
    }

    public String getValuePremium() {
        return valuePremiumAmount.getText();
    }

    public String getPremiumAmount() {
        return premiumAmount.getText();
    }

    public String getEssentialMedicalCover() {
        return waitUtils.waitForVisibility(essentialMedicalCover).getText().trim();
    }

    public String getValueMedicalCover() {
        return waitUtils.waitForClickable(valueMedicalCover).getText();
    }

    public String getPremiumMedicalCover() {
        return waitUtils.waitForVisibility(premiumMedicalCover).getText();
    }

    public boolean isRecommendedTagDisplayed() {
        return recommendedTag.isDisplayed();
    }

    public boolean isPoweredByAIDisplayed() {
        return poweredByAI.isDisplayed();
    }

    public void clickCompareBenefits() {
        waitUtils.waitForClickable(compareBenefitsBtn).click();
    }

    public void clickShareQuote() {
        waitUtils.waitForClickable(shareQuoteBtn).click();
    }

    public void clickNextCoverage() {
        waitUtils.waitForClickable(nextCoverageBtn).click();
    }

    public void clickPreviousCoverage() {
        waitUtils.waitForClickable(previousCoverageBtn).click();
    }

    public int getBenefitCount() {
        return allBenefits.size();
    }

    public int getKeyHighlightCount() {
        return keyHighlightItems.size();
    }

    public void navigateToLastCoverage() {

        while (isNextCoverageEnabled()) {

            String lastPremium =
                    visiblePremiums.get(visiblePremiums.size() - 1).getText();

            clickNextCoverage();

            waitUtils.waitForTextChange(
                    visiblePremiums.get(visiblePremiums.size() - 1),
                    lastPremium);
        }
    }

    public void navigateToFirstCoverage() {

        while (true) {

            String className =
                    previousCoverageBtn.getAttribute("class");

            if (className.contains("disabled")) {
                break;
            }

            String firstPremium =
                    visiblePremiums.get(0).getText();

            clickPreviousCoverage();

            waitUtils.waitForTextChange(
                    visiblePremiums.get(0),
                    firstPremium);
        }
    }
    public boolean isPreviousCoverageEnabled() {

        waitUtils.waitForVisibility(previousCoverageBtn);

        String classValue = previousCoverageBtn.getAttribute("class");

        return classValue.contains("active")
                && !classValue.contains("disabled");
    }

    public void clickMedicalCoverDropdown(int index) {

        WebElement dropdown = dropdownInputs.get(index);

        jsUtils.scrollToElement(dropdown);

        waitUtils.waitForClickable(dropdown).click();
    }

    public List<String> getDropdownValues() {

        List<String> values = new ArrayList<>();

        for (WebElement option : allMedicalCoverOptions) {

            String value = option.getText().trim();

            if (!value.isEmpty()) {
                values.add(value);
            }
        }

        return values;
    }

    public String selectLowestCoverage(int dropdownIndex) {

        clickMedicalCoverDropdown(dropdownIndex);

        String lowestCover =
                allMedicalCoverOptions.get(0).getText();

        String previousPremium =
                visiblePremiums.get(dropdownIndex).getText();

        allMedicalCoverOptions.get(0).click();

        waitUtils.waitForTextChange(
                visiblePremiums.get(dropdownIndex),
                previousPremium);

        return lowestCover;
    }

    public int convertPremiumToInteger(String premium) {

        premium = premium.replace("₹", "");
        premium = premium.replace(",", "");
        premium = premium.trim();

        return Integer.parseInt(premium);
    }

    public List<PlanDetails> getVisiblePlans() {

        List<PlanDetails> plans = new ArrayList<>();

        for (int i = 0; i < visiblePlanNames.size(); i++) {

            plans.add(
                    new PlanDetails(
                            visiblePlanNames.get(i).getText(),
                            visibleMedicalCovers.get(i).getText(),
                            visiblePremiums.get(i).getText()));
        }

        return plans;
    }

    public List<PlanDetails> getAllDisplayedPlans() {

        List<PlanDetails> allPlans =
                new ArrayList<>();

        navigateToFirstCoverage();

        while (true) {

            List<PlanDetails> currentPlans =
                    getVisiblePlans();

            for (PlanDetails p : currentPlans) {

                boolean exists = false;

                for (PlanDetails existing : allPlans) {

                    if (existing.getPlanName()
                            .equalsIgnoreCase(p.getPlanName())) {

                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    allPlans.add(p);
                }
            }

            if (!isNextCoverageEnabled()) {
                break;
            }

            String lastPremium =
                    visiblePremiums.get(
                                    visiblePremiums.size()-1)
                            .getText();

            clickNextCoverage();

            waitUtils.waitForTextChange(
                    visiblePremiums.get(
                            visiblePremiums.size()-1),
                    lastPremium);
        }

        return allPlans;
    }

    public List<PlanDetails> getLowestThreePlans() {

        List<PlanDetails> plans =
                getAllDisplayedPlans();

        for (int i = 0; i < plans.size(); i++) {

            for (int j = i + 1; j < plans.size(); j++) {

                int premium1 =
                        convertPremiumToInteger(
                                plans.get(i)
                                        .getPremiumAmount());

                int premium2 =
                        convertPremiumToInteger(
                                plans.get(j)
                                        .getPremiumAmount());

                if (premium2 < premium1) {

                    PlanDetails temp = plans.get(i);
                    plans.set(i, plans.get(j));
                    plans.set(j, temp);
                }
            }
        }

        List<PlanDetails> lowestThree =
                new ArrayList<>();

        for (int i = 0;
             i < Math.min(3, plans.size());
             i++) {

            lowestThree.add(plans.get(i));
        }

        return lowestThree;
    }

    public boolean isNextCoverageEnabled() {

        waitUtils.waitForVisibility(nextCoverageBtn);

        String classValue = nextCoverageBtn.getAttribute("class");

        return classValue.contains("active")
                && !classValue.contains("disabled");
    }


}
