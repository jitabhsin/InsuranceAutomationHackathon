package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarPage {

    WebDriver driver;
    WaitUtils waitUtils;

    public CarPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
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
}