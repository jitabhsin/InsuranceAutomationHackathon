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

//    public WebElement waitForVisibilityOfElementLocated(By element){
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//    }

    public WebElement waitForVisibility(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return list;
    }
    public WebElement waitForVisibilityIgnoringStale(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForSelected(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public boolean waitForAttributeContains(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }
    public boolean waitForUrlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }
    public boolean waitForElementCount(List<WebElement> list, int expectedCount) {
        return wait.until(driver ->list.size() >= expectedCount);
    }
}