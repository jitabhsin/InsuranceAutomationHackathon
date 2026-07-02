package org.insurance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;

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

    String countryName = "";

    @FindBy(xpath="//label[@for='ilcountry']")
    public WebElement selectCountryElement;

    @FindBy(xpath = "//input[@placeholder='Add countries']")
    public WebElement selectCountryText;

    @FindBy(xpath = "//span[text()='France']")
    public WebElement selectedCountryText;

    @FindBy(id="il-start-date")
    public WebElement selectStartDateElement;

    @FindBy(id="il-end-date")
    public WebElement selectEndDateElement;

    @FindBy(xpath = "//a[text()='Continue']")
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

    @FindBy(xpath="//img[@class='cal-popup']")
    public WebElement nextMonthButton;

    @FindBy(xpath = "//div[@class='travel-head-month-year cal-popup']")
    public WebElement leftSideMonthAndYear;

    @FindBy(xpath = "//div[@class='travel-head-month-year']")
    public WebElement rightSidetMonthAndYear;

    @FindBy(xpath = "//div[@class='travel-calender-main cal-popup']")
    public WebElement calenderElement;


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

    public boolean isCountrySelectedCorrectly(){
        try{
            return selectedCountryText.getText().equals(countryName);
        } catch (Exception e){
            return false;
        }
    }
    public boolean isSelectTravelTypeVisible(){
        try{
            return waitUtils.waitForVisibility(selectCountryText).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isSelectTravelTypeSelected() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String content = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');",
                selectCountryElement
        );

        return content != null && !content.equals("none");
    }
    public void selectCountry(String countryName){
        waitUtils.waitForVisibility(selectCountryElement).click();
        waitUtils.waitForVisibility(selectCountryText).sendKeys(countryName);
        this.countryName = countryName;

        List<WebElement> options = waitUtils.waitForVisibilityOfAllElements();


        for(WebElement option : options){
            if(option.getText().equalsIgnoreCase(countryName)){
                option.click();
                break;
            }
        }
    }

    public void selectStartDate(String startDate){
        String[] dateSeperator = startDate.split(",");
        String date = dateSeperator[0].trim();
        String monthAndYear = dateSeperator[1].trim();

        Actions actions = new Actions(driver);
        actions.moveToElement(calenderElement).perform();

        clickNextMonthAndYear(monthAndYear);
        clickDate(monthAndYear, date);
    }


    public void selectEndDate(String endDate){
        String[] dateSeperator = endDate.split(",");
        String date = dateSeperator[0].trim();
        String monthAndYear = dateSeperator[1].trim();

        clickNextMonthAndYear(monthAndYear);
        clickDate(monthAndYear, date);
    }

    public void submitDate(){
        waitUtils.waitForVisibility(dateSubmitButton).click();
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

    public void clickNextMonthAndYear(String monthAndYear){
        String leftSideMonthAndYearText = leftSideMonthAndYear.getText();
        while(!leftSideMonthAndYearText.equalsIgnoreCase(monthAndYear)){
            waitUtils.waitForVisibility(nextMonthButton).click();
            waitUtils.waitForVisibility(leftSideMonthAndYear);
            leftSideMonthAndYearText = leftSideMonthAndYear.getText();
        }
    }

    public void clickDate(String monthAndYear, String date){
        String xpathString = "//div[text()='" + monthAndYear + "']/ancestor::table//div[text()='" + date + "']";
        WebElement dateElement = driver.findElement(By.xpath(xpathString));
        waitUtils.waitForVisibility(dateElement).click();
    }


}
