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

    @FindBy(xpath = "//a[contains(@href,'travel-insurance')]")
    public WebElement travelInsuranceElement;

    @FindBy(xpath = "//a[contains(@href,'car-insurance')]")
    public WebElement carInsuranceElement;

    @FindBy(xpath = "//a[contains(@href,'health-insurance')]")
    public WebElement healthInsuranceElement;

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