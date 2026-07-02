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

    // Car Registration Number
    @FindBy(id = "valid-carregnumber")
    public WebElement carRegistrationField;

    // Mobile Number
    @FindBy(id = "valid-mobilenumber")
    public WebElement mobileNumberField;

    // Email
    @FindBy(id = "valid-emailid")
    public WebElement emailField;

    public boolean isCarPageDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(carRegistrationField)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegistrationFieldDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(carRegistrationField)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMobileFieldDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(mobileNumberField)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailFieldDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(emailField)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}