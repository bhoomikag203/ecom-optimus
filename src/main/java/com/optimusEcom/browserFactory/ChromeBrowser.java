package com.optimusEcom.browserFactory;

import com.optimusEcom.properties.Properties;
import constants.Device;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser implements BrowserDriver {
    private boolean runInDocker = Properties.runInDocker;
    private boolean runInMobileView = Properties.mobileView;

    @Override
    public WebDriver getDriver() {
        setBinaryPath();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        if (runInDocker) {
            try {
                if (runInMobileView)
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), setMobileView(chromeOptions));

                else
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), chromeOptions);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        if (runInMobileView) {
            return new ChromeDriver(setMobileView(chromeOptions));

        }

        return new ChromeDriver(chromeOptions);
    }

    private ChromeOptions setMobileView(ChromeOptions chromeOptions) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", getDeviceName());
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;
    }

    private String getDeviceName() {
        return String.valueOf(Device.Galaxy_S5).replace("_", " ");
    }

    @Override
    public void setBinaryPath() {
        WebDriverManager.chromedriver().setup();
    }
}