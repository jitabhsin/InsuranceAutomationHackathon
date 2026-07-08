package org.insurance.pages;

import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.util.*;

public class TravelHomePage {
    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    public TravelHomePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        jsUtils = new JavaScriptUtils(driver);
    }

    String countryName = "";

    @FindBy(xpath="//label[@for='ilcountry']")
    public WebElement selectCountryElement;

    @FindBy(xpath = "//input[@placeholder='Add countries']")
    public WebElement selectCountryText;

    @FindBy(xpath = "//div[@class='no-result-message']")
    public WebElement noResultText;

    @FindBy(xpath="//input[@class='tel date-bg-input val-mob val-req numeric cal-popup ng-untouched ng-pristine ng-valid']")
    public WebElement selectStartAndEndDateElement;

    @FindBy(xpath = "//label[text()='Return date ']")
    public WebElement selectEndDateElement;

    @FindBy(xpath = "//a[text()='Continue']")
    public WebElement dateSubmitButton;

    @FindBy(id = "mul-no")
    public WebElement contactNumber;

    @FindBy(id = "mul-em")
    public WebElement email;

    @FindBy(xpath = "//span[text()='0-50 Years']/following-sibling::div//a[contains(@class,'btn-plus')]")
    public WebElement zeroTofiftyAgeGroup;

    @FindBy(xpath = "//span[text()='51-60 Years']/following-sibling::div//a[contains(@class,'btn-plus')]")
    public WebElement fiftyAgeGroup;

    @FindBy(xpath = "//span[text()='61-70 Years']/following-sibling::div//a[contains(@class,'btn-plus')]")
    public WebElement sixtyAgeGroup;

    @FindBy(xpath = "//span[text()='71-85 Years']/following-sibling::div//a[contains(@class,'btn-plus')]")
    public WebElement seventyAboveAgeGroup;

    @FindBy(xpath="//label[text()='No']")
    public WebElement noHealthCheckBox;

    @FindBy(xpath="//label[text()='Yes']")
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

    @FindBy(xpath = "//input[@placeholder='DD']")
    private List<WebElement> dayFields;

    @FindBy(xpath = "//input[@placeholder='MM']")
    private List<WebElement> monthFields;

    @FindBy(xpath = "//input[@placeholder='YYYY']")
    private List<WebElement> yearFields;

    @FindBy(xpath = "//div[contains(@class,'il-traveller-details')]//label")
    public List<WebElement> travellerHealthIssueCheckboxes;

    public void safeClick(WebElement element) {
        try {
            element = waitUtils.waitForClickable(element);
            jsUtils.scrollToElement(element);
            element.click();
        } catch (Exception e) {
            System.out.println("Normal click failed. Trying JS Click");
            jsUtils.scrollAndClick(element);
        }
    }

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
        String content = jsUtils.isTravelTypeSelected(selectCountryElement);
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

    public boolean noResultFoundForCountry(){
        try{
            return noResultText.isDisplayed();
        } catch (Exception e){
            return false;
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

    public void selectTravellerCount(int count, int[] ages) {
        if (ages.length < count) {
            throw new IllegalArgumentException("Number of ages must be equal to or greater than traveller count");
        }

        for (int i = 0; i < count; i++) {
            int age = ages[i];

            if (age > 0 && age <= 50) {
                safeClick(zeroTofiftyAgeGroup);
            } else if (age > 50 && age <= 60) {
                safeClick(fiftyAgeGroup);
            } else if (age > 60 && age <= 70) {
                safeClick(sixtyAgeGroup);
            } else if (age > 70 && age <= 85) {
                safeClick(seventyAboveAgeGroup);
            } else {
                throw new IllegalArgumentException("Invalid traveller age : " + age);
            }
        }
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

    public boolean validateHealthIssueTravellers(String travellerAges, String healthIssueTravellers) {

        String[] travellerAgeArray = travellerAges.split(",");
        String[] healthIssueArray = healthIssueTravellers.split(",");

        List<String> travellerAgeList = new ArrayList<>();

        for (String age : travellerAgeArray) {
            travellerAgeList.add(age.trim());
        }

        for (String issueAge : healthIssueArray) {
            issueAge = issueAge.trim();

            if (!travellerAgeList.contains(issueAge)) {
                System.out.println("Invalid HealthIssueTraveller Age: " + issueAge + "\nTraveller Ages: " + travellerAges + "\nHealth Issue Travellers: " + healthIssueTravellers);
                return false;
            }
        }
        return true;
    }

    public boolean validateSeniorTravellerDOBs(String travellerAges, String seniorTravellerDOBs) {

        if (seniorTravellerDOBs == null || seniorTravellerDOBs.trim().isEmpty()) {
            return true;
        }

        List<String> travellerAgeList = new ArrayList<>();

        for (String age : travellerAges.split(",")) {
            travellerAgeList.add(age.trim());
        }

        String[] dobEntries = seniorTravellerDOBs.split(",");

        for (String entry : dobEntries) {
            String seniorAge = entry.split("=")[0].trim();

            if (!travellerAgeList.contains(seniorAge)) {
                System.out.println("Invalid Senior DOB Mapping Age: " + seniorAge);
                return false;
            }
        }
        return true;
    }

    public void enterSeniorTravellerDOBs(int[] ages, String seniorTravellerDOBs) {

        if (seniorTravellerDOBs == null || seniorTravellerDOBs.trim().isEmpty()) {
            return;
        }

        List<String> seniorAgeOrder = new ArrayList<>();

        for (int age : ages) {
            if (age > 70) {
                seniorAgeOrder.add(String.valueOf(age));
            }
        }

        Map<String, String> dobMap = new HashMap<>();
        String[] dobEntries = seniorTravellerDOBs.split(",");

        for (String entry : dobEntries) {
            String[] parts = entry.trim().split("=");
            dobMap.put(parts[0].trim(), parts[1].trim());
        }

        for (int i = 0; i < seniorAgeOrder.size(); i++) {
            String age = seniorAgeOrder.get(i);
            String dob = dobMap.get(age);

            if (dob == null) {
                continue;
            }

            String[] dobParts = dob.split("-");

            String day = dobParts[0];
            String month = dobParts[1];
            String year = dobParts[2];

            dayFields.get(i).clear();
            dayFields.get(i).sendKeys(day);

            monthFields.get(i).clear();
            monthFields.get(i).sendKeys(month);

            yearFields.get(i).clear();
            yearFields.get(i).sendKeys(year);

            System.out.println("Age " + age + " DOB Entered : " + dob);
        }
    }
    public void selectHealthIssueTravellersByAge(String travellerAgesValue, String healthIssueTravellers) {

        if (healthIssueTravellers == null || healthIssueTravellers.trim().isEmpty()) {
            return;
        }

        String[] travellerAges = travellerAgesValue.split(",");
        String[] issueAges = healthIssueTravellers.split(",");

        List<Integer> sortedTravellerAges = new ArrayList<>();

        for (String age : travellerAges) {
            sortedTravellerAges.add(Integer.parseInt(age.trim()));
        }

        Collections.sort(sortedTravellerAges);

        for (String age : issueAges) {
            int issueAge = Integer.parseInt(age.trim());
            int checkboxIndex = sortedTravellerAges.indexOf(issueAge);

            if (checkboxIndex != -1 && checkboxIndex < travellerHealthIssueCheckboxes.size()) {
                safeClick(travellerHealthIssueCheckboxes.get(checkboxIndex));
                System.out.println("Selected traveller age: " + issueAge);
            }
        }
    }

}
