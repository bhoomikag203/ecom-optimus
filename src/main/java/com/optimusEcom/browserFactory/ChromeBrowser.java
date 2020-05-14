package com.optimusEcom.browserFactory;

import com.optimusEcom.properties.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeBrowser implements BrowserDriver {
    private boolean runInDocker = Properties.runInDocker;

    @Override
    public WebDriver getDriver() {
        setBinaryPath();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        if (runInDocker)
            try {
                return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        return new ChromeDriver(chromeOptions);
    }

    @Override
    public void setBinaryPath() {
        WebDriverManager.chromedriver().setup();
    }
}