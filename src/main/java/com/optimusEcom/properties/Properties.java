package com.optimusEcom.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {
    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String baseUrl = propertiesReader.getBaseUrl();

    public static final String password = propertiesReader.getPassword();

    public static final String browser = propertiesReader.getBrowser();

}