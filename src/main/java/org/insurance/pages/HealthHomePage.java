package org.insurance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class HealthHomePage {

    WebDriver driver;
    WaitUtils waitUtils;
    JavaScriptUtils jsUtils;

    public HealthHomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.jsUtils = new JavaScriptUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy( className = "health_icon_bg_size")
    WebElement healthTab;

    public void clickHealthTab() {
        waitUtils.waitForClickable(healthTab).click();
    }

    public boolean isHealthTabDisplayed() {
        return waitUtils.waitForVisibility(healthTab).isDisplayed();
    }


}
