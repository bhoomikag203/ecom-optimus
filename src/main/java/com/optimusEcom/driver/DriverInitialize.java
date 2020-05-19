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
        if (platformName.equalsIgnoreCase(String.valueOf(Platform.ANDROID))
                && browser.equalsIgnoreCase(String.valueOf(Browser.CHROME))) {

            driver = new AndroidPlatform().getDriver();
            return driver;

        }

        //run in chrome
        if (browser.equalsIgnoreCase(String.valueOf(Browser.CHROME)))

            driver = new ChromeBrowser().getDriver();

        //run in firefox
        else if (browser.equalsIgnoreCase(String.valueOf(Browser.FIREFOX)))

            driver = new FirefoxBrowser().getDriver();

        DriverProvider.setDriver(driver);

        return driver;
    }

}