package com.paulmarkcastillo.seleniumtoolbox.configs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseCommonConfig {

    Properties properties;

    BaseCommonConfig(String localConfig, String defaultConfig) {
        String propertyFilePath = defaultConfig;
        File localProperty = new File(localConfig);
        if (localProperty.exists()) {
            propertyFilePath = localConfig;
            System.out.println("USING  LOCAL CONFIGURATIONS");
        } else {
            System.out.println("USING DEFAULT CONFIGURATIONS");
        }

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration File not found at " + propertyFilePath + ".");
        }
    }

    public static String getEnvironment(String name) {
        String value = System.getProperty(name);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException(name + "not specified in the Maven Environment");
        }
    }

    protected String getProperty(String property) {
        return properties.getProperty(property);
    }

    protected long getPropertyLong(String property) {
        return Long.parseLong(getProperty(property));
    }

    public long getImplicitlyWait() {
        return getPropertyLong("implicitlyWait");
    }

    public String getReportConfigPath() {
        return getProperty("reportConfigPath");
    }

    public String machine() {
        return getProperty("machine");
    }

    public long webDriverWait() {
        return getPropertyLong("webDriverWait");
    }

    public String javaVersion() {
        return getProperty("javaVersion");
    }

    public String cucumberVersion() {
        return getProperty("cucumberVersion");
    }

    public String mavenVersion() {
        return getProperty("mavenVersion");
    }

    public String assignAuthor() {
        return getProperty("assignAuthor");
    }

    public String reportPath() {
        return getProperty("reportPath");
    }
}
