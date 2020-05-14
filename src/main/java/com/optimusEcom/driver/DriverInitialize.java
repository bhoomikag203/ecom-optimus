package com.optimusEcom.driver;

import com.optimusEcom.browserFactory.ChromeBrowser;
import com.optimusEcom.browserFactory.FirefoxBrowser;
import com.optimusEcom.platforms.AndroidPlatform;
import com.optimusEcom.properties.Properties;
import constants.Browsers;
import constants.Platforms;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

//DriverInitializer
public class DriverInitialize {
    private String browser;
    private String platformName;
    private String deviceName = Properties.deviceName;
    private String automationName = Properties.automationName;

    public DriverInitialize(String browser) {
        this.browser = browser;
    }

    public WebDriver initialize() {
        browser = Properties.browser;
        platformName = Properties.platformName;
        WebDriver driver = null;

        //mobile view
        if (platformName.equalsIgnoreCase(Platforms.ANDROID)
                && browser.equalsIgnoreCase(Browsers.CHROME)) {

            driver = new AndroidPlatform().getDriver();
            return driver;

        }

        switch (browser) {
            case Browsers.CHROME:
                driver = new ChromeBrowser().getDriver();
                break;

            case Browsers.FIREFOX:
                driver = new FirefoxBrowser().getDriver();
                break;

        }

        DriverProvider.setDriver(driver);

        return driver;
    }

}