package com.optimusEcom.properties;

public class Properties {
    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String baseUrl = propertiesReader.getBaseUrl();

    public static final String password = propertiesReader.getPassword();

    public static final String browser = propertiesReader.getBrowser();

    public static final String platformName = propertiesReader.getPlatform();

    public static final String automationName = propertiesReader.getAutomationName();

    public static final String deviceName = propertiesReader.getDeviceName();


}