package com.optimusEcom.browserFactory;

import org.openqa.selenium.WebDriver;

public interface BrowserDriver {

    WebDriver getDriver();

    void setBinaryPath();
}
