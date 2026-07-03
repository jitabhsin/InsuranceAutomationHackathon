//package tests;
//
//import basetest.BaseTest;
//import org.insurance.pages.CarPage;
//import org.insurance.pages.HomePage;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import utils.ExcelReader;
//
//public class TC_14SelectCarAndModel extends BaseTest {
//
//    HomePage homePage;
//    CarPage carPage;
//
//    @DataProvider(name = "carData")
//    public Object[][] getData() {
//        ExcelReader excel = new ExcelReader();
//        return excel.readSheetCar();
//    }
//
//    @Test(dataProvider = "carData")
//    public void verifySelectCarAndModel(
//            String city,
//            String make,
//            String model,
//            String mobile,
//            String email) {
//
//        homePage = new HomePage(driver);
//        carPage = new CarPage(driver);
//
//        homePage.clickCarInsurance();
//
//        carPage.clickNewVehicleLink();
//
//        carPage.enterCity(city);
//        carPage.selectCity(city);
//
//        carPage.enterCarMake(make);
//        carPage.selectCarModel(model);
//
//        carPage.enterMobile(mobile);
//
//        carPage.enterEmail(email);
//
//        carPage.clickProceed();
//
//        Assert.assertTrue(
//                carPage.isSelectPlansPageDisplayed(),
//                "Select Plans page is not displayed");
//
//        System.out.println("✓ City : " + city);
//        System.out.println("✓ Make : " + make);
//        System.out.println("✓ Model : " + model);
//        System.out.println("✓ Mobile : " + mobile);
//        System.out.println("✓ Email : " + email);
//        System.out.println("✓ Select Plans page displayed successfully");
//    }
//}