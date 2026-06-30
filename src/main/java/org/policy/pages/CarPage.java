package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.policy.utils.WaitUtils;

public class CarPage {

    WebDriver driver;
    WaitUtils waitUtils;

    public CarPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
