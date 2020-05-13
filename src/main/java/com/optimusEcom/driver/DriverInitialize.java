package com.optimusEcom.driver;

import com.optimusEcom.properties.Properties;
import com.optimusEcom.util.TestUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

//DriverInitializer
public class DriverInitialize {
    private String browser;
    public WebDriver driver;

    public DriverInitialize(String browser) {
        this.browser = browser;
        System.out.println("Driver Initializing...");
    }

    public WebDriver initialize() {
        String browser = com.optimusEcom.properties.Properties.browser;
        String platformName = Properties.platformName;
        String deviceName = Properties.deviceName;
        String automationName = Properties.automationName;
        boolean dockerize = Properties.dockerize;

        //for mobile view
        if (platformName.equalsIgnoreCase("Android")) {
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
        } else {

            if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                try {
                    if (!dockerize)
                        driver = new FirefoxDriver(firefoxOptions);
                    else
                        driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), firefoxOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                try {
                    if (!dockerize)
                        driver = new ChromeDriver(chromeOptions);
                    else
                        driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), chromeOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        System.out.println("Driver initialized...");
        return driver;
    }

}