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
    private boolean runInMobileView = Properties.mobileView;

    @Override
    public WebDriver getDriver() {
        setBinaryPath();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.addArguments("--headless");

        if (runInDocker)

            try {
                if (runInMobileView)
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), setMobileView(firefoxOptions));

                else
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), firefoxOptions);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        if (runInMobileView) {
            return new FirefoxDriver(setMobileView(firefoxOptions));
        }

        return new FirefoxDriver(firefoxOptions);
    }

    private FirefoxOptions setMobileView(FirefoxOptions firefoxOptions) {
        firefoxOptions.addArguments("--width=340");
        firefoxOptions.addArguments("--height=640");
        return firefoxOptions;
    }

    @Override
    public void setBinaryPath() {
        WebDriverManager.firefoxdriver().setup();
    }
}