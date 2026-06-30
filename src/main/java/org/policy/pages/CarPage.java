package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.WaitUtils;

public class CarPage {

    WebDriver driver;
    WaitUtils waitUtils;

    public CarPage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver );
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[normalize-space()='View Prices']")
    public WebElement viewPrices;

    @FindBy(xpath="//span[normalize-space()='Click here']")
    public WebElement newCar;

    public void clickViewPrices(){
        waitUtils.waitForClickable(viewPrices).click();
    }

    public void clickNewCar(){
        waitUtils.waitForClickable(newCar).click();
    }

}