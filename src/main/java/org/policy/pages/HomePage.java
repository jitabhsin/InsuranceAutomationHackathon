package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Travel')]]//a")
    public WebElement travelInsuranceElement;

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Car')]]//a")
    public WebElement carInsuranceElement;

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Health')]]//a")
    public WebElement healthInsuranceElement;

    public void clickTravelInsurance(){
        travelInsuranceElement.click();
    }

    public void clickCarInsurance(){
        carInsuranceElement.click();
    }

    public void clickHealthInsurance(){
        healthInsuranceElement.click();
    }

}
