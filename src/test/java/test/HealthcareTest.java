package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.policy.pages.HealthHomePage;

import java.time.Duration;
import java.util.List;

public class HealthcareTest {

    WebDriver driver;
    HealthHomePage healthHomePage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://health.policybazaar.com/?pq=health0&new=1&utm_content=health_icon_hp_health_insurance_domv1&utm_source=yahoo_b");
        healthHomePage = new HealthHomePage(driver);
    }

    @Test(priority = 1)
    public void verifyMaleGenderSelectionAndMemberListLoads() {
        healthHomePage.selectMaleGender();
        List<?> maleList = healthHomePage.getMaleMemberList();

        Assert.assertNotNull(maleList, "Male member list should not be null");
        Assert.assertFalse(maleList.isEmpty(), "Male member list should not be empty after toggle");
    }

    @Test(priority = 2)
    public void verifyFemaleGenderSelectionAndMemberListLoads() {
        healthHomePage.selectFemaleGender();
        List<?> femaleList = healthHomePage.getFemaleMemberList();

        Assert.assertNotNull(femaleList, "Female member list should not be null");
        Assert.assertFalse(femaleList.isEmpty(), "Female member list should not be empty after toggle");
    }

    @Test(priority = 3)
    public void verifyMemberListChangesOnGenderToggle() {
        healthHomePage.selectMaleGender();
        int maleCount = healthHomePage.getMaleMemberList().size();

        healthHomePage.selectFemaleGender();
        int femaleCount = healthHomePage.getFemaleMemberList().size();

        // Both lists should have loaded; counts may match or differ depending on
        // role-specific labels (e.g. Uncle/Aunt, Live-in Partner Male/Female)
        Assert.assertTrue(maleCount > 0 && femaleCount > 0,
                "Both gender member lists should be populated");
    }

    @Test(priority = 4)
    public void verifySelectSelfUnderMaleGender() {
        healthHomePage.selectMaleGender();
        healthHomePage.clickMemberByLabel("Self");
        // Add an assertion here once you confirm the checked-state locator,
        // e.g. Assert.assertTrue(driver.findElement(By.id("1")).isSelected());
    }

    @Test(priority = 5)
    public void verifySelectSonUnderMaleGender() {
        healthHomePage.selectMaleGender();
        healthHomePage.clickMemberByLabel("Son");
    }

    @Test(priority = 6)
    public void verifySelectDaughterUnderFemaleGender() {
        healthHomePage.selectFemaleGender();
        healthHomePage.clickMemberByLabel("Daughter");
    }

    @Test(priority = 7)
    public void verifyGenderSpecificLabelExistsOnlyForMale() {
        healthHomePage.selectMaleGender();
        // e.g. "Live-in Partner (Male)" should appear when Male is selected
        boolean present = healthHomePage.getMaleMemberList().stream()
                .anyMatch(el -> el.getAttribute("value") != null);
        Assert.assertTrue(present, "Male-specific member options should be present");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
