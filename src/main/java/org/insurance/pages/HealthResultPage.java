package org.insurance.pages;

import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class HealthResultPage {
    WebDriver driver;
    WaitUtils waitUtils;

    @FindBy(xpath = "//div[@id='riaChat']/div")
    List<WebElement> planList;

    @FindBy(xpath = "//h4[normalize-space()='Optional add-ons']")
    WebElement optionalAddons;


    public HealthResultPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public int resultPlans(){
        int i = 0;
        waitUtils.waitForVisibility(optionalAddons);
        System.out.println(planList.size());
        for(WebElement element : planList){
            String title = element.findElement(By.xpath(".//div/div/h3")).getText();
            String price = "";
            if(i==0) {
                price = element.findElement(By.xpath(".//div/div/div/div[2]/p")).getText();
            }else {
                price = element.findElement(By.xpath(".//div/div/div/div[1]/p")).getText();
            }
            i++;
            System.out.println(title + " -> " + price);
        }
        return planList.size();
    }
}
