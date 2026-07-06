package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TravelHomePage {
    WebDriver driver;
    WaitUtils waitUtils;

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

    @FindBy(xpath="//input[@class='tel date-bg-input val-mob val-req numeric cal-popup ng-untouched ng-pristine ng-valid']")
    public WebElement selectStartAndEndDateElement;

    @FindBy(xpath = "//label[text()='Return date ']")
    public WebElement selectEndDateElement;

    @FindBy(xpath = "//a[text()='Continue']")
    public WebElement dateSubmitButton;

    @FindBy(xpath = "//button[contains(@class, 'travel_main_cta') and normalize-space()='Done']")
    public WebElement ageSubmitButton;

    @FindBy(xpath = "//button[contains(@class, 'travel_main_cta') and normalize-space()='Explore Plans ›']")
    public WebElement submitButton;

    @FindBy(id = "mul-no")
    public WebElement contactNumber;

    @FindBy(id = "mul-em")
    public WebElement email;

    @FindBy(xpath = "//span[text()='0-50 Years']/following-sibling::div//a[contains(@class,'btn-plus')]a")
    public WebElement zeroTofiftyAgeGroup;

    @FindBy(xpath = "//span[text()='51-60 Years']/following-sibling::div//a[contains(@class,'btn-plus')]a")
    public WebElement fiftyAgeGroup;

    @FindBy(xpath = "//span[text()='61-70 Years']/following-sibling::div//a[contains(@class,'btn-plus')]a")
    public WebElement sixtyAgeGroup;

    @FindBy(xpath = "//span[text()='71-85 Years']/following-sibling::div//a[contains(@class,'btn-plus')]a")
    public WebElement seventyAboveAgeGroup;

    @FindBy(id="//label[text()='No']")
    public WebElement noHealthCheckBox;

    @FindBy(id="//label[text()='Yes']")
    public WebElement yesHealthCheckBox;

    @FindBy(xpath="//img[@class='cal-popup']")
    public WebElement nextMonthButton;

    @FindBy(xpath = "//div[@class='travel-head-month-year cal-popup']")
    public WebElement leftSideMonthAndYear;

    @FindBy(xpath = "//div[@class='travel-calender-main cal-popup']")
    public WebElement calenderElement;

    @FindBy(xpath = "//span[@class='ui-field-info float-right']")
    public WebElement tripDurationElement;

    @FindBy(id="ml-check")
    public WebElement termsCheckbox;

    @FindBy(xpath = "//h4[text()='Add travellers']")
    public WebElement verifyAddTraveller;

    @FindBy(xpath = "//span[@class='il-ui-error text-center'] | //span[@class='error_message']")
    public List<WebElement> allCountryErrorElements;

    @FindBy(xpath = "//span[@class='error_message']")
    public List<WebElement> allTravellerErrorElements;

    @FindBy(xpath = "//a[@class='il-con-close']")
    public List<WebElement> selectedCountryElements;

    @FindBy(xpath = "//input[@class='ng-untouched ng-pristine ng-invalid']")
    public List<WebElement> selectedTravellerElements;

    @FindBy(xpath = "//a[@class='primary-btn']")
    public WebElement travellerSubmitButton;

    @FindBy(xpath = "//label[text()='All the travellers have a non-immigrant visa']")
    public WebElement nonImmigrantVisaElement;

    @FindBy(xpath = "//h2[@class='text-bold']")
    public WebElement nonImmigrantAttention;

    @FindBy(xpath = "//p[starts-with(text(),' Please note that this policy is')]")
    public WebElement nonImmigrantInformation;

    @FindBy(xpath = "//a[text()='Ok']")
    public WebElement okButton;

    public boolean isTravelPageDisplayed(){
        try{
            return waitUtils.waitForVisibility(selectCountryElement).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public String getSelectedCountry() {
        By locator = By.xpath("//span[text()='" + countryName + "']");
        return driver.findElement(locator).getText();
    }

    public boolean isCountrySelectedCorrectly() {
        try {
            By locator = By.xpath("//span[text()='" + countryName + "']");
            return driver.findElement(locator).getText().equals(countryName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoCountrySelected() {
        try {
            By locator = By.xpath("//span[text()='" + countryName + "']");
            return driver.findElement(locator).getText().trim().isEmpty();
        } catch (Exception e) {
            return true;
        }
    }


    public boolean isSelectTravelTypeVisible(){
        try{
            return waitUtils.waitForVisibility(selectCountryElement).isDisplayed();
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

        for (int i = 0; i < count; i++) {
            if (ages[i] > 0 && ages[i] <= 50) {
                waitUtils.waitForVisibility(zeroTofiftyAgeGroup).click();
            } else if (ages[i] > 50 && ages[i] <= 60) {
                waitUtils.waitForVisibility(fiftyAgeGroup).click();
            } else if (ages[i] > 60 && ages[i] <= 70) {
                waitUtils.waitForVisibility(sixtyAgeGroup).click();
            } else if (ages[i] > 70 && ages[i] <= 85) {
                waitUtils.waitForVisibility(seventyAboveAgeGroup).click();
            }
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

    public String retrieveTripDuration(){
        return tripDurationElement.getText();
    }
    public boolean isCalenderOpen(){
        try{
            return waitUtils.waitForVisibility(calenderElement).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isSubmitButtonPresent(){
        try{
            return waitUtils.waitForVisibility(dateSubmitButton).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public boolean isRedirectedToSelectTravellerCount(){
        try{
            return waitUtils.waitForVisibility(verifyAddTraveller).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

    public String getTermsStatus() {
        return termsCheckbox.isSelected() ? "CHECKED" : "UNCHECKED";
    }

    public void providePersonalDetails(String number, String mailId){
        contactNumber.sendKeys(number);
        email.sendKeys(mailId);
    }

    public boolean countryErrorMessagesDisplayed(){
        return !allCountryErrorElements.isEmpty();
    }

    public List<String> listAllErrorMessages(){
        List<String> list = new ArrayList<>();
        for(WebElement element : allCountryErrorElements){
            list.add(element.getText());
        }
        return list;
    }

    public String retrieveCountryError(){
        return allCountryErrorElements.get(0).getText();
    }

    public String retrieveStartDateError(){
        return allCountryErrorElements.get(1).getText();
    }

    public String retrieveEndDateError(){
        return allCountryErrorElements.get(2).getText();
    }

    public boolean travellerErrorMessagesDisplayed(){
        return !allTravellerErrorElements.isEmpty();
    }

    public List<String> listAllTravellerErrorMessages(){
        List<String> list = new ArrayList<>();
        for(WebElement element : allCountryErrorElements){
            list.add(element.getText());
        }
        return list;
    }

    public WebElement verifyNoNumberSelected() {
        return selectedTravellerElements.isEmpty() ? null : selectedTravellerElements.get(0);
    }

    public WebElement verifyNoEmailSelected() {
        return selectedTravellerElements.isEmpty() ? null : selectedTravellerElements.get(1);
    }

    public String retrieveMobileError(){
        return allTravellerErrorElements.get(0).getText();
    }

    public String retrieveEmailError(){
        return allTravellerErrorElements.get(1).getText();
    }

    public String retrieveHealthError(){
        return allTravellerErrorElements.get(2).getText();
    }

    public void selectNonImmigrantCheckBox(){
        waitUtils.waitForVisibility(nonImmigrantVisaElement).click();
    }

    public boolean attentionFrameDisplayed(){
        try{
            return waitUtils.waitForVisibility(nonImmigrantAttention).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyOkButtonPresence(){
        try{
            return waitUtils.waitForVisibility(okButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String retrieveAlertMessage(){
        String alertMessage = nonImmigrantAttention.getText() + "\n" + nonImmigrantInformation.getText();
        return alertMessage;
    }

    public boolean verifyEndDateOptionAvailableAgain(){
        try{
            return waitUtils.waitForVisibility(selectEndDateElement).isDisplayed();
        } catch (Exception e){
            return false;
        }
    }

}
