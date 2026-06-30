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

    @FindBy(xpath="//div[@class='prd-block'][7]/a")
    public WebElement travelInsuranceElement;

    @FindBy(xpath="/html/body/main/div[2]/section/div[4]/a/div[1]/div/i")
    public WebElement carInsuranceElement;

    @FindBy(xpath="/html/body/main/div[2]/section/div[6]/a/div[1]/div/i")
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
