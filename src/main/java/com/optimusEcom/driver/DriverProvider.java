package com.optimusEcom.driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    public static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        DriverProvider.driver = driver;
    }
}