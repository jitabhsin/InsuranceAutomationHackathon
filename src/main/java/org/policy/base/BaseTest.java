package org.policy.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver setUp(String browser) {
        String websiteLink = "https://www.policybazaar.com/?utm_source=google_brand&utm_medium=ppc&utm_term=Policybazaar&utm_campaign=Brand_PolicyBazaar_Exact00PolicyBazaar&gad_source=1&gad_campaignid=2056207150&gbraid=0AAAAADwVZjKi4505gYOT6yyAWdI6mTm9w&gclid=EAIaIQobChMIgoXJ7a-ulQMVsGwPAh0TQynmEAAYASAAEgI-3PD_BwE";

        switch (browser.toLowerCase()) {
            case "edge":
                driver = new EdgeDriver();
                break;

            case "chrome":
                driver = new ChromeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(websiteLink);

        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}