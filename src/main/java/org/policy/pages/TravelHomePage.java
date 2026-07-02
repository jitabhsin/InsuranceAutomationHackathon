package org.policy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.JavaScriptUtils;
import org.policy.utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class TravelHomePage {
    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils javaScriptUtils;

    public TravelHomePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="country")
    public WebElement selectCountryElement;


    @FindBy(xpath = "//div[contains(@class,'selectedCountryWrap')]//p")
    public WebElement selectedCountryText;


    @FindBy(xpath="//article[@class='newPq_duration_wrap']/div")
    public WebElement selectDateElement;


    @FindBy(xpath = "//button[contains(@class, 'travel_main_cta') and normalize-space()='Continue']")
    public WebElement dateSubmitButton;

    @FindBy(xpath = "//button[contains(@class, 'travel_main_cta') and normalize-space()='Done']")
    public WebElement ageSubmitButton;

    @FindBy(xpath = "//button[contains(@class, 'travel_main_cta') and normalize-space()='Explore Plans ›']")
    public WebElement submitButton;

    @FindBy(id="0")
    public WebElement traveller1Age;

    @FindBy(id="1")
    public WebElement traveller2Age;

    @FindBy(id="21")
    public WebElement selectAge1;

    @FindBy(id="22")
    public WebElement selectAge2;

    @FindBy(id="ped_no")
    public WebElement diabetesCheckBox;


    public boolean isTravelPageDisplayed(){
        try{
            return waitUtils.waitForVisibility(selectCountryElement).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public String getSelectedCountry(){
        return waitUtils.waitForVisibility(selectedCountryText).getText().trim();
    }



    public void selectCountry(String countryName){
        waitUtils.waitForVisibility(selectCountryElement).click();
        waitUtils.waitForVisibility(selectCountryElement).clear();
        waitUtils.waitForVisibility(selectCountryElement).sendKeys(countryName);

        List<WebElement> options = waitUtils.waitForVisibilityOfAllElements();

        for(WebElement option : options){
            if(option.getText().equalsIgnoreCase(countryName)){
                option.click();
                break;
            }
        }
    }

    public void selectStartDate(String startDate){
        WebElement sDate = waitUtils.waitForVisibilityOfElementLocated(By.xpath("//button[@aria-label='" + startDate + "']"));
        sDate.click();
    }

    public void selectEndDate(String endDate){
        driver.findElement(By.xpath("//button[@aria-label='" + endDate + "']")).click();
    }

    public void submitDate(){
        submitButton.click();
    }


    public void selectTravellerCount(int count, int... ages) {

        if (ages.length < count) {
            throw new IllegalArgumentException(
                    "Number of ages must be equal to or greater than traveller count");
        }

        if (count == 1) {
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_1']")).click();
        } else if (count == 2) {
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_2']")).click();
        } else if (count == 3) {
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_3']")).click();
        } else if (count == 4){
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_4']")).click();
        } else if (count == 5){
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_5']")).click();
        } else if (count >= 6){
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='traveller_6_plus']")).click();
        }

        for (int i = 0; i < count; i++) {
            waitUtils.waitForVisibilityOfElementLocated(By.id(String.valueOf(i))).click();
            String ageId = ages[i] + " years_undefined";
            waitUtils.waitForVisibilityOfElementLocated(By.xpath("//label[@for='" + ageId + "']")).click();
        }
    }

    public void selectHealthOfTravellers(String diabetesCheck){
        if(diabetesCheck.equalsIgnoreCase("no")){
            waitUtils.waitForVisibilityOfElementLocated(By.id("ped_no")).click();
        }
    }

    public void getTravelQuota(){
        waitUtils.waitForVisibility(submitButton).click();
    }


}
