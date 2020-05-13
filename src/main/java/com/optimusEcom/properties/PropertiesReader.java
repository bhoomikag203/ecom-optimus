package com.optimusEcom.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties prop;

    public PropertiesReader() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return prop.getProperty("url");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getPlatform(){
        return prop.getProperty("platformName");
    }

    public String getAutomationName(){
        return prop.getProperty("automationName");
    }

    public String getDeviceName(){
        return prop.getProperty("deviceName");
    }

    public boolean getRunInDocker(){
        return Boolean.parseBoolean(prop.getProperty("runInDocker"));
    }


}