package com.optimusEcom.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ScreenshotHelper {

    public void getScreenshot(WebDriver driver) {
        TakesScreenshot screenShot = ((TakesScreenshot) driver);

        File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);

        String fileName = (String.format("./screenshots/%s", UUID.randomUUID().toString() + ".png"));
        File destinationFile = new File((fileName));

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}