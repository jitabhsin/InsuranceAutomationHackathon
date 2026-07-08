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
        waitUtils.waitForVisibility(optionalAddons);
        int total = planList.size();
        System.out.println(total);

        for(int i = 1; i <= total; i++){
            String cardXpath = "(//app-plan-card)[" + i + "]";

            String titleXpath = cardXpath + "//div/div/h3";
            String priceXpath;

            if(i == 1 && product.equals("Elevate")){
                priceXpath = cardXpath + "//div/div/div/div[2]/p";
            } else if(product.equals("Activate Booster")){
                priceXpath = cardXpath + "//div/div[2]/div/p";
            } else {
                priceXpath = cardXpath + "//div/div/div/div[1]/p";
            }

            String title = driver.findElement(By.xpath(titleXpath)).getText();
            String price = driver.findElement(By.xpath(priceXpath)).getText();

            System.out.println(title + " -> " + price);
        }
        return total;
    }
}
