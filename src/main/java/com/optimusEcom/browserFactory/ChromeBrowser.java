package com.optimusEcom.browserFactory;

import com.optimusEcom.properties.Properties;
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
//        chromeOptions.addArguments("headless");

        if (runInDocker) {
            try {
                if (runInMobileView)
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), setMobileView(chromeOptions));

                else
                    return new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), chromeOptions);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } else if (runInMobileView) {
                return new ChromeDriver(setMobileView(chromeOptions));

        }

        return new ChromeDriver(chromeOptions);
    }

    private ChromeOptions setMobileView(ChromeOptions chromeOptions) {
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 340);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 2);

        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; OS 5_0 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A334 Safari/7534.48.3");

        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        return chromeOptions;
    }

    @Override
    public void setBinaryPath() {
        WebDriverManager.chromedriver().setup();
    }
}