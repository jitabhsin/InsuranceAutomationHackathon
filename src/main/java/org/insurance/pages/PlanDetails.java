package org.insurance.pages;

public class PlanDetails {

    private String planName;
    private String medicalCover;
    private String premiumAmount;

    public PlanDetails(String planName, String medicalCover, String premiumAmount) {
        this.planName = planName;
        this.medicalCover = medicalCover;
        this.premiumAmount = premiumAmount;
    }

    public String getPlanName() {
        return planName;
    }

    public String getMedicalCover() {
        return medicalCover;
    }

    public String getPremiumAmount() {
        return premiumAmount;
    }

    @Override
    public String toString() {
        return planName + " | " + medicalCover + " | " + premiumAmount;
    }
}