package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.policybazaar.com/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
