package org.insurance.pages;

import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class CarPage {

    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    public CarPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "valid-carregnumber")
    public WebElement carRegistrationField;

    @FindBy(id = "valid-mobilenumber")
    public WebElement mobileNumberField;

    @FindBy(id = "valid-emailid")
    public WebElement emailField;

    @FindBy(id = "car-get-quote")
    public WebElement getQuoteButton;

    @FindBy(xpath = "//span[contains(@class,'ui-error')]")
    public WebElement errorMessage;

    @FindBy(xpath = "//a[contains(text(),'Got a new vehicle')]")
    public WebElement newVehicleLink;

    @FindBy(id = "car-registration-mob")
    public WebElement newVehicleMobileField;

    @FindBy(id = "car-registration-email")
    public WebElement newVehicleEmailField;

    @FindBy(id = "keyboardbindLast")
    public WebElement newVehicleGetQuoteButton;

    @FindBy(xpath = "//span[contains(text(),'Please enter a valid mobile number')]")
    public WebElement mobileErrorMessage;

    @FindBy(xpath = "//span[contains(text(),'Please enter valid email id')]")
    public WebElement emailErrorMessage;

    @FindBy(id = "carRg")
    public WebElement cityField;

    @FindBy(id = "makeModelName")
    public WebElement makeModelField;

    @FindBy(xpath = "//a[contains(text(),'Proceed')]")
    public WebElement proceedButton;

    @FindBy(xpath = "//a[normalize-space()='Proceed']")
    private WebElement proceedBtnCity;

    @FindBy(id = "checkODTPtpCaseValue")
    private WebElement ownDamageTpOption;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'Zero Dep')]]")
    private WebElement zeroDepPlan;

    @FindBy(xpath = "//div[contains(@class,'pageLoader')]")
    private WebElement pageLoader;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'Zero Dep')]]//span[@class='premium-amount']")
    private WebElement zeroDepAmount;

    @FindBy(xpath = "//span[contains(@class,'extraboldTxt') and contains(.,'₹')]")
    private WebElement totalPremiumAmount;

    @FindBy(xpath = "//span[@class='plan-amount']")
    private WebElement planAmount;

    @FindBy(xpath = "//a[normalize-space()='Expand']")
    private WebElement expandLink;

    @FindBy(xpath = "//li[p[normalize-space()='City of registration']]/span")
    private WebElement cityOfRegistration;

    @FindBy(xpath = "//h3[normalize-space()='Personal Protect Policy']/following::button[contains(@class,'js-added-btn')][1]")
    private WebElement personalProtectToggleBtn;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'No Frills')]]")
    private WebElement noFrillsPlan;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'Smart Cover')]]")
    private WebElement smartCoverPlan;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'No Frills')]]//span[@class='premium-amount']")
    private WebElement noFrillsAmount;

    @FindBy(xpath = "//button[.//strong[contains(normalize-space(),'Smart Cover')]]//span[@class='premium-amount']")
    private WebElement smartCoverAmount;

    @FindBy(xpath = "//h3[normalize-space()='Personal Protect Policy']/following::span[@class='premium-amount' or contains(@class,'plan-price')][1]")
    private WebElement personalProtectDisplayedPrice;

    @FindBy(xpath = "//h3[normalize-space()='Personal Protect Policy']/following::input[@placeholder='Select Sum Assured'][1]")
    private WebElement personalProtectSumDropdown;

    @FindBy(xpath = "//h3[normalize-space()='Personal Protect Policy']/following::ul[1]//li")
    private java.util.List<WebElement> personalProtectSumOptions;

    @FindBy(id = "checklongtermtpCaseValue")
    private WebElement longTermPolicyOption;

    @FindBy(xpath = "//label[@for='checklongtermtpCaseValue']")
    private WebElement longTermPolicyLabel;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    private WebElement continueBtn;

    @FindBy(xpath = "//h1[contains(normalize-space(),'Please verify the information below')]")
    private WebElement verifyInfoHeader;

    @FindBy(xpath = "//h1[contains(normalize-space(),'Please verify')]/following::p[1]")
    private WebElement verifyInfoSubtext;

    @FindBy(id = "carRg")
    private WebElement verifyCityInput;

    @FindBy(id = "makeModelName")
    private WebElement verifyMakeModelInput;

    @FindBy(id = "carPurchaseDate")
    private WebElement verifyRegDateInput;

    @FindBy(xpath = "//span[contains(@class,'reg-un-indvalue')]")
    private WebElement verifyRegUnderIndividual;

    @FindBy(xpath = "//p[contains(normalize-space(),'valid and effective PUC certificate')]")
    private WebElement verifyPucNote;

    @FindBy(xpath = "//a[normalize-space()='Modify']")
    private WebElement modifyBtn;

    @FindBy(id = "buyId")
    private WebElement thatsCorrectBtn;

    @FindBy(id = "buyId")
    private WebElement thatsCorrectButton;

    @FindBy(xpath = "//a[contains(@class,'js_closePopup')]")
    private WebElement kycPopupCloseBtn;

    @FindBy(xpath = "//div[@class='il-car-details-head' and normalize-space()='Car detail']/following-sibling::div")
    private WebElement insuredCarDetail;

    @FindBy(xpath = "//div[@class='il-car-details-head' and normalize-space()='Registration no.']/following-sibling::div")
    private WebElement insuredRegNo;

    @FindBy(xpath = "//div[@class='il-car-details-head' and normalize-space()='Insured declared value (IDV)']/following-sibling::div")
    private WebElement insuredIdv;

    @FindBy(xpath = "//h3[normalize-space()='Premium summary']")
    private WebElement premiumSummaryHeader;

    @FindBy(xpath = "//li[p[normalize-space()='Base premium']]/span")
    private WebElement insuredBasePremium;

    @FindBy(xpath = "//li[p[contains(normalize-space(),'Additional covers')]]/span[contains(@class,'ng-star-inserted')]")
    private WebElement insuredAdditionalCovers;

    @FindBy(xpath = "//li[p[normalize-space()='Sub Total']]/span")
    private WebElement insuredSubTotal;

    @FindBy(xpath = "//li[p[normalize-space()='Net Premium']]/span")
    private WebElement insuredNetPremium;

    @FindBy(xpath = "//li[p[contains(normalize-space(),'Tax')]]/span[contains(@class,'ng-star-inserted')]")
    private WebElement insuredTax;

    @FindBy(xpath = "//li[p[normalize-space()='Total Premium']]/span")
    private WebElement insuredTotalPremium;

    @FindBy(xpath = "//a[normalize-space()='Proceed to pay']")
    private WebElement proceedToPayBtn;

    public boolean isLongTermPolicySelected() {
        return waitUtils.waitForSelected(longTermPolicyOption);
    }

    public boolean isCarPageDisplayed() {
        try { return waitUtils.waitForVisibility(carRegistrationField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isRegistrationFieldDisplayed() {
        try { return waitUtils.waitForVisibility(carRegistrationField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isMobileFieldDisplayed() {
        try { return waitUtils.waitForVisibility(mobileNumberField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isEmailFieldDisplayed() {
        try { return waitUtils.waitForVisibility(emailField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public void clickGetQuote() {
        waitUtils.waitForClickable(getQuoteButton).click();
    }

    public boolean isValidationMessageDisplayed() {
        try { return waitUtils.waitForVisibility(errorMessage).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public String getValidationMessage() {
        return waitUtils.waitForVisibility(errorMessage).getText().trim();
    }

    public void clickNewVehicleLink() {
        waitUtils.waitForClickable(newVehicleLink).click();
    }

    public boolean isNewVehiclePageDisplayed() {
        try { return waitUtils.waitForVisibility(newVehicleMobileField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isNewVehicleMobileFieldDisplayed() {
        try { return waitUtils.waitForVisibility(newVehicleMobileField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isNewVehicleEmailFieldDisplayed() {
        try { return waitUtils.waitForVisibility(newVehicleEmailField).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public void clickNewVehicleGetQuote() {
        waitUtils.waitForClickable(newVehicleGetQuoteButton).click();
    }

    public String getMobileErrorMessage() {
        return waitUtils.waitForVisibility(mobileErrorMessage).getText().trim();
    }

    public String getEmailErrorMessage() {
        return waitUtils.waitForVisibility(emailErrorMessage).getText().trim();
    }

    public void enterCity(String city) {
        WebElement el = waitUtils.waitForVisibility(cityField);
        el.clear();
        el.sendKeys(city);
    }

    public void enterCarMake(String make) {
        WebElement el = waitUtils.waitForVisibility(makeModelField);
        el.clear();
        el.sendKeys(make);
    }

    public void enterMobile(String mobile) {
        WebElement el = waitUtils.waitForVisibility(newVehicleMobileField);
        el.clear();
        el.sendKeys(mobile);
    }

    public void enterEmail(String email) {
        WebElement el = waitUtils.waitForVisibility(newVehicleEmailField);
        el.clear();
        el.sendKeys(email);
    }

    public void clickProceed() {
        waitUtils.waitForClickable(proceedButton).click();
    }

    public boolean isSelectPlansPageDisplayed() {
        return waitUtils.waitForUrlContains("select-plans");
    }

    public void clickExpand() {
        waitUtils.waitForVisibility(expandLink);
        jsUtils.scrollToElement(expandLink);
        jsUtils.jsClick(expandLink);
    }

    public String getCityOfRegistration() {
        return waitUtils.waitForVisibility(cityOfRegistration).getText().trim();
    }

    public String getPlanAmount() {
        return waitUtils.waitForVisibility(planAmount).getText().trim();
    }

    public String getTotalPremiumAmount() {
        WebElement element = waitUtils.waitForVisibilityIgnoringStale(totalPremiumAmount);
        jsUtils.scrollToElement(element);
        return element.getText().replaceAll("\\+.*", "").trim();
    }

    public String getZeroDepAmount() {
        return waitUtils.waitForVisibilityIgnoringStale(zeroDepAmount).getText().trim();
    }

    public void clickProceedBtnCity() {
        waitUtils.waitForVisibility(proceedBtnCity);
        jsUtils.scrollToElement(proceedBtnCity);
        jsUtils.jsClick(proceedBtnCity);
    }

    public boolean checkODTP() {
        return waitUtils.waitForSelected(ownDamageTpOption);
    }

    public String getNoFrillsAmount() {
        return waitUtils.waitForVisibilityIgnoringStale(noFrillsAmount).getText().trim();
    }

    public String getSmartCoverAmount() {
        return waitUtils.waitForVisibilityIgnoringStale(smartCoverAmount).getText().trim();
    }

    public void togglePersonalProtect() {
        waitUtils.waitForVisibility(personalProtectToggleBtn);
        jsUtils.scrollToElement(personalProtectToggleBtn);
        jsUtils.jsClick(personalProtectToggleBtn);
    }

    public boolean isNoFrillsCardDisplayed() {
        try {
            return waitUtils.waitForVisibilityIgnoringStale(noFrillsPlan).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isZeroDepCardDisplayed() {
        try {
            return waitUtils.waitForVisibilityIgnoringStale(zeroDepPlan).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSmartCoverCardDisplayed() {
        try {
            return waitUtils.waitForVisibilityIgnoringStale(smartCoverPlan).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isExpandLinkDisplayed() {
        try { return waitUtils.waitForVisibility(expandLink).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isPersonalProtectDisplayed() {
        try { return waitUtils.waitForVisibility(personalProtectToggleBtn).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public void openPersonalProtectDropdown() {
        waitUtils.waitForVisibility(personalProtectSumDropdown);
        jsUtils.scrollToElement(personalProtectSumDropdown);
        jsUtils.jsClick(personalProtectSumDropdown);
    }

    public List<WebElement> getPersonalProtectSumOptions() {
        return personalProtectSumOptions;
    }

    public void selectPersonalProtectSum(WebElement option) {
        waitUtils.waitForVisibility(option);
        jsUtils.jsClick(option);
    }

    public boolean selectLongTermPolicy() {
        waitUtils.waitForVisibility(longTermPolicyLabel);
        jsUtils.scrollToElement(longTermPolicyLabel);
        if (!longTermPolicyOption.isSelected()) {
            jsUtils.jsClick(longTermPolicyLabel);
        }
        return waitUtils.waitForSelected(longTermPolicyOption);
    }

    public void clickContinue() {
        waitUtils.waitForVisibility(continueBtn);
        jsUtils.scrollToElement(continueBtn);
        jsUtils.jsClick(continueBtn);
    }

    public boolean isVerifyInfoHeaderDisplayed() {
        try { return waitUtils.waitForVisibility(verifyInfoHeader).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public String getVerifyInfoSubtext() {
        return waitUtils.waitForVisibility(verifyInfoSubtext).getText().trim();
    }

    public String getVerifyCity() {
        return waitUtils.waitForVisibility(verifyCityInput).getAttribute("value").trim();
    }

    public String getVerifyMakeModel() {
        return waitUtils.waitForVisibility(verifyMakeModelInput).getAttribute("value").trim();
    }

    public String getVerifyRegDate() {
        return waitUtils.waitForVisibility(verifyRegDateInput).getAttribute("value").trim();
    }

    public String getVerifyRegUnderIndividual() {
        return waitUtils.waitForVisibility(verifyRegUnderIndividual).getText().trim();
    }

    public String getVerifyPucNote() {
        return waitUtils.waitForVisibility(verifyPucNote).getText().trim();
    }

    public boolean isModifyBtnDisplayed() {
        try { return waitUtils.waitForVisibility(modifyBtn).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isThatsCorrectBtnDisplayed() {
        try { return waitUtils.waitForVisibility(thatsCorrectBtn).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public void clickThatsCorrect() {
        waitUtils.waitForVisibility(thatsCorrectButton);
        jsUtils.scrollToElement(thatsCorrectButton);
        jsUtils.jsClick(thatsCorrectButton);
    }

    public void closeKycPopupIfPresent() {
        try {
            if (waitUtils.waitForVisibility(kycPopupCloseBtn).isDisplayed()) {
                jsUtils.jsClick(kycPopupCloseBtn);
                System.out.println("KYC popup closed");
            }
        } catch (Exception e) {
            System.out.println("KYC popup not shown");
        }
    }

    public boolean isInsuredDetailsPage() {
        return waitUtils.waitForUrlContains("insured-details");
    }

    public boolean isPremiumSummaryDisplayed() {
        try {
            return waitUtils.waitForVisibilityIgnoringStale(premiumSummaryHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProceedToPayEnabled() {
        try { return waitUtils.waitForClickable(proceedToPayBtn).isEnabled(); }
        catch (Exception e) { return false; }
    }
    public void selectCity(String city) {
        driver.findElement(By.xpath("//li[contains(text(),'" + city + "')]")).click();
    }

    public void selectCarModel(String model) {
        driver.findElement(By.xpath("//li[contains(text(),'" + model + "')]")).click();
    }

    public boolean selectZeroDepIfNotSelected() {
        WebElement plan = waitUtils.waitForVisibilityIgnoringStale(zeroDepPlan);

        if (!plan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(plan);
        }

        return waitUtils.waitForAttributeContains(plan, "class", "plans-box-active");
    }

    public boolean selectNoFrillsIfNotSelected() {
        WebElement plan = waitUtils.waitForVisibilityIgnoringStale(noFrillsPlan);

        if (!plan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(plan);
        }

        return waitUtils.waitForAttributeContains(plan, "class", "plans-box-active");
    }

    public boolean selectSmartCoverIfNotSelected() {
        WebElement plan = waitUtils.waitForVisibilityIgnoringStale(smartCoverPlan);

        if (!plan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(plan);
        }

        return waitUtils.waitForAttributeContains(plan, "class", "plans-box-active");
    }

    public String getInsuredCarDetail()       { return waitUtils.waitForVisibility(insuredCarDetail).getText().trim(); }
    public String getInsuredRegNo()           { return waitUtils.waitForVisibility(insuredRegNo).getText().trim(); }
    public String getInsuredIdv()             { return waitUtils.waitForVisibility(insuredIdv).getText().trim(); }
    public String getInsuredBasePremium()     { return waitUtils.waitForVisibility(insuredBasePremium).getText().trim(); }
    public String getInsuredAdditionalCovers(){ return waitUtils.waitForVisibility(insuredAdditionalCovers).getText().trim(); }
    public String getInsuredSubTotal()        { return waitUtils.waitForVisibility(insuredSubTotal).getText().trim(); }
    public String getInsuredNetPremium()      { return waitUtils.waitForVisibility(insuredNetPremium).getText().trim(); }
    public String getInsuredTax()             { return waitUtils.waitForVisibility(insuredTax).getText().trim(); }
    public String getInsuredTotalPremium()    { return waitUtils.waitForVisibility(insuredTotalPremium).getText().trim(); }
}