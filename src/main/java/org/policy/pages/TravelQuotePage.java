package org.policy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.policy.utils.WaitUtils;

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
    public WebElement sortOptions;

    @FindBy(className = "tr_qv_planCardWrap")
    public List<WebElement> totalPlans;

    public String totalTravelPlan(){
        return totalPlan.getText();
    }

    public void applyFilter(){
        filter.click();

        WaitUtils wait = new WaitUtils(driver);

        WebElement applyBtn = wait.waitForVisibilityOfElementLocated(By.xpath("//button[contains(@class,'travel_main_cta') and normalize-space()='Apply']"));
        Select select = new Select(sortOptions);
        select.selectByVisibleText("Premium low to high");

        applyBtn.click();
    }

    public void topThreePlan(){
        for(int i=0;i<3;i++){
            WebElement plan = totalPlans.get(i);

            String name = plan.findElement(By.xpath(".//figcaption/p")).getText();
            String price = plan.findElement(By.xpath(".//span[@class='premiumPlanPrice']")).getText();

            System.out.println(name + " -> " + price);
        }
    }
}
