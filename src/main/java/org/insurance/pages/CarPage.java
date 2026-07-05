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

    public boolean isCarPageDisplayed() {
        try {
            return waitUtils.waitForVisibility(carRegistrationField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegistrationFieldDisplayed() {
        try {
            return waitUtils.waitForVisibility(carRegistrationField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMobileFieldDisplayed() {
        try {
            return waitUtils.waitForVisibility(mobileNumberField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailFieldDisplayed() {
        try {
            return waitUtils.waitForVisibility(emailField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickGetQuote() {
        waitUtils.waitForClickable(getQuoteButton).click();
    }

    public boolean isValidationMessageDisplayed() {
        try {
            return waitUtils.waitForVisibility(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getValidationMessage() {
        return waitUtils.waitForVisibility(errorMessage).getText().trim();
    }

    public void clickNewVehicleLink() {
        waitUtils.waitForClickable(newVehicleLink).click();
    }

    public boolean isNewVehiclePageDisplayed() {
        try {
            return waitUtils.waitForVisibility(newVehicleMobileField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNewVehicleMobileFieldDisplayed() {
        try {
            return waitUtils.waitForVisibility(newVehicleMobileField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNewVehicleEmailFieldDisplayed() {
        try {
            return waitUtils.waitForVisibility(newVehicleEmailField).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
        waitUtils.waitForVisibility(cityField).clear();
        cityField.sendKeys(city);
    }

    public void selectCity(String city) {
        driver.findElement(By.xpath("//li[contains(text(),'" + city + "')]")).click();
    }

    public void enterCarMake(String make) {
        waitUtils.waitForVisibility(makeModelField).clear();
        makeModelField.sendKeys(make);
    }

    public void selectCarModel(String model) {
        driver.findElement(By.xpath("//li[contains(text(),'" + model + "')]")).click();
    }

    public void enterMobile(String mobile) {
        waitUtils.waitForVisibility(newVehicleMobileField).clear();
        newVehicleMobileField.sendKeys(mobile);
    }

    public void enterEmail(String email) {
        waitUtils.waitForVisibility(newVehicleEmailField).clear();
        newVehicleEmailField.sendKeys(email);
    }

    public void clickProceed() {
        waitUtils.waitForClickable(proceedButton).click();
    }

    public boolean isSelectPlansPageDisplayed() {
        return driver.getCurrentUrl().contains("select-plans");
    }

    public void clickExpand() {
        jsUtils.scrollToElement(expandLink);
        waitUtils.waitForClickable(expandLink).click();
    }

    public String getCityOfRegistration() {
        return waitUtils.waitForVisibility(cityOfRegistration).getText().trim();
    }

    public String getPlanAmount() {
        return waitUtils.waitForVisibility(planAmount).getText().trim();
    }

    public String getTotalPremiumAmount() {
        jsUtils.scrollToElement(totalPremiumAmount);
        return waitUtils.waitForVisibility(totalPremiumAmount).getText().replaceAll("\\+.*","").trim();
    }

    public String getZeroDepAmount() {
        return waitUtils.waitForVisibility(zeroDepAmount).getText().trim();
    }

    public void clickProceedBtnCity(){
        waitUtils.waitForClickable(proceedBtnCity).click();
    }

    public boolean checkODTP() {
        return waitUtils.waitForSelected(ownDamageTpOption);
    }
    public boolean selectZeroDepIfNotSelected() {
        if (!zeroDepPlan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(zeroDepPlan);
        }
        return waitUtils.waitForAttributeContains(zeroDepPlan, "class", "plans-box-active");
    }

    public boolean selectNoFrillsIfNotSelected() {
        if (!noFrillsPlan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(noFrillsPlan);
        }
        return waitUtils.waitForAttributeContains(noFrillsPlan, "class", "plans-box-active");
    }

    public boolean selectSmartCoverIfNotSelected() {
        if (!smartCoverPlan.getAttribute("class").contains("plans-box-active")) {
            jsUtils.jsClick(smartCoverPlan);
        }
        return waitUtils.waitForAttributeContains(smartCoverPlan, "class", "plans-box-active");
    }

    public String getNoFrillsAmount() {
        return waitUtils.waitForVisibility(noFrillsAmount).getText().trim();
    }

    public String getSmartCoverAmount() {
        return waitUtils.waitForVisibility(smartCoverAmount).getText().trim();
    }

    public void togglePersonalProtect() {
        jsUtils.scrollToElement(personalProtectToggleBtn);
        jsUtils.jsClick(personalProtectToggleBtn);
    }

    public boolean isNoFrillsCardDisplayed()  { try { return waitUtils.waitForVisibility(noFrillsPlan).isDisplayed(); } catch (Exception e) { return false; } }
    public boolean isZeroDepCardDisplayed()   { try { return waitUtils.waitForVisibility(zeroDepPlan).isDisplayed(); } catch (Exception e) { return false; } }
    public boolean isSmartCoverCardDisplayed(){ try { return waitUtils.waitForVisibility(smartCoverPlan).isDisplayed(); } catch (Exception e) { return false; } }

    public boolean isExpandLinkDisplayed() {
        try { return waitUtils.waitForVisibility(expandLink).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isPersonalProtectDisplayed() {
        try { return waitUtils.waitForVisibility(personalProtectToggleBtn).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public void openPersonalProtectDropdown() {
        jsUtils.scrollToElement(personalProtectSumDropdown);
        jsUtils.jsClick(personalProtectSumDropdown);
    }

    public List<WebElement> getPersonalProtectSumOptions() {
        return personalProtectSumOptions;
    }

    public void selectPersonalProtectSum(WebElement option) {
        jsUtils.jsClick(option);
    }
}