package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // Travel Insurance
    @FindBy(xpath = "//a[contains(@href,'travel-insurance')]")
    private WebElement travelInsuranceElement;

    // Car Insurance
    @FindBy(xpath = "//a[contains(@href,'car-insurance')]")
    private WebElement carInsuranceElement;

    // Health Insurance
    @FindBy(xpath = "//a[contains(@href,'health-insurance')]")
    private WebElement healthInsuranceElement;

    public void clickTravelInsurance() {
        waitUtils.waitForVisibility(travelInsuranceElement).click();
    }

    public void clickCarInsurance() {
        waitUtils.waitForVisibility(carInsuranceElement).click();
    }

    public void clickHealthInsurance() {
        waitUtils.waitForVisibility(healthInsuranceElement).click();
    }
}