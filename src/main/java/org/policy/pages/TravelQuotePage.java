package org.policy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TravelQuotePage {
    private WebDriver driver;

    public TravelQuotePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "singleProfileText")
    public WebElement totalPlan;

    @FindBy(xpath = "//div[contains(@class, 'quotesFilters')]//a[normalize-space()='Sort by']")
    public WebElement filter;

    @FindBy(className = "options_box_wrapper")
    public List<WebElement> sortOptions;

    public String totalTravelPlan(){
        return totalPlan.getText();
    }

    public void applyFilter(){
        filter.click();
    }
}
