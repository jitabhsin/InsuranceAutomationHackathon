package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CarPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By quoteButton = By.xpath(
            "//button[contains(.,'Get Quote') or " +
                    "contains(.,'Check price') or " +
                    "contains(.,'Check Price')]");

    private final By registrationField = By.xpath(
            "//input[@type='text']");

    private final By errorMessage = By.xpath(
            "//*[contains(@class,'error') or " +
                    "contains(@class,'validation') or " +
                    "contains(@class,'invalid')]");

    public CarPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isCarPageDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(driver.findElement(quoteButton))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRegistrationFieldDisplayed() {
        try {
            return waitUtils
                    .waitForVisibility(driver.findElement(registrationField))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickGetQuote() {
        waitUtils
                .waitForClickable(driver.findElement(quoteButton))
                .click();
    }

    public String getErrorIfNot() {
        clickGetQuote();

        return waitUtils
                .waitForVisibility(driver.findElement(errorMessage))
                .getText()
                .trim();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}