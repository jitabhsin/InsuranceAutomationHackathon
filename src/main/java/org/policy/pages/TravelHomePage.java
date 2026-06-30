package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.WaitUtils;

import java.util.List;

public class TravelHomePage {
    WebDriver driver;
    WaitUtils waitUtils;

    public TravelHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="countryMobile")
    public WebElement selectCountryElement;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[2]/div[1]")
    public WebElement selectStartDateElement;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[2]/div[2]")
    public WebElement selectEndDateElement;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[3]")
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
}
