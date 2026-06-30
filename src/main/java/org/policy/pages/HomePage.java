package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.WaitUtils;

public class HomePage {
    WebDriver driver;
    WaitUtils waitUtils;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Travel')]]//a")
    public WebElement travelInsuranceElement;

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Car')]]//a")
    public WebElement carInsuranceElement;

    @FindBy(xpath="//div[contains(@class,'prd-block')][.//p[contains(.,'Health')]]//a")
    public WebElement healthInsuranceElement;

    public void clickTravelInsurance(){
        waitUtils.waitForVisibility(travelInsuranceElement).click();
    }

    public void clickCarInsurance(){
        waitUtils.waitForVisibility(carInsuranceElement).click();
    }

    public void clickHealthInsurance(){
        waitUtils.waitForVisibility(healthInsuranceElement).click();
    }

}
