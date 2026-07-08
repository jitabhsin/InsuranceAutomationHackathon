package org.insurance.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.insurance.utils.JavaScriptUtils;
import org.insurance.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlanCoverQuote {

    private String planName;
    private String medicalCover;
    private String premium;

    public PlanCoverQuote(
            String planName,
            String medicalCover,
            String premium) {

        this.planName = planName;
        this.medicalCover = medicalCover;
        this.premium = premium;
    }

    public String getPlanName() {
        return planName;
    }

    public String getMedicalCover() {
        return medicalCover;
    }

    public String getPremium() {
        return premium;
    }
}