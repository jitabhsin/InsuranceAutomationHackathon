package org.insurance.utils;

import org.openqa.selenium.By;
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

    public WebElement waitForVisibilityOfElementLocated(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement waitForVisibility(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitForVisibilityOfAllElements(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='dropdown-item']")));
        return list;
    }


    public boolean waitForSelected(WebElement element) {
        return wait.until(driver -> element.isSelected());
    }

    public boolean waitForAttributeContains(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public boolean waitForElementCount(By locator, int expectedCount) {
        return wait.until(driver ->
                driver.findElements(locator).size() >= expectedCount);
    }

    public boolean waitForAttributeChange(
            WebElement element,
            String attribute,
            String previousValue) {

        return wait.until(driver ->
                !element.getAttribute(attribute)
                        .equals(previousValue));
    }

    public boolean waitForTextChange(
            WebElement element,
            String previousText) {

        return wait.until(driver ->
                !element.getText()
                        .equals(previousText));
    }

}
