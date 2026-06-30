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
}
