package org.insurance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;

import java.util.List;

public class HealthHomePage {

    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    @FindBy( className = "health_icon_bg_size")
    WebElement healthTab;

    @FindBy(xpath = "//label[normalize-space()='Select products']")
    WebElement verifyProductText;

    @FindBy(xpath = "//label[normalize-space()='Insure members']")
    WebElement verifyInsureMembersText;

    @FindBy(xpath = "//label[normalize-space()='Contact details']")
    WebElement verifyContactDetailsText;

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

    @FindBy(xpath = "//button[contains(@type, 'button') and normalize-space()='-']")
    List<WebElement> minusBtn;

    @FindBy(xpath = "//div[@class='dob-close']/a")
    WebElement closeDobBtn;

    @FindBy(className = "btn-insuremembers-details")
    WebElement doneBtn;

    @FindBy(xpath = "//span[@class='filled-field-value ins-mem-pop']")
    public WebElement verifyMembersResult;

    @FindBy(className = "btn-adddetails")
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

    @FindBy(id = "city-get-quote")
    WebElement getQuoteBtn;

    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickHealthTab() {
        waitUtils.waitForClickable(healthTab).click();
    }

    public String isSelectProductTextDisplayed() {
        return waitUtils.waitForVisibility(verifyProductText).getText();
    }

    public String isInsureMembersTextDisplayed() {
        return waitUtils.waitForVisibility(verifyInsureMembersText).getText();
    }
    public String isContactDetailsTextDisplayed() {
        return waitUtils.waitForVisibility(verifyContactDetailsText).getText();
    }


    public int clickProductDropdwn(){
        waitUtils.waitForVisibility(selectProductDropdownBtn);
        selectProductDropdownBtn.click();
        return productOptions.size();
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

    public int adultCount = 1;
    public int kidsCount = 1;
    public void addMembers(String member, String dob){
        String DOB[] = dob.split("-");
        if(member.equalsIgnoreCase("Adult")){
            if(!minusBtn.get(0).getAttribute("class").contains("btn-disable")){
                closeDobBtn.click();
            }
            addBtn.get(0).click();driver.findElement(By.id("valid-adult" + adultCount + "date")).sendKeys(DOB[0]);
            driver.findElement(By.id("valid-adult" + adultCount + "month")).sendKeys(DOB[1]);
            driver.findElement(By.id("valid-adult" + adultCount + "year")).sendKeys(DOB[2]);
            adultCount++;
        }
        else if(member.equalsIgnoreCase("Kids")){
            if(!minusBtn.get(1).getAttribute("class").contains("btn-disable")){
                closeDobBtn.click();
            }
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
        if(!mobileNumber.getAttribute("value").isEmpty()){
            mobileNumber.clear();
        }
        mobileNumber.sendKeys(mobileNo);
    }

    public void enterEmailId(String email){
        if(!emailId.getAttribute("value").isEmpty()){
            emailId.clear();
        }
        emailId.sendKeys(email);
    }

    public void enterPincode(String pin){
        if(!pincode.getAttribute("value").isEmpty()){
            pincode.clear();
        }
        pincode.sendKeys(pin);
    }

    public void enterName(String Name){
        if(!name.getAttribute("value").isEmpty()){
            name.clear();
        }
        name.sendKeys(Name);
    }

    public boolean isDoneButtonDisplayed() {
        return waitUtils.waitForVisibility(doneButton).isDisplayed();
    }

    public void clickDoneButton(){
        jsUtils.jsClick(waitUtils.waitForClickable(doneButton));
    }

    public void clearContactDetails(){
        driver.navigate().refresh();
    }

    public void clickGetQuote(){
        jsUtils.jsClick(waitUtils.waitForClickable(getQuoteBtn));
    }
}
