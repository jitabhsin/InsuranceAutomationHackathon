package org.policy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HealthHomePage {

    WebDriver driver;
    WebDriverWait wait;

    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "male")
    WebElement maleRadio;

    @FindBy(id = "female")
    WebElement femaleRadio;

    // Members dynamically changes so we used By
    By memberCheckboxLocator = By.xpath("//input[@name='member']");

    // Two lists to store member elements per gender
    List<WebElement> maleMemberList = new ArrayList<>();
    List<WebElement> femaleMemberList = new ArrayList<>();

    // ---------- Gender Toggle ----------

    public void selectMaleGender() {
        maleRadio.click();
        waitForMembersToLoad();
        maleMemberList = driver.findElements(memberCheckboxLocator);
    }

    public void selectFemaleGender() {
        femaleRadio.click();
        waitForMembersToLoad();
        femaleMemberList = driver.findElements(memberCheckboxLocator);
    }

    // Small wait for members to load
    private void waitForMembersToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(memberCheckboxLocator));
    }

    // ---------- Click a specific member ----------

    // Works for whichever gender section is currently active
    public void clickMemberByLabel(String label) {
        WebElement labelEl = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[p[normalize-space(text())='" + label + "']]")
        ));
        labelEl.click();
    }

    // Clicking the particular member
    public void clickMemberFromList(List<WebElement> memberList, String label) {
        for (WebElement checkbox : memberList) {
            String id = checkbox.getAttribute("id");
            WebElement labelEl = driver.findElement(By.xpath("//label[@for='" + id + "']"));
            if (labelEl.getText().trim().equalsIgnoreCase(label)) {
                checkbox.click();
                return;
            }
        }
        throw new RuntimeException("Member not found in list: " + label);
    }

    // ---------- Getters ----------

    public List<WebElement> getMaleMemberList() {
        return maleMemberList;
    }

    public List<WebElement> getFemaleMemberList() {
        return femaleMemberList;
    }
}