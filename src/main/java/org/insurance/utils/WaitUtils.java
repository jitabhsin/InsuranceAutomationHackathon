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

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForVisibilityOfElementLocated(By element) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(element)
        );
    }

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                )
        );
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(element)
                )
        );
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements) {
        return wait.until(
                ExpectedConditions.visibilityOfAllElements(elements)
        );
    }

    public boolean waitForSelected(WebElement element) {
        return wait.until(driver -> {
            try {
                return element.isSelected();
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public boolean waitForAttributeContains(
            WebElement element,
            String attribute,
            String value) {

        return wait.until(driver -> {
            try {
                String attr = element.getAttribute(attribute);
                return attr != null && attr.contains(value);
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public boolean waitForUrlContains(String fragment) {
        return wait.until(
                ExpectedConditions.urlContains(fragment)
        );
    }

    public boolean waitForElementCount(
            List<WebElement> list,
            int expectedCount) {

        return wait.until(driver ->
                list.size() >= expectedCount);
    }

    public boolean waitForAttributeChange(
            WebElement element,
            String attribute,
            String previousValue) {

        return wait.until(driver -> {
            try {
                String currentValue = element.getAttribute(attribute);
                return currentValue != null &&
                        !currentValue.equals(previousValue);
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public boolean waitForTextChange(
            WebElement element,
            String previousText) {

        return wait.until(driver -> {
            try {
                return !element.getText().equals(previousText);
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }
}