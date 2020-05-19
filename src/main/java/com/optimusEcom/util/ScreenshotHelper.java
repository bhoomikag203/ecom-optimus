package com.optimusEcom.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {

    public void getScreenshot(WebDriver driver) {
        TakesScreenshot screenShot = ((TakesScreenshot) driver);

        File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss aa");
        String fileName = (String.format("./screenshots/%s", dateFormat.format(date) + ".png"));
        File destinationFile = new File((fileName));

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}