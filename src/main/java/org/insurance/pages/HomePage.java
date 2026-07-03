package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    WebDriver driver;
    WaitUtils waitUtils;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[text()='Travel']")
    public WebElement travelInsuranceElement;

    @FindBy(xpath = "//span[normalize-space()='Car']/ancestor::li[1]")
    public WebElement carInsuranceElement;

    @FindBy(xpath="//span[text()='Health']")
    public WebElement healthInsuranceElement;

    @FindBy(xpath="//span[text()='Select Scope']")
    public WebElement selectTravelScope;

    @FindBy(xpath="//a[@class='select-countries-link scope-popup']")
    public WebElement selectCountriesNeeded;

    public void clickTravelInsurance() {
        waitUtils.waitForVisibility(travelInsuranceElement).click();
    }

    public void clickCarInsurance() {
        waitUtils.waitForVisibility(carInsuranceElement).click();
    }

    public void clickHealthInsurance() {
        waitUtils.waitForVisibility(healthInsuranceElement).click();
    }

    public void clickTravelScope(){
        waitUtils.waitForVisibility(selectTravelScope).click();
    }

    public void clickOtherCountries(){
        waitUtils.waitForVisibility(selectCountriesNeeded).click();
    }

    public boolean isSelectOtherCountriesOptionAvailable(){
        try{
            return waitUtils.waitForVisibility(selectCountriesNeeded).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isTravelScopeOptionAvailable(){
        try{
            return waitUtils.waitForVisibility(selectTravelScope).isDisplayed();
        } catch (Exception e){
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

    public boolean isCarInsurancePresent() {
        try {
            return waitUtils
                    .waitForVisibility(carInsuranceElement)
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}