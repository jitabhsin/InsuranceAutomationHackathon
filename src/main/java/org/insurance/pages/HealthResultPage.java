package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HealthResultPage {
    WebDriver driver;
    WaitUtils waitUtils;

    @FindBy(xpath = "//div[@id='riaChat']/div")
    public List<WebElement> planList;

    @FindBy(xpath = "//h4[normalize-space()='Optional add-ons']")
    WebElement optionalAddons;

    @FindBy(xpath = "//h2[@class='si_heading']")
    public WebElement heading;

    public HealthResultPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public int resultPlans(String product){
        int i = 0;
        waitUtils.waitForVisibility(optionalAddons);
        System.out.println(planList.size());
        for(WebElement element : planList){
            String title = element.findElement(By.xpath(".//div/div/h3")).getText();
            String price = "";
            if(i==0 && product.equals("Elevate")) {
                price = element.findElement(By.xpath(".//div/div/div/div[2]/p")).getText();
            }
            else if(product.equals("Activate Booster")){
                price = element.findElement(By.xpath(".//div/div[2]/div/p")).getText();
            }else {
                price = element.findElement(By.xpath(".//div/div/div/div[1]/p")).getText();
            }
            i++;
            System.out.println(title + " -> " + price);
        }
        return planList.size();
    }
}
