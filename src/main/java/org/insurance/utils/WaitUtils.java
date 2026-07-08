package org.insurance.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement waitForVisibility(WebElement element){
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.visibilityOf(element));
        }
    }

    public WebElement waitForClickable(WebElement element){
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements){
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitForSelected(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean waitForAttributeContains(WebElement element, String attribute, String value) {
        try {
            return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean waitForUrlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }

    public boolean waitForElementCount(List<WebElement> list, int expectedCount) {
        return list.size() >= expectedCount;
    }
}