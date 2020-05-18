package com.optimusEcom.driver;

import com.optimusEcom.browserFactory.ChromeBrowser;
import com.optimusEcom.browserFactory.FirefoxBrowser;
import com.optimusEcom.platforms.AndroidPlatform;
import com.optimusEcom.properties.Properties;
import constants.Browser;
import constants.Platform;
import org.openqa.selenium.WebDriver;

//DriverInitializer
public class DriverInitialize {
    private String browser;
    private String platformName;

    public DriverInitialize(String browser) {
        this.browser = browser;
    }

    public WebDriver initialize() {
        browser = Properties.browser;
        platformName = Properties.platformName;
        WebDriver driver = null;

        //run in android mobile/emulator
        if (platformName.equalsIgnoreCase(Platform.ANDROID)
                && browser.equalsIgnoreCase(Browser.CHROME)) {

            driver = new AndroidPlatform().getDriver();
            return driver;

        }

        switch (browser) {
            case Browser.CHROME:
                driver = new ChromeBrowser().getDriver();
                break;

            case Browser.FIREFOX:
                driver = new FirefoxBrowser().getDriver();
                break;

        }

        DriverProvider.setDriver(driver);

        return driver;
    }

}