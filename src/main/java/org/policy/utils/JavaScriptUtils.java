package org.policy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {
    WebDriver driver;

    public JavaScriptUtils(WebDriver driver){
        this.driver = driver;
    }

    public void setDateValue(WebElement element, String date){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("argument[0].value='" + date + "';", element);
    }
}
