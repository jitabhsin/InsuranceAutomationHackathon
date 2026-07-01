package org.policy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    WebDriver driver;
    JavascriptExecutor js;

    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
}

