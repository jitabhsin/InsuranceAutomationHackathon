package org.insurance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class HealthHomePage {

    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    @FindBy( className = "health_icon_bg_size")
    WebElement healthTab;

    @FindBy(xpath = "//label[normalize-space()='Select products']")
    WebElement selectProductText;

    @FindBy(xpath = "//button[@class='product-dropbtn policyTypecls']")
    WebElement selectProductDropdownBtn;

    @FindBy(xpath = "//input[@name='radio-group']/following-sibling::label")
    List<WebElement> productOptions;

    @FindBy(xpath = "//span[@class='filled-field-value policyTypecls']")
    WebElement productValue;

    @FindBy(className = "btn-addmember")
    WebElement memberBtn;

    @FindBy(xpath = "//button[@class='btn-adults btn-adults2 ins-mem-pop']")
    WebElement addAdultBtn;

    @FindBy(xpath = "//span[@class='contact-pop']")
    WebElement contactDetails;

    @FindBy(id = "valid-popmobilnumber")
    WebElement mobileNumber;

    @FindBy(id = "valid-popemil")
    WebElement emailId;

    @FindBy(id = "valid-poppincode")
    WebElement pincode;

    @FindBy(id = "your-name")
    WebElement name;

    @FindBy(className = "btn-done")
    WebElement doneButton;


    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickHealthTab() {
        waitUtils.waitForClickable(healthTab).click();
    }

    public String isSelectProductDisplayed() {
        return waitUtils.waitForVisibility(selectProductText).getText();
    }

    public void clickProductDropdwn(){
        selectProductDropdownBtn.click();
    }

    public String selectProduct(String productName){
        for(WebElement element : productOptions){
            System.out.println(element.getText());
            if(element.getText().trim().equalsIgnoreCase(productName)){
                waitUtils.waitForClickable(element).click();
                break;
            }
        }
        return productValue.getText();
    }

    public void clickMemberBtn(){
        memberBtn.click();
    }

    public boolean isContactDetailsDisplayed() {
        return waitUtils.waitForVisibility(contactDetails).isDisplayed();
    }

    public void clickContactDetails(){
        contactDetails.click();
    }


    public void enterMobileNo(String mobileNo){
        mobileNumber.sendKeys(mobileNo);
    }

    public void enterEmailId(String email){
        emailId.sendKeys(email);
    }

    public void enterPincode(String pin){
        pincode.sendKeys(pin);
    }

    public void enterName(String Name){
        name.sendKeys(Name);
    }
    public void clickDoneButton(){
        doneButton.click();
    }

}
