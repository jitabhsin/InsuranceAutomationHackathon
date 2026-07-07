package org.insurance.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;
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

    @FindBy(xpath = "//h4[normalize-space()='Essential']")
    WebElement essentialPlanHeader;

    @FindBy(xpath = "//h4[normalize-space()='Essential']/ancestor::div[contains(@class,'plan-curated-heading')]//span[contains(@class,'premium-amnt')]")
    WebElement essentialPremiumAmount;

    @FindBy(xpath = "//h4[normalize-space()='Essential']/ancestor::div[contains(@class,'plan-curated-heading')]//p[contains(@class,'medcover-txt')]/span")
    WebElement essentialMedicalCover;

    @FindBy(xpath = "//h4[normalize-space()='Essential']/ancestor::div[contains(@class,'plan-curated-block')]//a[normalize-space()='See all']")
    WebElement essentialSeeAllBtn;

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

    @FindBy(xpath = "//h4[normalize-space()='Premium']")
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

    public boolean isNextCoverageEnabled() {

        String classValue = nextCoverageBtn.getAttribute("class");

        return classValue.contains("active") && !classValue.contains("disabled");
    }


    public Map<String, Double> getCurrentQuotes() {

        Map<String, Double> quotes =
                new LinkedHashMap<>();

        int size =
                Math.min(
                        providerNames.size(),
                        premiumAmounts.size());

        for (int i = 0; i < size; i++) {

            String provider =
                    providerNames.get(i)
                            .getText()
                            .trim();

            String premiumText =
                    premiumAmounts.get(i)
                            .getText()
                            .replace("₹", "")
                            .replace(",", "")
                            .trim();

            if (!provider.isEmpty()
                    && !premiumText.isEmpty()) {

                quotes.put(
                        provider,
                        Double.parseDouble(premiumText));
            }
        }

        return quotes;
    }

    public Map<String, Double> getAllQuotes() {

        Map<String, Double> allQuotes =
                new LinkedHashMap<>();

        while (true) {

            allQuotes.putAll(
                    getCurrentQuotes());

            if (!isNextCoverageEnabled()) {
                break;
            }

            String previousProvider =
                    providerNames.get(0)
                            .getText();

            clickNextCoverage();

            waitUtils.waitForTextChange(
                    providerNames.get(0),
                    previousProvider);
        }

        return allQuotes;
    }

    public void verifyPlan(
            String planName,
            boolean displayed,
            String premium,
            String medicalCover) {

        if (!displayed) {
            throw new AssertionError(
                    planName + " Plan not displayed");
        }

        if (premium.trim().isEmpty()) {
            throw new AssertionError(
                    planName + " Premium Missing");
        }

        if (medicalCover.trim().isEmpty()) {
            throw new AssertionError(
                    planName + " Medical Cover Missing");
        }

        System.out.println("----------------------------------");
        System.out.println("PLAN NAME     : " + planName);
        System.out.println("PREMIUM       : " + premium);
        System.out.println("MEDICAL COVER : " + medicalCover);
        System.out.println("----------------------------------");
    }
}