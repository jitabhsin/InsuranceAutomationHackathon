package org.policy.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver, WebDriver wait){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForVisibility(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitForVisibilityOfAllElements(){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li")));
        return list;
    }
}
