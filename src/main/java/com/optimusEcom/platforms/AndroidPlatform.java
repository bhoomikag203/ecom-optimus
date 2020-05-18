package com.optimusEcom.platforms;

import com.optimusEcom.properties.Properties;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidPlatform {
    private String platformName = Properties.platformName;
    private String deviceName = Properties.deviceName;
    private String automationName = Properties.automationName;
    private String browser = Properties.browser;

    public AndroidDriver getDriver() {

        AndroidDriver driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);

        try {
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}