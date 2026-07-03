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
    List<WebElement> addBtn;

    @FindBy(className = "btn-insuremembers-details")
    WebElement doneBtn;

    @FindBy(xpath = "//span[@class='filled-field-value ins-mem-pop']")
    public WebElement verifyMembersResult;

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
        waitUtils.waitForVisibility(selectProductDropdownBtn);
        selectProductDropdownBtn.click();
    }

    public String selectProduct(String productName){
        for(WebElement element : productOptions){
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

    int adultCount = 1;
    int kidsCount = 1;
    public void addMembers(String member, String dob){
        String DOB[] = dob.split("-");
        if(member.equalsIgnoreCase("Adult")){
            addBtn.get(0).click();
            driver.findElement(By.id("valid-adult" + adultCount + "date")).sendKeys(DOB[0]);
            driver.findElement(By.id("valid-adult" + adultCount + "month")).sendKeys(DOB[1]);
            driver.findElement(By.id("valid-adult" + adultCount + "year")).sendKeys(DOB[2]);
            adultCount++;
        }
        else if(member.equalsIgnoreCase("Kids")){
            addBtn.get(1).click();
            driver.findElement(By.id("valid-k" + kidsCount + "date")).sendKeys(DOB[0]);
            driver.findElement(By.id("valid-k" + kidsCount + "month")).sendKeys(DOB[1]);
            driver.findElement(By.id("valid-k" + kidsCount + "year")).sendKeys(DOB[2]);
            kidsCount++;
        }
        else{
            throw new IllegalArgumentException("Invalid Member");
        }
    }

    public boolean clickDoneBtn(){
        doneBtn.click();
        return true;
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

    public boolean isDoneButtonDisplayed() {
        return waitUtils.waitForVisibility(doneButton).isDisplayed();
    }

    public void clickDoneButton(){
        doneButton.click();
    }

}
