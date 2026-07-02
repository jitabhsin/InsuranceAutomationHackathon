package org.insurance.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.insurance.utils.WaitUtils;

public class HomePage {

    WebDriver driver;
    WaitUtils waitUtils;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[contains(@class,'il-travel-img') or contains(text(),'Travel Insurance')]")
    public WebElement travelInsuranceElement;

    @FindBy(xpath="//span[normalize-space()='Car']/parent::div")
    public WebElement carInsuranceElement;

    @FindBy(xpath="//*[contains(@class,'il-health-img') or contains(text(),'Health Insurance')]")
    public WebElement healthInsuranceElement;

    public void clickTravelInsurance(){
        waitUtils.waitForClickable(travelInsuranceElement).click();
    }

    public void clickCarInsurance(){

        waitUtils.waitForVisibility(carInsuranceElement);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        carInsuranceElement);
    }

    public void clickHealthInsurance(){
        waitUtils.waitForClickable(healthInsuranceElement).click();
    }

    public boolean isCarInsurancePresent() {
        try {
            return waitUtils
                    .waitForVisibility(carInsuranceElement)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHomePageDisplayed() {
        try {
            return driver.getTitle() != null &&
                    !driver.getTitle().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}