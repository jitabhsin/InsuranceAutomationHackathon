package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.JavaScriptUtils;
import org.policy.utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class TravelHomePage {
    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils javaScriptUtils;

    public TravelHomePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="country")
    public WebElement selectCountryElement;

    @FindBy(xpath="//span[normalize-space()='Start date']/ancestor::div")
    public WebElement selectDateElement;

    @FindBy(xpath="//article[@class='newPq_travellers_wrap']")
    public WebElement travellerCountElement;

    @FindBy(className="travel_main_cta")
    public WebElement submitButton;

    @FindBy(id="traveller_2")
    public WebElement selectCount;

    @FindBy(id="0")
    public WebElement traveller1Age;

    @FindBy(id="1")
    public WebElement traveller2Age;

    @FindBy(id="21")
    public WebElement selectAge1;

    @FindBy(id="22")
    public WebElement selectAge2;

    @FindBy(id="ped_no")
    public WebElement diabetesCheckBox;

    public boolean isTravelPageDisplayed(){
        try{
            return waitUtils.waitForVisibility(selectCountryElement).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public void selectCountry(String countryName){
        selectCountryElement.click();
        selectCountryElement.clear();
        selectCountryElement.sendKeys(countryName);

        List<WebElement> options = waitUtils.waitForVisibilityOfAllElements();

        for(WebElement option : options){
            if(option.getText().equalsIgnoreCase(countryName)){
                option.click();
                break;
            }
        }

        submitButton.click();
    }

    public void selectStartDate(String startDate){
        javaScriptUtils.setDateValue(selectDateElement, startDate);
    }

    public void selectEndDate(String endDate){
        javaScriptUtils.setDateValue(selectDateElement, endDate);
    }

    public void submitDate(){
        submitButton.click();
    }

    public void selectTravellerCount(){
        waitUtils.waitForVisibility(travellerCountElement).click();
        waitUtils.waitForVisibility(selectCount).click();
        waitUtils.waitForVisibility(traveller1Age).click();
        waitUtils.waitForVisibility(selectAge1).click();
        waitUtils.waitForVisibility(traveller2Age).click();
        waitUtils.waitForVisibility(selectAge2).click();
        waitUtils.waitForVisibility(diabetesCheckBox).click();
        waitUtils.waitForVisibility(submitButton).click();
    }

    public void getTravelQuota(){
        waitUtils.waitForVisibility(submitButton).click();
    }


}
