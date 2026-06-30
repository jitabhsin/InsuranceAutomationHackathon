package org.policy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HealthHomePage {

    WebDriver driver;
    WaitUtils waitUtils;

    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, Duration.ofSeconds(10)); // second arg unused, but required to compile
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "male")
    WebElement maleRadio;

    @FindBy(id = "female")
    WebElement femaleRadio;

    By memberCheckboxLocator = By.xpath("//input[@name='member']");

    List<WebElement> maleMemberList = new ArrayList<>();
    List<WebElement> femaleMemberList = new ArrayList<>();

    public void selectMaleGender() {
        waitUtils.waitForClickable(maleRadio).click();
        maleMemberList = driver.findElements(memberCheckboxLocator);
        for (WebElement el : maleMemberList) {
            waitUtils.waitForVisibility(el); // waits on each element individually
        }
    }

    public void selectFemaleGender() {
        waitUtils.waitForClickable(femaleRadio).click();
        femaleMemberList = driver.findElements(memberCheckboxLocator);
        for (WebElement el : femaleMemberList) {
            waitUtils.waitForVisibility(el);
        }
    }

    public void clickMemberByLabel(String label) {
        WebElement labelEl = driver.findElement(By.xpath("//label[normalize-space()='" + label + "']"));
        waitUtils.waitForClickable(labelEl).click();
    }

    public List<WebElement> getMaleMemberList() {
        return maleMemberList;
    }

    public List<WebElement> getFemaleMemberList() {
        return femaleMemberList;
    }
}