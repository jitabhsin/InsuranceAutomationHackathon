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

    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[@for='male']")
    WebElement maleLabel;

    @FindBy(xpath = "//label[@for='female']")
    WebElement femaleLabel;

    @FindBy(xpath = "//input[@name='member']")
    List<WebElement> memberCheckboxes;

    List<WebElement> maleMemberList = new ArrayList<>();
    List<WebElement> femaleMemberList = new ArrayList<>();

    public boolean verifyHealthMenuVisible(HomePage homePage) {
        WebElement healthMenu = waitUtils.waitForVisibility(homePage.healthInsuranceElement);
        return healthMenu.isDisplayed();
    }

    public boolean navigateAndWaitForSubmenu(HomePage homePage) {
        homePage.clickHealthInsurance();
        waitUtils.waitForVisibility(maleLabel);
        waitUtils.waitForVisibility(femaleLabel);
        List<WebElement> members =
                waitUtils.waitForVisibilityOfAllElements(By.xpath("//input[@name='member']"));

        return maleLabel.isDisplayed()
                && femaleLabel.isDisplayed()
                && members != null
                && !members.isEmpty()
                && driver.getCurrentUrl().contains("health");
    }

    public void navigateToHealthPage(HomePage homePage) {
        homePage.clickHealthInsurance();
    }

    public void selectMaleGender() {
        jsUtils.jsClick(waitUtils.waitForVisibility(maleLabel));
        maleMemberList = new ArrayList<>(memberCheckboxes);
    }

    public void selectFemaleGender() {
        jsUtils.jsClick(waitUtils.waitForVisibility(femaleLabel));
        femaleMemberList = new ArrayList<>(memberCheckboxes);
    }

    public void clickMemberByLabel(String labelText) {
        WebElement labelEl = driver.findElement(memberLabelLocator(labelText));
        waitUtils.waitForClickable(labelEl).click();
    }

    private By memberLabelLocator(String labelText) {
        return By.xpath("//label[normalize-space()='" + labelText + "']");
    }

    public List<WebElement> getMaleMemberList() {
        return maleMemberList;
    }

    public List<WebElement> getFemaleMemberList() {
        return femaleMemberList;
    }
}
