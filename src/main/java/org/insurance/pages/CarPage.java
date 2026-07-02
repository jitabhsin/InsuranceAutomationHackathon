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

    // Get Quote Button
    @FindBy(id = "car-get-quote")
    public WebElement getQuoteButton;

    // Validation Message
    @FindBy(xpath = "//span[contains(@class,'ui-error')]")
    public WebElement errorMessage;

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

    public void clickGetQuote() {

        waitUtils
                .waitForClickable(getQuoteButton)
                .click();
    }

    public boolean isValidationMessageDisplayed() {

        try {

            return waitUtils
                    .waitForVisibility(errorMessage)
                    .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getValidationMessage() {

        return waitUtils
                .waitForVisibility(errorMessage)
                .getText()
                .trim();
    }
}