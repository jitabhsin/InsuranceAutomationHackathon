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

    @FindBy(id="country")
    public WebElement selectCountry;

    @FindBy(xpath="//article[@class='newPq_duration_wrap']/div")
    public WebElement selectStartDate;

    @FindBy(xpath="//article[@class='newPq_travellers_wrap']")
    public WebElement travellerCount;

    public void selectCountry(String countryName){

    }
}
