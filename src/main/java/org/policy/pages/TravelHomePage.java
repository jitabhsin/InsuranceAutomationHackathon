package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TravelHomePage {
    private WebDriver driver;

    public TravelHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="countryMobile")
    public WebElement selectCountry;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[2]/div[1]")
    public WebElement selectStartDate;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[2]/div[2]")
    public WebElement selectEndDate;

    @FindBy(xpath="/html/body/section[1]/div/section/section[2]/article[3]")
    public WebElement travellerCount;

    public void selectCountry(String countryName){

    }
}
