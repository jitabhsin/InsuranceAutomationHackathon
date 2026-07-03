package org.insurance.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots";
        File folder = new File(screenshotDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String screenshotPath = screenshotDir + File.separator + testName + "_" + timestamp + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotPath);
            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot saved at: {}", screenshotPath);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
        }
        return screenshotPath;
    }
}