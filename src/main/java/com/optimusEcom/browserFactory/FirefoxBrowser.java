package com.optimusEcom.browserFactory;

import com.optimusEcom.properties.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxBrowser implements BrowserDriver {
   private boolean runInDocker = Properties.runInDocker;

    @Override
    public WebDriver getDriver() {
        setBinaryPath();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--headless");

        if (runInDocker)
            try {
                return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), firefoxOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        return new FirefoxDriver(firefoxOptions);
    }

    @Override
    public void setBinaryPath() {
        WebDriverManager.firefoxdriver().setup();
    }
}